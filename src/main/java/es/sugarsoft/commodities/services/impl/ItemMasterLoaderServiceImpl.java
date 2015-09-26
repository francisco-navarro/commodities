package es.sugarsoft.commodities.services.impl;

import java.net.URLEncoder;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.HttpTableParser;
import es.sugarsoft.commodities.investing.http.connection.impl.HtmlConnectionService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.persistence.ItemMasterMapper;
import es.sugarsoft.commodities.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.services.ItemUpdaterService;
import es.sugarsoft.commodities.services.SectionService;

@SuppressWarnings("rawtypes")
@Service("itemMasterLoaderService")
public class ItemMasterLoaderServiceImpl implements ItemMasterLoaderService {

	private static final String MAIN_TABLE = "#dailyTab > tbody > tr";
	private static final String SECOND_TABLE = "#cross_rate_1 > tbody > tr";
	
	private SectionService sectionService;
	private ItemMasterMapper itemMasterDao;
	private ItemUpdaterService itemUpdaterService;
	private HttpTableParser httpTableParser = new HttpTableParser();

	@Autowired
	public ItemMasterLoaderServiceImpl(ItemMasterMapper itemMasterDao,
			SectionService sectionService,
			ItemUpdaterService itemUpdaterService) {

		this.itemMasterDao = itemMasterDao;
		this.sectionService = sectionService;
		this.itemUpdaterService = itemUpdaterService;
	}

	@Override
	@Deprecated
	public void loadTableItems(String market, String table) {

		HtmlConnectionService connection = null;
		

		try {
			String url = HtmlConnectionService.getTableUri(market + "/" + URLEncoder.encode(table, "UTF-8"));

			connection = new HtmlConnectionService(url);			
			List<Item> list = httpTableParser.getItems(connection.getOutput());

			for (Item commodity : list) {
				itemMasterDao.add(commodity, 2l);
				itemUpdaterService.updateItem(commodity.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void  loadTableItemsFromSectionId(long id){

		HtmlConnectionService connection = null;
		
		try {
			String url = getUriFromSectionId(id);

			connection = new HtmlConnectionService(url);
			List<Item> list = httpTableParser.getItems(connection.getOutput());

			for (Item commodity : list) {
				itemMasterDao.add(commodity,id);
				itemUpdaterService.updateItem(commodity.getId());
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
