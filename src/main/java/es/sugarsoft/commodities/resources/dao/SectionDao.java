package es.sugarsoft.commodities.resources.dao;

import java.util.List;

import es.sugarsoft.commodities.resources.Section;

public interface SectionDao {
	
	public Section getById();
	
	public List<Section> getByParentId(int parentId);

}
