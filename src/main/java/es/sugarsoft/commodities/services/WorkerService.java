package es.sugarsoft.commodities.services;

import java.util.List;

import es.sugarsoft.commodities.workers.resources.WorkerResource;

public interface WorkerService {
	
	public Long count();

	public List<WorkerResource> getAllSectionWorkers();

}
