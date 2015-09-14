package es.sugarsoft.commodities.resources.h2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.dao.SectionDao;

@Component
public class SectionDaoImpl implements SectionDao {
	
	private ConnectionManager connectionManager;
	
	private static final String GET_BY_ID = "SELECT * FROM SECTION WHERE ID = ?";

	private static final String GET_BY_PARENT = "SELECT * FROM SECTION WHERE PARENT_ID = ?";
	
	@Autowired
	public SectionDaoImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public Section getById(long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connectionManager.getConnection().prepareStatement(GET_BY_ID);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {				
				return getSection(rs);
			}else{
				return null;
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
		return null;
		
	}

	@Override
	public List<Section> getByParentId(long parentId) {
		
		List<Section> list = new ArrayList<Section>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connectionManager.getConnection().prepareStatement(GET_BY_PARENT);
			ps.setLong(1, parentId);

			rs = ps.executeQuery();

			while (rs.next()) {				
				list.add(getSection(rs));
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

	private Section getSection(ResultSet rs) throws SQLException {
		Section section = new Section();
		section.setId(rs.getInt("ID"));
		section.setDescription(rs.getString("DESCRIPTION"));
		section.setUrl(rs.getString("URL"));
		
		return section;
	}

}
