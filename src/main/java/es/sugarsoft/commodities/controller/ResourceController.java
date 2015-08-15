package es.sugarsoft.commodities.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.investing.services.ItemUpdaterService;

@Controller
public class ResourceController {

	private ItemMasterLoaderService itemMasterLoaderService;
	private ItemUpdaterService itemUpdaterService;

	@Autowired
	public ResourceController(ItemMasterLoaderService itemMasterLoaderService, ItemUpdaterService itemUpdaterService) {

		this.itemMasterLoaderService = itemMasterLoaderService;
		this.itemUpdaterService = itemUpdaterService;
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String list(HttpServletRequest request, @PathVariable("id") Long id) {
		
		throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
	}

	@RequestMapping(value = "/table/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String list(HttpServletRequest request, @PathVariable("name") String name) {
		
		itemMasterLoaderService.loadTableItems(name);
		return null;
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String load(HttpServletRequest request, @PathVariable("id") Long id) {

		itemUpdaterService.updateItem(id);
		return null;
	}

}
