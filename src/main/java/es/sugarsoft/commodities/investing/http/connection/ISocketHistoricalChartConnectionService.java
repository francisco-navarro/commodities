package es.sugarsoft.commodities.investing.http.connection;

import java.util.Date;

public interface ISocketHistoricalChartConnectionService {

	String getData(long id, Date fromDate, Date endDate);

}
