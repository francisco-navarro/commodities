package es.sugarsoft.commodities.workers.config;

import java.time.LocalTime;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sugarsoft.commodities.investing.services.ItemMasterLoaderService;
import es.sugarsoft.commodities.resources.dao.WorkerDao;
import es.sugarsoft.commodities.workers.resources.WorkerResource;

@Component
public class WorkersConfig {
	
	private static ItemMasterLoaderService itemMasterLoader;
	
	private WorkerDao workerDao;
	
	public static final String PARAMS_STRING = "PARAMS_STRING";


	@Autowired
	public void init(ItemMasterLoaderService itemMasterLoader, WorkerDao workerDao){

		WorkersConfig.itemMasterLoader = itemMasterLoader;
		this.workerDao = workerDao;
		
		try{
			int minute = getActualMinute()+1;
			

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();

			List<WorkerResource> workers = getWorkers();
			for(WorkerResource job: workers ){
				CronTrigger trigger = new CronTrigger();
				trigger.setName(job.getDescription()+ job.getParams());
				trigger.setCronExpression("0 "+minute+" * * * ?");				
				System.out.println("Register "+job.toString());
				scheduler.scheduleJob(job.getJobDetail(), trigger);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private int getActualMinute() {
		LocalTime time = LocalTime.now();		
		return time.getMinute();
	}

	private List<WorkerResource> getWorkers() {		
		return workerDao.getAllSectionWorkers();
	}

	
	public static ItemMasterLoaderService getItemMasterLoader() {
		return itemMasterLoader;
	}

	public static void setItemMasterLoader(ItemMasterLoaderService itemMasterLoader) {
		WorkersConfig.itemMasterLoader = itemMasterLoader;
	}

}
