package es.sugarsoft.commodities.resources.dao.h2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Value;
import es.sugarsoft.commodities.resources.dao.ItemDao;

@Component
public class ItemDaoImpl implements ItemDao {

	private ConnectionManager connectionManager;

	private static final String INSERT = "insert into ITEM (ID,DATE,VALUE) values (?,?,?)";
	private static final String LIST_BY_SECTION = "SELECT I.ID FROM ITEM_MASTER I WHERE I.SECTION_ID = ?";
	private static final String GET_VALUES = "select I.ID, VALUE, DATE,DESCRIPTION from ITEM I , ITEM_MASTER IM "
			+ "where I.ID = IM.ID AND I.ID = ? order by DATE";

	@Autowired
	public ItemDaoImpl(ConnectionManager connectionManager) {
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
			} catch (SQLException e1) {
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				connectionManager.getConnection().commit();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public Item getValues(long id, long interval) {

		Item item = new Item();
		List<Value> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connectionManager.getConnection().prepareStatement(GET_VALUES);
			ps.setLong(1, id);
			// TODO: Tener en cuenta el intervalo

			rs = ps.executeQuery();

			while (rs.next()) {
				if (item.getDescription() == null) {
					item.setDescription(rs.getString("DESCRIPTION"));
					item.setId(rs.getLong("ID"));
				}
				list.add(getNumericValue(rs));
			}
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

		item.setData(list.toArray(new Value[0]));
		return item;
	}

	@Override
	public List<Long> getValuesBySection(long idSection, long interval) {

		List<Long> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connectionManager.getConnection().prepareStatement(LIST_BY_SECTION);
			ps.setLong(1, idSection);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getLong(1));
			}
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
		
		return list;
		
	}
	
	private Value getNumericValue(ResultSet rs) throws SQLException {
		double value = rs.getDouble("VALUE");
		long time = rs.getTimestamp("DATE").getTime();
		return new Value(time, value);
	}

}
