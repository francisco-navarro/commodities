package es.sugarsoft.commodities.investing.http.parser;

import java.util.Date;
import java.util.List;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.ItemHistory;

public interface IHttpHistoryParser {


	List<ItemHistory> getItemDetails(long itemId, Date dateFrom, Date dateTo);

}