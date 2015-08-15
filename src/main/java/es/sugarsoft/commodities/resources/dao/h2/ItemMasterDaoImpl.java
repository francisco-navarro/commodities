package es.sugarsoft.commodities.resources.dao.h2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.dao.ItemMasterDao;

@Component
public class ItemMasterDaoImpl implements ItemMasterDao {
	
	private ConnectionManager connectionManager;
	
	private static final String INSERT = "insert into ITEM_MASTER (ID,DESCRIPTION, JSON) values (?,?,?)";
	
	@Autowired
	public ItemMasterDaoImpl (ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public void add(Item commodity) {
		
		PreparedStatement ps = null;
		try {
			ps = connectionManager.getConnection().prepareStatement(INSERT);
			ps.setLong(1, commodity.getId());
			ps.setString(2 , commodity.getDescription());
			ps.setString(3, commodity.getJson());
	
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
