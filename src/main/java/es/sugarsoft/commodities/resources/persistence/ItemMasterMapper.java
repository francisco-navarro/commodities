package es.sugarsoft.commodities.resources.persistence;

import es.sugarsoft.commodities.resources.Item;

public interface ItemMasterMapper {

	
	Item getById(Long id);

	void add(Item commodity, Long sectionId);
	

}
