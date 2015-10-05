package es.sugarsoft.commodities.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.persistence.ItemHistoryMapper;
import es.sugarsoft.commodities.resources.persistence.ItemMapper;
import es.sugarsoft.commodities.resources.persistence.SectionMapper;
import es.sugarsoft.commodities.services.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	
	private SectionMapper sectionDao;
	private ItemHistoryMapper itemHistoryMapper;
	private ItemMapper itemDao;
	
	private static final long INTERVAL = 1l;
	
	@Autowired
	public SectionServiceImpl(SectionMapper sectionDao,
			ItemMapper itemDao,
			ItemHistoryMapper itemHistoryMapper) {
		super();
		this.sectionDao = sectionDao;
		this.itemDao = itemDao;
		this.itemHistoryMapper = itemHistoryMapper;
	}


	@Override
	public List<Section> getByParentId(Integer id) {
		List<Section> list = sectionDao.getByParentId(id);
		for(Section section : list ){
			section.setResources(itemDao.getValuesBySection(section.getId(), INTERVAL));
		}
		return list;
	}


	@Override
	public Section get(long id) {
		Section section = sectionDao.getById(id);
		section.setHistoricalTable(itemHistoryMapper.getHistoricalTableBySection(id));
		return section;
	}

}
