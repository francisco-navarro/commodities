package es.sugarsoft.commodities.resources.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.sugarsoft.commodities.resources.ItemHistory;

public interface ItemHistoryMapper {

	List<ItemHistory> getHistoricalTableBySection(long id);
	
	List<ItemHistory> getNotStoredValues(@Param("id")long id, @Param("date")Date date);

	void create(ItemHistory item);

}
