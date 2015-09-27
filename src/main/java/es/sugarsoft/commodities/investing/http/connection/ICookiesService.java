package es.sugarsoft.commodities.investing.http.connection;

import java.util.List;
import java.util.Map;

public interface ICookiesService {

	public String getPHPSESSID();

	public String getFpros_popup();

	void setCookies(Map<String, List<String>> headers);

}
