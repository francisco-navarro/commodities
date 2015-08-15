package es.sugarsoft.commodities.investing;

import java.io.IOException;

public class ChartEngine {
	
	
	private HttpConnection connection;
	
	public ChartEngine(String id) throws Exception{
		connection = new HttpConnection(HttpConnection.CHART_URL.replace("###", id));
	}
	
	public String getJson() throws IOException{
		return connection.getOutput();
	}
	
}
