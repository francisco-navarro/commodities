package es.sugarsoft.commodities.resources.json;

import es.sugarsoft.commodities.resources.Item;

//@JsonSerialize(using=PageResourceSerializer.class)
public class PageResource {
	
	
	private long totalItems;
	private Item[] elements;
	

	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public Item[] getElements() {
		return elements;
	}
	public void setElements(Item[] elements) {
		this.elements = elements;
	}

}
