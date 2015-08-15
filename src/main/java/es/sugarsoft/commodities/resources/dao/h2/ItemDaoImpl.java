package es.sugarsoft.commodities.resources.dao.h2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.dao.ItemDao;

@Component
public class ItemDaoImpl implements ItemDao {
	
	private ConnectionManager connectionManager;
	
	private static final String INSERT = "insert into ITEM (ID,DATE,VALUE) values (?,?,?)";
	
	@Autowired
	public ItemDaoImpl (ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public void add(long pairId, long time, Double qty) {
		
		PreparedStatement ps = null;
		try {
			ps = connectionManager.getConnection().prepareStatement(INSERT);
			ps.setLong(1, pairId);
			ps.setTimestamp(2, new java.sql.Timestamp(time));
			ps.setDouble(3, qty);
	
			ps.executeUpdate();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connectionManager.getConnection().rollback();
			} catch (SQLException e1) {}
		}finally{			
			try {
				if(ps!=null){
					ps.close();
				}
				connectionManager.getConnection().commit();
			} catch (SQLException e) {}
		}
	}
	
	

}
