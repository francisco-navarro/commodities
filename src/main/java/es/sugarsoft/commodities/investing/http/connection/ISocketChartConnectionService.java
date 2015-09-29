package es.sugarsoft.commodities.investing.http.connection;

import java.io.IOException;

public interface ISocketChartConnectionService {

	String getJsonData(long id) throws IOException;

}
