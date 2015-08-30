package es.sugarsoft.commodities.workers;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.sugarsoft.commodities.workers.config.WorkersConfig;

public class ItemMasterLoaderWorker implements Job{



	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap(); 
		String params = (String) dataMap.get(WorkersConfig.PARAMS_STRING);
		WorkersConfig.getItemMasterLoader().loadTableItemsFromSectionId(Long.valueOf(params));
	}

}
