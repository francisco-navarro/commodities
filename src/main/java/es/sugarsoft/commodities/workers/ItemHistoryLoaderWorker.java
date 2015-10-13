package es.sugarsoft.commodities.workers;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ItemHistoryLoaderWorker implements Job{
	
	private static final Date today = new Date();
	private static final int minPrevDaysCount = 365;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//Hacer ItemHistoricalService.loadNotStoredValues() dia por dia
		
	}


}
