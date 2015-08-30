package es.sugarsoft.commodities.resources.dao.h2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.ConnectionManager;
import es.sugarsoft.commodities.resources.dao.WorkerDao;
import es.sugarsoft.commodities.workers.resources.WorkerResource;

@Component
public class WorkerDaoImpl implements WorkerDao{
	
	private static final String GET_BY_SECTIONS = "select worker_id,class,description,params,cron_expression from WORKER w, WORKER_SECTION ws where w.ID=ws.WORKER_ID";
	private ConnectionManager connectionManager;
	
	
	@Autowired
	public WorkerDaoImpl(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	@Override
	public List<WorkerResource> getAllSectionWorkers(){
		List<WorkerResource> list = new ArrayList<>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		try {
			ps = connectionManager.getConnection().prepareStatement(GET_BY_SECTIONS);
			rs = ps.executeQuery();

			while (rs.next()) {
				WorkerResource worker = new WorkerResource();
				worker.setWorkerId(rs.getLong("worker_id"));
				worker.setClassname( rs.getString("class"));
				worker.setDescription( rs.getString("description"));
				worker.setParams( rs.getString("params"));
				worker.setCronExpression(rs.getString("cron_expression"));
				list.add(worker);
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

}
