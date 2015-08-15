package es.sugarsoft.commodities.investing.services;

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
	void updateItem(Long id);

}
