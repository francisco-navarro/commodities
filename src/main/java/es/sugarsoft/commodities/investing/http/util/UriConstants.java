package es.sugarsoft.commodities.investing.http.util;

public class UriConstants {
	
	public static final String DOMAIN = new String(new byte[] {105, 110, 118, 101, 115, 116, 105, 110, 103, 46, 99, 111, 109});
	public static final String CHART_URL = "http://sbcharts." + DOMAIN + "/charts_xml/jschart_sideblock_###_area.json";
	
	public static final String SECTION_URL ="http://es." + DOMAIN + "/";
	
	public static String getTableUri(String table){
		return SECTION_URL+table;
	}

}
