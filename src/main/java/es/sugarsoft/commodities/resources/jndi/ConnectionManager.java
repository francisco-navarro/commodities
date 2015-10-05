package es.sugarsoft.commodities.resources.jndi;

import java.sql.Connection;

public interface ConnectionManager {
	
	public Connection getConnection();
	
}
