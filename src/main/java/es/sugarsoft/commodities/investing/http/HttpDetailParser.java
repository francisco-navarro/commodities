package es.sugarsoft.commodities.investing.http;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.investing.http.connection.HtmlConnection;
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
			Elements fields = detailTable.select("div");
			for(int j=0;j<fields.size();j++){
				logger.debug(fields.get(j));
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return item;
	}
	
	

}
