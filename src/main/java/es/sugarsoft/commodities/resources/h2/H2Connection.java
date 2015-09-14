package es.sugarsoft.commodities.resources.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import es.sugarsoft.commodities.resources.ConnectionManager;


public class H2Connection implements ConnectionManager{

	private Connection connection;

	private String url = "jdbc:h2:tcp://localhost/~/test";

	private String userName = "sa";

	private String password = "";
	
	


	public H2Connection() throws Exception {
		Class.forName("org.h2.Driver");
		Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
		connection = DriverManager.getConnection(url, connectionProps);
	}

	@Override
	public Connection getConnection() {

		return connection;
	}

}
