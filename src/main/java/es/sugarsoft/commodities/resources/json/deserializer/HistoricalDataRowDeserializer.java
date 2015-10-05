package es.sugarsoft.commodities.resources.json.deserializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.sugarsoft.commodities.resources.ItemHistory;

public class HistoricalDataRowDeserializer {
	
//0	<th class="first left noWrap">Fecha</th>
//1 <th class="noWrap">Último</th>
//2 <th class="noWrap">Apertura</th>
//3 <th class="noWrap">Máximo</th>
//4 <th class="noWrap">Mínimo</th>
//5	<th class="noWrap">Vol.</th>            
//6	<th class="noWrap">Var. %</th>

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	public static ItemHistory deserialize(long id, Element element) throws ParseException, NumberFormatException {
		ItemHistory row = new ItemHistory();		
		Elements cols = element.select("td");
		
		row.setItemId(id);
		row.setDay(getDay(cols.get(0)));
		row.setCloseValue(Float.valueOf(cols.get(1).text()));
		row.setOpenValue(Float.valueOf(cols.get(2).text()));
		row.setMax(Float.valueOf(cols.get(3).text()));
		row.setMin(Float.valueOf(cols.get(4).text()));
		
		return row;
	}

	private static Date getDay(Element element) throws ParseException {		
		return sdf.parse(element.text());
	}
	
}
