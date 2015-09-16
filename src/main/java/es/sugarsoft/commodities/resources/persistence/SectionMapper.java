package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import es.sugarsoft.commodities.resources.Section;

public interface SectionMapper {
	
	public Section getById(long id);
	
	public List<Section> getByParentId(long parentId);

}