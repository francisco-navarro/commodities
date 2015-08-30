package es.sugarsoft.commodities.investing.services;

import java.util.List;

import es.sugarsoft.commodities.resources.Section;

public interface SectionService {

	List<Section> getByParentId(Integer id);

	Section get(long id);

}
