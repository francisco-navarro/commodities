package es.sugarsoft.commodities.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommoditiesServlet extends HttpServlet {
	
//	@Autowired
//	private ItemUpdaterService itemUpdaterService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		super.doGet(req, resp);
	}
//
//	public void setItemUpdaterService(ItemUpdaterService itemUpdaterService) {
//		this.itemUpdaterService = itemUpdaterService;
//	}

	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
	}
	
	

}
