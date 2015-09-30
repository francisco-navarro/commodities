package es.sugarsoft.commodities.investing.http.connection;

import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface ISocketChartConnectionService {

	JSONObject getJsonData(long id, Date fromDate, Date endDate) throws IOException, ParseException;

}
