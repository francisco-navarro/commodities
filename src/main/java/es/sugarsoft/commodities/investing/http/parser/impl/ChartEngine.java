package es.sugarsoft.commodities.investing.http.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.IHtmlConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IChartEngine;
import es.sugarsoft.commodities.investing.http.util.UriConstants;

@Service("chartEngine")
public class ChartEngine implements IChartEngine {
	
	
	private IHtmlConnectionService htmlConnectionService;
	
	@Autowired
	public ChartEngine(IHtmlConnectionService htmlConnectionService) throws Exception{
		this.htmlConnectionService = htmlConnectionService;
	}
	
	@Override
	public String getJson(long id) throws Exception{
		return htmlConnectionService.connect(UriConstants.CHART_URL.replace("###", Long.toString(id)));
	}
	
}
