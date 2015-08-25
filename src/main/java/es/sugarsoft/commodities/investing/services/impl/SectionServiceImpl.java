package es.sugarsoft.commodities.investing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.services.SectionService;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.dao.ItemDao;
import es.sugarsoft.commodities.resources.dao.SectionDao;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	
	private SectionDao sectionDao;
	private ItemDao itemDao;
	
	private static final long INTERVAL = 1l;
	
	@Autowired
	public SectionServiceImpl(SectionDao sectionDao,
			ItemDao itemDao) {
		super();
		this.sectionDao = sectionDao;
		this.itemDao = itemDao;
	}


	@Override
	public List<Section> getByParentId(Integer id) {
		List<Section> list = sectionDao.getByParentId(id);
		for(Section section : list ){
			section.setResources(itemDao.getValuesBySection(section.getId(), INTERVAL));
		}
		return list;
	}

}
