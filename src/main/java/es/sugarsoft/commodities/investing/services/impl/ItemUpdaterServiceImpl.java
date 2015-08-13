package es.sugarsoft.commodities.investing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.resources.dao.ItemDao;

@Service("itemUpdaterService")
public class ItemUpdaterServiceImpl {
	
	private ItemDao itemDao;
	
	@Autowired
	public ItemUpdaterServiceImpl(ItemDao itemDao){
		this.itemDao = itemDao;
	}

}
