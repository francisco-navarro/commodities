package es.sugarsoft.commodities.investing.http.parser.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalChartConnectionService;
import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IHttpHistoryParser;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.ItemHistory;
import es.sugarsoft.commodities.resources.json.deserializer.HistoricalDataRowDeserializer;

@Service("httpHistoryParser")
public class HttpHistoryParser implements IHttpHistoryParser {

	private static final Logger logger = Logger.getLogger(HtmlConnectionService.class);
	private static final SimpleDateFormat monthParser = new SimpleDateFormat("MMM dd",Locale.ENGLISH);

	private ISocketHistoricalChartConnectionService connection;
	
	@Autowired
	public HttpHistoryParser(ISocketHistoricalChartConnectionService connection){
		this.connection = connection;
	}
	
	@Override
	public List<ItemHistory> getItemDetails(Item item, Date fromDate, Date endDate) {
		
		String html = null;
		try {
			html = connection.getData(item.getId(), fromDate, endDate);
			Document doc = Jsoup.parse(html);
			Elements rows = doc.select(".historicalTbl > tbody > tr");
			return parseRows(item.getId(), rows);
		}catch(Exception e){
			logger.error("Error leyendo html:\n"+html,e);
		}
		return null;
	}

	private List<ItemHistory> parseRows(long itemId, Elements rows) {
		
		List<ItemHistory> list = new ArrayList<>();
		
		for(int i=0; i<rows.size(); i++){
			try{
			ItemHistory item = HistoricalDataRowDeserializer.deserialize(itemId, rows.get(i));
			list.add(item);
			}catch(Exception e){
				logger.warn("Error parsing "+rows.get(i));
			}
		}
		
		return list;
	}

}
