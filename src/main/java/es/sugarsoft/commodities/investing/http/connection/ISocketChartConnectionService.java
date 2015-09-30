package es.sugarsoft.commodities.investing.http.connection;

import java.io.IOException;
import java.util.Date;

import es.sugarsoft.commodities.investing.http.connection.impl.SocketChartConnectionService.Json;

public interface ISocketChartConnectionService {

	String getJsonData(long id, Date fromDate, Date endDate) throws IOException;

}
