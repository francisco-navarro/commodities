package es.sugarsoft.commodities.investing.http.connection;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface ISocketChartConnectionService {

	/**
	 * Devuelve un json con los valores de un artículo dado cierto intervalo
	 * @param id ID del artículo
	 * @param interval Intervalo en segundos
	 * @return JSON resultado
	 * @throws IOException
	 * @throws ParseException
	 */
	JSONObject getJsonData(long id, int interval) throws IOException, ParseException; 

}
