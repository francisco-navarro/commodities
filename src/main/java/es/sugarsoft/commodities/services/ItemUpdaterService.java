package es.sugarsoft.commodities.services;

import es.sugarsoft.commodities.resources.Item;

/**
 * @author fjnavarrol
 * 
 * Clase que actualiza los valores de stock de los objetos
 *
 */
public interface ItemUpdaterService {

	/**
	 * Actualiza la tabla de los valores de item seg�n un id, creando una nueva
	 * conexi�n y realizando todo el proceso.
	 * @param id
	 */
	void updateItem(long id);

	/**
	 * Recoge los valores num�ricos de un item
	 * @param id
	 * @param interval
	 * @return
	 */
	Item getValues(long id, long interval);

}
