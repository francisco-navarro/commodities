package es.sugarsoft.commodities.workers.resources;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import es.sugarsoft.commodities.workers.ItemMasterLoaderWorker;
import es.sugarsoft.commodities.workers.config.WorkersConfig;

public class WorkerResource {
	
	private long workerId;
	private String classname;
	private String description;
	private String params;
	private String cronExpression;
	
	public long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(long workerId) {
		this.workerId = workerId;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public JobDetail getJobDetail() {

		try{
			JobDetail job = new JobDetail();
			job = new JobDetail();
			job .setName(description+params);
			job.setJobClass(getWorkerClass());
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(WorkersConfig.PARAMS_STRING, params);
			job.setJobDataMap(jobDataMap );
			return job;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private Class<?> getWorkerClass() throws ClassNotFoundException {
		return Class.forName(classname);
	}
	@Override
	public String toString() {
		return "WorkerResource [classname=" + classname + ", description=" + description + ", params=" + params + "]";
	}
	
	

}
