package es.sugarsoft.commodities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.services.SectionService;
import es.sugarsoft.commodities.resources.Section;

@Controller
public class SectionController {
	
	private SectionService sectionService;

	@Autowired
	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@ResponseBody
	@RequestMapping(value = "/sections/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Section> getById(
			@PathVariable("id") Integer id){
		
		return sectionService.getByParentId(id);			
	}
	

}
