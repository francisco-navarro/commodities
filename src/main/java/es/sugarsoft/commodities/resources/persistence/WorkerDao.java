package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import es.sugarsoft.commodities.workers.resources.WorkerResource;

public interface WorkerDao {
	
	List<WorkerResource> getAllSectionWorkers();

}