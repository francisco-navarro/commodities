package es.sugarsoft.commodities.resources.persistence;

import es.sugarsoft.commodities.resources.Item;

public interface ItemMasterDao {

	
	Item getById(long id);

	void add(Item commodity, long sectionId);
	

}
