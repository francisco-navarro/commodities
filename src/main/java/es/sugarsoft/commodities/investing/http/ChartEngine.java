package es.sugarsoft.commodities.investing.http;

import java.io.IOException;

import es.sugarsoft.commodities.investing.http.connection.HtmlConnection;

public class ChartEngine {
	
	
	private static final String CHART_URL = "http://sbcharts." + HtmlConnection.DOMAIN + "/charts_xml/jschart_sideblock_###_area.json";;
	private HtmlConnection connection;
	
	public ChartEngine(long id) throws Exception{
		connection = new HtmlConnection(CHART_URL.replace("###", Long.toString(id)));
	}
	
	public String getJson() throws IOException{
		return connection.getOutput();
	}
	
}
