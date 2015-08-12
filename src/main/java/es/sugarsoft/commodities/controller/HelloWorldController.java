package es.sugarsoft.commodities.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController { 

	@RequestMapping(value = "/abc", method = RequestMethod.GET,produces = "application/json" )
    public @ResponseBody String myMethod(HttpServletRequest request,
        HttpServletResponse response) {
        return "{ \"a\": 1}";
    }  
}
