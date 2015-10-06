package es.sugarsoft.commodities.services;

import java.util.Date;
import java.util.List;

import es.sugarsoft.commodities.resources.ItemHistory;

public interface ItemHistoricalService {

	List<ItemHistory> getHistoricalTableBySection(long id);

	void loadNotStoredValues(long sectionId, Date day);

}
