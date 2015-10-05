package es.sugarsoft.commodities.resources;

import java.io.Serializable;
import java.util.List;

public class Section implements Serializable {
	
	private Integer id;
	private Integer parentId;
	private String description;
	private String url;
	private List<Long> resources;
	private List<ItemHistory> historicalTable;
	
	public Section(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Long> getResources() {
		return resources;
	}
	public void setResources(List<Long> resources) {
		this.resources = resources;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public List<ItemHistory> getHistoricalTable() {
		return historicalTable;
	}

	public void setHistoricalTable(List<ItemHistory> historicalTable) {
		this.historicalTable = historicalTable;
	}
	

}
