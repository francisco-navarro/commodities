package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import es.sugarsoft.commodities.resources.ItemHistory;

public interface ItemHistoryMapper {

	List<ItemHistory> getHistoricalTableBySection(long id);

}
