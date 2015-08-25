package es.sugarsoft.commodities.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;

@Controller
public class TableController {
	
	private ItemMasterLoaderService itemMasterLoaderService;

	@Autowired
	public TableController(ItemMasterLoaderService itemMasterLoaderService) {
		this.itemMasterLoaderService = itemMasterLoaderService;
	}
	

	@RequestMapping(value = "/table/commodities/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String updateItemMasterByCommodity(HttpServletRequest request, @PathVariable("name") String name) {
		
		itemMasterLoaderService.loadTableItems("commodities", name);
		return null;
	}
	

}
