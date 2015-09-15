package es.sugarsoft.commodities.services;

/**
 * @author fjnavarrol
 *
 *	Clase que recorre y carga los metadatos de los items
 */
public interface ItemMasterLoaderService {

	/**
	 * Dada una parte de uri, recorre la tabla en busca de sus elementos
	 * @param uri
	 */
	void loadTableItems(String market, String table);

	/**
	 * Dada una uri, recorre la tabla en busca de sus elementos
	 * @param uri
	 */
	void loadTableItemsFromSectionId(long id);

}
