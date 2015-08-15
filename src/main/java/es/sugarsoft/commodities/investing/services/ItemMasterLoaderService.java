package es.sugarsoft.commodities.investing.services;

/**
 * @author fjnavarrol
 *
 *	Clase que recorre y carga los metadatos de los items
 */
public interface ItemMasterLoaderService {

	/**
	 * Dada una uri, recorre la tabla en busca de sus elementos
	 * @param uri
	 */
	void loadTableItems(String uri);

}
