package es.sugarsoft.commodities.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.investing.services.ItemUpdaterService;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.json.PageResource;

@Controller
public class ResourceController {

	private static final Long DEFAULT_INTERVAL = 1800l;
	private ItemMasterLoaderService itemMasterLoaderService;
	private ItemUpdaterService itemUpdaterService;

	@Autowired
	public ResourceController(ItemMasterLoaderService itemMasterLoaderService, ItemUpdaterService itemUpdaterService) {

		this.itemMasterLoaderService = itemMasterLoaderService;
		this.itemUpdaterService = itemUpdaterService;
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PageResource list(
			HttpServletRequest request, 
			@PathVariable("id") Long id,
			@RequestParam(value="interval", required=false) Long interval) {
		
		PageResource page = new PageResource();
		if(interval == null){
			interval = DEFAULT_INTERVAL;
		}
		
		try{
			
			page.setElements(new Item[]{itemUpdaterService.getValues(id,interval)});
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}

	@RequestMapping(value = "/table/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String updateItemMasterByTable(HttpServletRequest request, @PathVariable("name") String name) {
		
		itemMasterLoaderService.loadTableItems(name);
		return null;
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String updateItemValues(HttpServletRequest request, @PathVariable("id") Long id) {

		itemUpdaterService.updateItem(id);
		return null;
	}

}
