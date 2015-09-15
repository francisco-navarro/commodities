package es.sugarsoft.commodities.workers.config;

import java.time.LocalTime;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.resources.persistence.WorkerMapper;
import es.sugarsoft.commodities.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.services.WorkerService;
import es.sugarsoft.commodities.workers.resources.WorkerResource;

@Component
public class WorkersConfig {
	
	private static ItemMasterLoaderService itemMasterLoader;
	
	private WorkerService workerService;
	
	public static final String PARAMS_STRING = "PARAMS_STRING";
	
	@Autowired
	public void initTest( WorkerMapper workerDao){
		System.out.println(workerDao);
	}


	@Autowired
	public void init(ItemMasterLoaderService itemMasterLoader, WorkerService workerService){

		WorkersConfig.itemMasterLoader = itemMasterLoader;
		this.workerService = workerService;
		
		System.out.println(workerService.count());
		try{
			int minute = getActualMinute()+1;
			

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			

			List<WorkerResource> workers = getWorkers();
//			for(WorkerResource job: workers ){
//				CronTrigger trigger = new CronTrigger();
//				trigger.setName(job.getDescription()+ job.getParams());
//				trigger.setCronExpression("0 "+minute+" * * * ?");				
//				System.out.println("Register "+job.toString());
//				scheduler.scheduleJob(job.getJobDetail(), trigger);				
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private int getActualMinute() {
		LocalTime time = LocalTime.now();		
		return time.getMinute();
	}

	private List<WorkerResource> getWorkers() {		
		return workerService.getAllSectionWorkers();
	}

	
	public static ItemMasterLoaderService getItemMasterLoader() {
		return itemMasterLoader;
	}

	public static void setItemMasterLoader(ItemMasterLoaderService itemMasterLoader) {
		WorkersConfig.itemMasterLoader = itemMasterLoader;
	}

}
