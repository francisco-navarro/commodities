package es.sugarsoft.commodities.resources.h2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Value;
import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.dao.ItemMasterDao;

@Component
public class ItemMasterDaoImpl implements ItemMasterDao {
	
	private ConnectionManager connectionManager;
	
	private static final String INSERT = "insert into ITEM_MASTER (ID,DESCRIPTION, JSON, SECTION_ID) values (?,?,?,?)";
	private static final String GET = "select * FROM ITEM_MASTER WHERE ID = ?";
	
	@Autowired
	public ItemMasterDaoImpl (ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public void add(Item commodity, long sectionId) {

		if(getById(commodity.getId())==null){
			PreparedStatement ps = null;
			try {
				ps = connectionManager.getConnection().prepareStatement(INSERT);
				ps.setLong(1, commodity.getId());
				ps.setString(2 , commodity.getDescription());
				ps.setString(3, commodity.getJson());
				ps.setLong(4, sectionId);

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

	@Override
	public Item getById(long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connectionManager.getConnection().prepareStatement(GET);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();

			if (rs.next()) {
				Item item = new Item();
				item.setId(rs.getLong("ID"));
				item.setDescription(rs.getString("DESCRIPTION"));
				return item;
			}
			return null;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connectionManager.getConnection().rollback();
			} catch (SQLException e1) {
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		return null;
	}
	
	

}
