package es.sugarsoft.commodities.resources.jndi;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service("connectionManager")
public class JNDIConnection implements ConnectionManager{

	private Connection connection;

	private String resource = "jdbc/commodities";

	private String userName = "sa";

	private String password = "";
	
	


	public JNDIConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup(resource);
		connection = ds.getConnection();
	}

	@Override
	public Connection getConnection() {

		return connection;
	}

}
