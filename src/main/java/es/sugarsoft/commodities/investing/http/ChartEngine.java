package es.sugarsoft.commodities.investing.http;

import java.io.IOException;

import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;

public class ChartEngine {
	
	
	private static final String CHART_URL = "http://sbcharts." + HtmlConnectionService.DOMAIN + "/charts_xml/jschart_sideblock_###_area.json";;
	private HtmlConnectionService connection;
	
	public ChartEngine(long id) throws Exception{
		connection = new HtmlConnectionService(CHART_URL.replace("###", Long.toString(id)));
	}
	
	public String getJson() throws IOException{
		return connection.getHtmlOutput();
	}
	
}
