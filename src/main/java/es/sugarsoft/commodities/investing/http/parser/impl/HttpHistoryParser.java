package es.sugarsoft.commodities.investing.http.parser.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalChartConnectionService;
import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IHttpHistoryParser;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.ItemHistory;

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
		Document doc = null;		
		try {
			String html = connection.getData(item.getId(), fromDate, endDate);
			doc = Jsoup.parse(html);
		}catch(Exception e){
			
		}
		return null;
	}

}
