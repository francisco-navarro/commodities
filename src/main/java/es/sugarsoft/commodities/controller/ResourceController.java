package es.sugarsoft.commodities.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sugarsoft.commodities.investing.ChartEngine;

@Controller
public class ResourceController {

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET,produces = "application/json" )
    public @ResponseBody String myMethod( HttpServletRequest request,
    		 @PathVariable("id") Long id) {
		try {
			ChartEngine chart = new ChartEngine(id.toString());			
			return chart.getJson();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
    }  
}
