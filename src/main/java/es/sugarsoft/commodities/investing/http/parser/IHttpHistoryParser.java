package es.sugarsoft.commodities.investing.http.parser;

import es.sugarsoft.commodities.resources.Item;

public interface IHttpHistoryParser {

	Item getItemDetails(Item item);

}