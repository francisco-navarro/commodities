package es.sugarsoft.commodities.services;

/**
 * @author fjnavarrol
 *
 *	Clase que recorre y carga los metadatos de los items
 */
public interface ItemMasterLoaderService {

	/**
	 * Dada una uri, recorre la tabla en busca de sus elementos
	 * La uri de la tabla está guardada en bbdd
	 * @param id de la seccion
	 */
	void loadTableItemsFromSectionId(long id);

}
