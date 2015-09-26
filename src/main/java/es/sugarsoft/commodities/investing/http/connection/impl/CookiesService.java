package es.sugarsoft.commodities.investing.http.connection.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ICookiesService;

@Service("cookiesService")
public class CookiesService implements ICookiesService{

	public static final String DOMAIN = new String(new byte[] {105, 110, 118, 101, 115, 116, 105, 110, 103, 46, 99, 111, 109});
	private static final String TABLE_URL = "http://es." + DOMAIN + "/commodities/";

	private static String PHPSESSID;
	private static String fpros_popup;

	public void setCookies(Map<String, List<String>> headers) {

		for (String headerKey : headers.keySet()) {
			if (headerKey != null && headerKey.equals("Set-Cookie")) {
				for (String cookie : headers.get(headerKey)) {
					if (cookie.startsWith("PHPSESSID")) {
						PHPSESSID =(cookie.replaceFirst(";.*", ""));
					} else if (cookie.startsWith("fpros_popup")) {
						fpros_popup = (cookie.replaceFirst(";.*", ""));
					}
				}
			}
		}
	}

	public synchronized void renewCookies(long id){		

		URLConnection urlConn;
		try{
			urlConn = initConnection();
			// Connect to a html
			urlConn.connect();
			// Close the html connection
			urlConn.getInputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private URLConnection initConnection() throws IOException {
		URL url = new URL(TABLE_URL);

		URLConnection urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		urlConn.setRequestProperty("Accept-Encoding", "UTF-8");
		urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en;q=0.6,fi;q=0.4");
		urlConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");
		return urlConn;
	}

	@Override
	public String getPHPSESSID() {
		return PHPSESSID;
	}

	@Override
	public String getFpros_popup() {
		return fpros_popup;
	}


}
