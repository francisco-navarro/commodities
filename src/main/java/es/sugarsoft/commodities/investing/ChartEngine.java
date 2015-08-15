package es.sugarsoft.commodities.investing;

import java.io.IOException;

import es.sugarsoft.commodities.investing.http.HttpConnection;

public class ChartEngine {
	
	
	private HttpConnection connection;
	
	public ChartEngine(long id) throws Exception{
		connection = new HttpConnection(HttpConnection.CHART_URL.replace("###", Long.toString(id)));
	}
	
	public String getJson() throws IOException{
		return connection.getOutput();
	}
	
}
