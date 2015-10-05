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
	 * Actualiza la tabla de los valores de item segun un id, creando una nueva
	 * conexion y realizando todo el proceso.
	 * @param id
	 */
	void updateItem(long id, int interval);

	/**
	 * Recoge los valores numericos de un item
	 * @param id
	 * @param interval
	 * @return
	 */
	Item getValues(long id, long interval);

	

}
