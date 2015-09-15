package es.sugarsoft.commodities.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.resources.persistence.WorkerMapper;
import es.sugarsoft.commodities.services.WorkerService;
import es.sugarsoft.commodities.workers.resources.WorkerResource;

@Service("workerService")
public class WorkerServiceImpl implements WorkerService{
	
	@Autowired
	private WorkerMapper workerMapper;

	@Override
	public Long count() {
		return workerMapper.count();
	}

	@Override
	public List<WorkerResource> getAllSectionWorkers() {
		return workerMapper.getAllSectionWorkers();
	}
	
	

}
