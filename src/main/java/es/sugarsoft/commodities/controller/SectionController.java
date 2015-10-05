package es.sugarsoft.commodities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.services.SectionService;

@Controller
public class SectionController {
	
	private SectionService sectionService;
	private ItemMasterLoaderService itemMasterLoaderService;
	

	@Autowired
	public SectionController(SectionService sectionService,
			 ItemMasterLoaderService itemMasterLoaderService) {
		this.sectionService = sectionService;
		this.itemMasterLoaderService = itemMasterLoaderService;
	}

	@ResponseBody
	@RequestMapping(value = "/sections/{id}", method = RequestMethod.GET, produces = "application/json")
	public Section getById(
			@PathVariable("id") Integer id){		
		return sectionService.get(id);			
	}
	
	@ResponseBody
	@RequestMapping(value = "/sections", method = RequestMethod.GET, produces = "application/json")
	public List<Section> getByParentId(
			@RequestParam(value = "parentId", required = true) int parentId){
		return sectionService.getByParentId(parentId);				
	}
	
	@ResponseBody
	@RequestMapping(value = "/sections/{id}", method = RequestMethod.PUT, produces = "application/json")
	public void loadTableItems(
			@PathVariable("id") Integer id){
		
		itemMasterLoaderService.loadTableItemsFromSectionId(id);		
	}

}
