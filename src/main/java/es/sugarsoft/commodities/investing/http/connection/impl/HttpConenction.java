package es.sugarsoft.commodities.investing.http.connection.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.apache.log4j.Logger;


public class HttpConenction {
	
	private static final Logger logger = Logger.getLogger(HttpConenction.class);

	public static final String DOMAIN = new String(new byte[] {105, 110, 118, 101, 115, 116, 105, 110, 103, 46, 99, 111, 109});

	private static final String COOKIE_CONSTANTS = "activeConsent-4=1.1; __PHPSESSID__; _gat=1; _gat_allSitesTracker=1; geoC=ES; SideBlockUser=a%3A2%3A%7Bs%3A10%3A%22stack_size%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Bi%3A8%3B%7Ds%3A6%3A%22stacks%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Ba%3A1%3A%7Bi%3A0%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228830%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A17%3A%22%2Fcommodities%2Fgold%22%3B%7D%7D%7D%7D; __fpros_popup__; gtmFired=OK; _ga=GA1.2.1197578690.1439400630";
	//private static final String COOKIE_CONSTANTS = "activeConsent-4=1.1; gtmFired=OK; PHPSESSID=h39j1jjhdb5s0khni2p259dr24; geoC=ES; fpros_popup=up; _gat=1; _gat_allSitesTracker=1; SideBlockUser=a%3A2%3A%7Bs%3A10%3A%22stack_size%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Bi%3A8%3B%7Ds%3A6%3A%22stacks%22%3Ba%3A1%3A%7Bs%3A11%3A%22last_quotes%22%3Ba%3A7%3A%7Bi%3A0%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bi%3A40751%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A27%3A%22%2Fcommodities%2Fgold%3Fcid%3D40751%22%3B%7Di%3A1%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228830%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A17%3A%22%2Fcommodities%2Fgold%22%3B%7Di%3A2%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228834%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A25%3A%22%2Fcommodities%2Flondon-sugar%22%3B%7Di%3A3%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228849%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A22%3A%22%2Fcommodities%2Fcrude-oil%22%3B%7Di%3A4%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A1%3A%221%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A26%3A%22Euro+D%C3%B3lar+estadounidense%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A19%3A%22%2Fcurrencies%2Feur-usd%22%3B%7Di%3A5%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228911%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A26%3A%22%2Fcommodities%2Flondon-coffee%22%3B%7Di%3A6%3Ba%3A3%3A%7Bs%3A7%3A%22pair_ID%22%3Bs%3A4%3A%228914%22%3Bs%3A10%3A%22pair_title%22%3Bs%3A0%3A%22%22%3Bs%3A9%3A%22pair_link%22%3Bs%3A24%3A%22%2Fcommodities%2Flive-cattle%22%3B%7D%7D%7D%7D; _ga=GA1.2.1197578690.1439400630";
	
	private String json;
	private String html;
	private String uri;
	private URL url;
	private String accept_types;
	private String userAgent;
	private String referer;
	private String status;
	private String method;
	private String params;

	private static String PHPSESSID;
	private static String fpros_popup;
	
	public HttpConenction(String uri, String acceptTypes) {				
		this(uri, acceptTypes, "GET");
	}
	
	public HttpConenction(String uri, String acceptTypes, String method) {
		
		logger.debug("Obteniendo conexion para "+uri);
		this.uri = uri;
		this.userAgent= "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36";
		this.referer = "http://es.investing.com/commodities/";
		this.method = method;
		this.accept_types = acceptTypes;
		
		try {
			this.url = new java.net.URL("http://es."+DOMAIN);
		} catch (MalformedURLException e) {
			logger.error("Error obteniendo url de "+DOMAIN,e);
		}
	}
	
	public void makePetition(){	
		
		Socket socket = null;

		try {
			
			socket = new Socket(url.getHost(), url.getPort() == -1 ? 80 : url.getPort());
			BufferedWriter wr = writePetition(socket);
			BufferedReader rd = getReader(socket);
			
			String line="";
			boolean append=false;
			StringBuffer sb = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				logger.debug(line);
				if(append){
					sb.append(line);
				}else if(line.contains("HTTP")){
					status = line;	
				}else if(line.contains("{")){
					json=line;
				}else if(line.contains("<")){
					append = true;
					sb.append(line);
				}
			}
			wr.close();
			rd.close();
			
			if(sb.length()>0){
				html = sb.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return rd;
	}

	private BufferedWriter writePetition(Socket socket) throws UnsupportedEncodingException, IOException {
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		wr.write(getInputDataPetition());
		wr.write("\r\n");
		wr.flush();
		return wr;
	}

	private String getInputDataPetition() {
		final String RETURN = "\r\n";
		String output = method+" " + uri + " HTTP/1.1" + RETURN 
				+ "Host: es.investing.com" + RETURN
				+ "Connection: keep-alive" + RETURN 
				+ "Accept: "+accept_types+", */*; q=0.01" + RETURN
				+ "Origin: http://es.investing.com "+ RETURN
				+ "X-Requested-With: XMLHttpRequest" + RETURN 
				+ "X-FirePHP-Version: 0.0.6" + RETURN
				+ "Content-Type: application/x-www-form-urlencoded" + RETURN //para peticiones post
				+ "User-Agent: "+ userAgent + RETURN  
				+ "Referer: " + referer + RETURN
				+ "Accept-Encoding: UTF-8" + RETURN
				+ "Accept-Language: es-ES,es;q=0.8,en;q=0.6,fi;q=0.4" + RETURN 
				+ "Cookie: " + COOKIE_CONSTANTS.replace("__PHPSESSID__", PHPSESSID).replace("__fpros_popup__", fpros_popup) + RETURN
				
				;
		if(method.equals("POST")){
			output +=  "Content-Length: 101" + RETURN  //para peticiones post
					+RETURN + params;
		}
		return output;
	}
	
	public void setParams(String params) {
		this.params = params;
	}

	public String getJson() {
		if(json==null){
			makePetition();
		}
		return json;
	}
	
	public String getHtml() {
		if(html==null){
			makePetition();
		}
		return html;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public static void injectCookies(String PHPSESSID, String fpros_popup){
		HttpConenction.PHPSESSID = PHPSESSID;
		HttpConenction.fpros_popup = fpros_popup;
	}
	
	public void setAccept_types(String accept_types) {
		this.accept_types = accept_types;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	public String getStatus() {
		return status;
	}

}
