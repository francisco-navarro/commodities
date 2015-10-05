package es.sugarsoft.commodities.services.impl;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IHttpTableParser;
import es.sugarsoft.commodities.investing.http.util.UriConstants;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.persistence.ItemMasterMapper;
import es.sugarsoft.commodities.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.services.ItemUpdaterService;
import es.sugarsoft.commodities.services.SectionService;

@SuppressWarnings("rawtypes")
@Service("itemMasterLoaderService")
public class ItemMasterLoaderServiceImpl implements ItemMasterLoaderService {

//	private static final String MAIN_TABLE = "#dailyTab > tbody > tr";
//	private static final String SECOND_TABLE = "#cross_rate_1 > tbody > tr";
	
	private SectionService sectionService;
	private ItemMasterMapper itemMasterDao;
	private ItemUpdaterService itemUpdaterService;
	private IHttpTableParser httpTableParser;

	@Autowired
	public ItemMasterLoaderServiceImpl(ItemMasterMapper itemMasterDao,
			SectionService sectionService,
			ItemUpdaterService itemUpdaterService,
			IHttpTableParser httpTableParser) {

		this.itemMasterDao = itemMasterDao;
		this.sectionService = sectionService;
		this.itemUpdaterService = itemUpdaterService;
		this.httpTableParser = httpTableParser;
	}

	
	@Override
	public void  loadTableItemsFromSectionId(long id){

		try {
			String url = getUriFromSectionId(id);
			List<Item> list = httpTableParser.getItemsFromTableUrl(url);

			for (Item commodity : list) {
				itemMasterDao.add(commodity,id);
				itemUpdaterService.updateItem(commodity.getId(),1800);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getUriFromSectionId(long id) {
		Section section = sectionService.get(id);
		return section.getUrl();
	}


}
