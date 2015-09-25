package es.sugarsoft.commodities.investing.http;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.investing.http.connection.HtmlConnection;
import es.sugarsoft.commodities.investing.http.util.ItemMethod;
import es.sugarsoft.commodities.resources.Item;

public class HttpDetailParser {

	private static final Logger logger = Logger.getLogger(HtmlConnection.class);

	private HtmlConnection connection;

	public HttpDetailParser(String url) throws Exception{
		connection = new HtmlConnection(HtmlConnection.SECTION_URL + url );
	}

	public Item getItemDetails(Item item) {
		Document doc = null;		
		try {
			doc = Jsoup.parse(connection.getOutput());
			Elements detailTable = doc.select(".overviewDataTable");
			Elements fields = detailTable.select("div").select(".inlineblock");
			for(int j=0;j<fields.size();j++){
				String key = getKey(fields, j);
				String value = getValue(fields, j);
				assignValue(item,key,value);
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
		return item;
	}


	/**
	 * Buscamos el metodo correspondiente al texto de la tabla para hacer un setter
	 * @param item
	 * @param key
	 * @param value
	 */
	private void assignValue(Item item, String key, String value) {
		if(ItemMethod.get(key) != null ){
			try {
				ItemMethod.get(key).getMethod().invoke(item, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.warn("Error asignando valor",e);
			} catch (IllegalArgumentException e) {
				logger.warn("Error asignando valor",e);
			} catch (InvocationTargetException e) {
				logger.warn("Error asignando valor",e);
			}
		}else{
			logger.warn("No se encuentra el metodo para "+key);
		}

	}


	private String getKey(Elements fields, int j) {
		Node span = fields.get(j).childNodes().get(0);
		Node text = span.childNodes().get(0);
		return ((TextNode)text).getWholeText();
	}

	private String getValue(Elements fields, int j) {
		Node span = fields.get(j).childNodes().get(1);
		Node text = span.childNodes().get(0);
		return ((TextNode)text).getWholeText();
	}



}
