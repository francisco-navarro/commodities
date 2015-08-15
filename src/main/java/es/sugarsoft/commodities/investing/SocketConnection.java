package es.sugarsoft.commodities.investing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class SocketConnection {

	private static final String DOMAIN = "investing.com";
	private static final String TABLE_URL = "http://es." + DOMAIN + "/commodities/";

	private int LENGHT = 1024;

	private URL url;
	private URLConnection urlConn;
	private Map<String, List<String>> headers;

	private String PHPSESSID;
	private String fpros_popup;
	private static final String COOKIE_CONSTANTS = "activeConsent-4=1.1; __PHPSESSID__; _gat=1; _gat_allSitesTracker=1; geoC=ES; SideBlockUser=a%3A2%3A%7Bs%3A10%3A%22stack_size%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Bi%3A8%3B%7Ds%3A6%3A%22stacks%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Ba%3A1%3A%7Bi%3A0%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228830%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A17%3A%22%2Fcommodities%2Fgold%22%3B%7D%7D%7D%7D; __fpros_popup__; gtmFired=OK; _ga=GA1.2.1197578690.1439400630";
	private static final String URI = "/common/modules/js_instrument_chart/api/data.php?symbol=Oro&pair_id=8830&chart_type=area&pair_interval=1800&candle_count=120&events=yes&volume_series=yes";

	public SocketConnection() throws Exception {

		initConnection();
		// Connect first connection to html
		urlConn.connect();
		getCookies();
		// Close the html connection
		urlConn.getInputStream().close();

		// Make a petition to get json data
		getData();
	}

	private void initConnection() throws IOException {
		url = new URL(TABLE_URL);

		urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		urlConn.setRequestProperty("Accept-Encoding", "UTF-8");
		urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en;q=0.6,fi;q=0.4");
		urlConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
	}

	private void getCookies() {
		headers = urlConn.getHeaderFields();
		for (String headerKey : headers.keySet()) {
			if (headerKey != null && headerKey.equals("Set-Cookie")) {
				for (String cookie : headers.get(headerKey)) {
					if (cookie.startsWith("PHPSESSID")) {
						PHPSESSID = cookie.replaceFirst(";.*", "");
					} else if (cookie.startsWith("fpros_popup")) {
						fpros_popup = cookie.replaceFirst(";.*", "");
					}
				}
			}
		}
	}

	public List<String> getHeader(String key) {
		return headers.get(key);
	}

	public InputStream getInputStream() throws IOException {
		return urlConn.getInputStream();
	}

	
	private void getData() throws IOException {
		
		Socket socket = null;
		
		try {
			
			socket = new Socket(url.getHost(), url.getPort()==-1?80:url.getPort());
			
		    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));		   
		    wr.write(getDataPetition());
		    wr.write("\r\n");
		    wr.flush();

		    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		      System.out.println(line);
		    }
		    wr.close();
		    rd.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
				;
			}
		}

	}

	private String getDataPetition() {
		final String RETURN = "\r\n";
		String output = "GET " + URI + " HTTP/1.1" + RETURN + "Host: es.investing.com" + RETURN
				+ "Connection: keep-alive" + RETURN + "Accept: application/json, text/javascript, */*; q=0.01" + RETURN
				+ "X-Requested-With: XMLHttpRequest" + RETURN + "X-FirePHP-Version: 0.0.6" + RETURN
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36"
				+ RETURN + "Referer: http://es.investing.com/commodities/" + RETURN + "Accept-Encoding: UTF-8" + RETURN
				+ "Accept-Language: es-ES,es;q=0.8,en;q=0.6,fi;q=0.4" + RETURN + "Cookie: "
				+ COOKIE_CONSTANTS.replace("__PHPSESSID__", PHPSESSID).replace("__fpros_popup__", fpros_popup) + RETURN;

		return output;
	}

}
