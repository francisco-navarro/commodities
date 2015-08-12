package es.sugarsoft.commodities.investing;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.resources.Commodity;

public class UrlTableEngine {
	
	
	private Connection connection;
	private Document doc;

	public UrlTableEngine(String url) throws Exception {
		connection = new Connection(url);	
		
		doc = Jsoup.parse(connection.getOutput());
		
		
	}

	public List<Commodity> getList() throws Exception{
		List<Commodity> list = new ArrayList<Commodity>();
		
		Elements elems = doc.select("#dailyTab > tbody > tr");
		
		for(int i= 0 ; i<elems.size(); i++){
			Element e = elems.get(i);
			Commodity c = new Commodity();			
			c.setPair(e.attr("id").replace("pair_", ""));
			c.setName(e.select("a").get(0).attr("title"));
			c.setJson(new ChartEngine(c.getPair()).getJson());
			list.add(c);
		}
		

		
		return list;
	}

}
