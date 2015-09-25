package es.sugarsoft.commodities.investing.http;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.investing.http.connection.HtmlConnection;
import es.sugarsoft.commodities.investing.http.util.ItemMethod;
import es.sugarsoft.commodities.resources.Item;

public class HttpHistoryParser {

	private static final Logger logger = Logger.getLogger(HtmlConnection.class);
	private static final SimpleDateFormat monthParser = new SimpleDateFormat("MMM dd",Locale.ENGLISH);

	private HtmlConnection connection;

	public HttpHistoryParser(String url) throws Exception{
		connection = new HtmlConnection(HtmlConnection.SECTION_URL + url +"-historical-data" );
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
				assignValue(item,key,decodeValue(key,value));
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
	private void assignValue(Item item, String key, Object value) {		
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


	private Object decodeValue(String key, String value) {
		if(key.equals("Mes")){
			try {
				return monthParser.parse(value.toLowerCase());
			} catch (ParseException e) {
				logger.error("Error parseando ",e);
			}
		}
		return value;
	}



}
