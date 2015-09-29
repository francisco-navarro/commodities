package es.sugarsoft.commodities.investing.http.connection.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ICookiesService;
import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;

@Service("htmlConnectionService")
public class HtmlConnectionService implements IHtmlConnectionService {
	
	private static final Logger logger = Logger.getLogger(HtmlConnectionService.class);


	private int LENGHT = 1024;

	private String status;
	private URL url;
	private URLConnection urlConn;
	private Map<String, List<String>> headers;
	
	private ICookiesService cookiesService;
	
	@Autowired
	public HtmlConnectionService(ICookiesService cookiesService){
		this.cookiesService= cookiesService;
	}
	
	@Override
	public String connect(String inputUrl) throws Exception{
		url = new URL(inputUrl);
		logger.debug("New connection to "+url);
		urlConn = url.openConnection();
		urlConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		urlConn.setRequestProperty("Accept-Encoding","UTF-8");
		urlConn.setRequestProperty("Accept-Language","es-ES,es;q=0.8,en;q=0.6,fi;q=0.4");
		urlConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36");

		urlConn.connect();

		headers = urlConn.getHeaderFields();
		cookiesService.setCookies(headers);
		status = headers.get(null).get(0);
		
		return getHtmlOutput();
	}


	/**
	 * Si la conexión es correcta, lee el html de la página desde el InputStream
	 * @return HTML de la página en UTF-8
	 * @throws IOException
	 */
	public String getHtmlOutput() throws IOException{	
		if(status.matches("HTTP/1.[0-9] 2[0-9][0-9].*")){
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
		return "{\"attr\":{}}";
	}


}
