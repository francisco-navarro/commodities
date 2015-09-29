package es.sugarsoft.commodities.investing.http.parser;

import es.sugarsoft.commodities.resources.Item;

public interface IHttpDetailParser {

	Item getItemDetails(Item item);

}