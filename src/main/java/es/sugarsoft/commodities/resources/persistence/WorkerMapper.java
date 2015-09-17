package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import es.sugarsoft.commodities.workers.resources.WorkerResource;

public interface WorkerMapper extends MyMapper {
	
	List<WorkerResource> getAllSectionWorkers();
	
	Long count();

}
