package es.sugarsoft.commodities.investing.http.parser;

import java.util.List;

import es.sugarsoft.commodities.resources.Item;

public interface IHttpTableParser {

	List<Item> getItemsFromTableUrl(String html);

	
}