package es.sugarsoft.commodities;

import java.io.IOException;

public class ChartEngine {
	
	final static String url = "http://sbcharts.investing.com/charts_xml/jschart_sideblock_###_area.json";

	private Connection connection;
	
	public ChartEngine(String id) throws Exception{
		connection = new Connection(url.replace("###", id));
	}
	
	public String getJson() throws IOException{
		return connection.getOutput();
	}
	
}
