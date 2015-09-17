package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.sugarsoft.commodities.resources.Section;

public interface SectionMapper {
	
	public Section getById(@Param("id")long id);
	
	public List<Section> getByParentId(@Param("parentId")long parentId);

}
