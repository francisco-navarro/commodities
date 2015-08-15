package es.sugarsoft.commodities.investing.services;

/**
 * @author fjnavarrol
 * 
 * Clase que actualiza los valores de stock de los objetos
 *
 */
public interface ItemUpdaterService {

	/**
	 * Actualiza la tabla de los valores de item según un id, creando una nueva
	 * conexión y realizando todo el proceso.
	 * @param id
	 */
	void updateItem(Long id);

}
