package es.sugarsoft.commodities.resources.dao;

import java.util.List;

import es.sugarsoft.commodities.workers.resources.WorkerResource;

public interface WorkerDao {
	
	public List<WorkerResource> getAllSectionWorkers();

}
