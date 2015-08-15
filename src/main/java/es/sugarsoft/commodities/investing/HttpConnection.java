package es.sugarsoft.commodities.investing;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpConnection {
	
	public static final String DOMAIN = "investing.com";
	public static final String CHART_URL ="http://sbcharts." + DOMAIN + "/charts_xml/jschart_sideblock_###_area.json";
	public static final String TABLE_URL ="http://es." + DOMAIN + "/commodities/";

	private int LENGHT = 1024;

	private URL url;
	private URLConnection urlConn;
	private Map<String, List<String>> headers;

	public HttpConnection(String inputUrl) throws Exception{
		url = new URL(inputUrl);
		urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		urlConn.setRequestProperty("Accept-Encoding","UTF-8");
		urlConn.setRequestProperty("Accept-Language","es-ES,es;q=0.8,en;q=0.6,fi;q=0.4");
		urlConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");

		urlConn.connect();

		headers = urlConn.getHeaderFields();

	}

	public List<String> getHeader(String key){
		return headers.get(key);
	}
	
	public InputStream getInputStream() throws IOException{
		return urlConn.getInputStream();
	}

	public String getOutput() throws IOException{
		final char[] buffer = new char[LENGHT];
		final StringBuilder out = new StringBuilder();
		try{
			Reader in = new InputStreamReader(urlConn.getInputStream(), "UTF-8");
			for (;;) {
				int rsz = in.read(buffer, 0, buffer.length);
				if (rsz < 0)
					break;
				out.append(buffer, 0, rsz);
			}
		}
		catch (UnsupportedEncodingException ex) {
			/* ... */
		}
		catch (IOException ex) {
			/* ... */
		}
		return out.toString();
	}

}
