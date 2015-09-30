package es.sugarsoft.commodities.investing.http.connection.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ICookiesService;
import es.sugarsoft.commodities.investing.http.connection.ISocketChartConnectionService;

@Service("socketChartConnectionService")
public class SocketChartConnectionService  implements ISocketChartConnectionService{

	private static final Logger logger = Logger.getLogger(SocketChartConnectionService.class);

	public static final String DOMAIN = new String(new byte[] {105, 110, 118, 101, 115, 116, 105, 110, 103, 46, 99, 111, 109});

	private static final String COOKIE_CONSTANTS = "activeConsent-4=1.1; __PHPSESSID__; _gat=1; _gat_allSitesTracker=1; geoC=ES; SideBlockUser=a%3A2%3A%7Bs%3A10%3A%22stack_size%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Bi%3A8%3B%7Ds%3A6%3A%22stacks%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Ba%3A1%3A%7Bi%3A0%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228830%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A17%3A%22%2Fcommodities%2Fgold%22%3B%7D%7D%7D%7D; __fpros_popup__; gtmFired=OK; _ga=GA1.2.1197578690.1439400630";
	private static final String URI = "/common/modules/js_instrument_chart/api/data.php";
	private static final String URI_HISTORICAL = "/instruments/HistoricalDataAjax";
	
	private ICookiesService cookiesService;
	
	public enum Json {
		chart,
		historical
	}

	@Autowired
	public SocketChartConnectionService(ICookiesService cookiesService) throws Exception {
		this.cookiesService = cookiesService;
	}
	
	@Override
	public String getJsonData(long id,  Date fromDate, Date endDate)throws IOException {
		if(fromDate!=null){
			return getJsonData(id, Json.historical,fromDate,endDate);
		}else{
			return getJsonData(id, Json.chart,null,null);
		}
	}
	
	private String getJsonData(long id, Json type, Date fromDate, Date endDate) throws IOException {

		Socket socket = null;
		String json ="";

		try {
			URL url = new java.net.URL("http://es."+DOMAIN);
			socket = new Socket(url.getHost(), url.getPort() == -1 ? 80 : url.getPort());

			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
			wr.write(getDataPetition(id,type, fromDate, endDate));
			wr.write("\r\n");
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			String status="";
			while ((line = rd.readLine()) != null) {
				if(line.contains("{")){
					json=line;
				}else if(line.contains("HTTP")){
					status = line;					
				}else{
					logger.debug(line);
				}
			}
			wr.close();
			rd.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
		
		return json;

	}

	private String getDataPetition(long id, Json type, Date fromDate, Date endDate) {
		final String RETURN = "\r\n";
		String uri = "";
		if(type == Json.chart){
			uri =buildURI(id);
		}else if(type == Json.historical){
			uri = buildHistoricalURI(id, fromDate, endDate);
		}
		uri =buildURI(id);
		String output = "GET " + uri + " HTTP/1.1" + RETURN + "Host: es.investing.com" + RETURN
				+ "Connection: keep-alive" + RETURN + "Accept: application/json, text/javascript, */*; q=0.01" + RETURN
				+ "X-Requested-With: XMLHttpRequest" + RETURN + "X-FirePHP-Version: 0.0.6" + RETURN
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36"
				+ RETURN + "Referer: http://es.investing.com/commodities/" + RETURN + "Accept-Encoding: UTF-8" + RETURN
				+ "Accept-Language: es-ES,es;q=0.8,en;q=0.6,fi;q=0.4" + RETURN + "Cookie: "
				+ COOKIE_CONSTANTS.replace("__PHPSESSID__", cookiesService.getPHPSESSID()).replace("__fpros_popup__", cookiesService.getFpros_popup()) + RETURN;

		return output;
	}

	private String buildURI(long id) {

		String uri = URI + "?";
		uri += //"symbol=Oro" + "&" +
				"pair_id=" +id+ "&" +
				"chart_type=area" + "&" +
				"pair_interval=1800" + "&" + 
				"candle_count=25" + "&" +
				"events=no" + "&" +
				"volume_series=yes";
		return uri;
	}
	
	private String buildHistoricalURI(long id, Date fromDate, Date endDate){
		String uri = URI_HISTORICAL + "?";
		uri += "action:historical_data"+
		"curr_id:" +id+ "&" +
		"st_date:14/08/2015" +
		"end_date:30/08/2015"+
		"interval_sec:Daily";
		return uri;
	}

}
