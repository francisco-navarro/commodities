package es.sugarsoft.commodities.resources.persistence;

import org.apache.ibatis.annotations.Param;

import es.sugarsoft.commodities.resources.Item;

public interface ItemMasterMapper {

	
	Item getById(@Param("id")Long id);

	void add(@Param("commodity")Item commodity, @Param("sectionId")Long sectionId);
	

}
