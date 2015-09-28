package es.sugarsoft.commodities.investing.http.connection;

public interface IHtmlConnectionService {

	String getTableUri(String table);

	String connect(String inputUrl) throws Exception;

}
