package es.sugarsoft.commodities.resources.dao.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.dao.ItemDao;

@Component
public class ItemDaoImpl implements ItemDao {
	
	private ConnectionManager connectionManager;
	
	@Autowired
	public ItemDaoImpl (ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}
	
	

}
