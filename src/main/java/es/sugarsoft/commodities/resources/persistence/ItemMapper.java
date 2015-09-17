package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.sugarsoft.commodities.resources.Item;

public interface ItemMapper {

	public void add(@Param("pairId")long pairId, @Param("time")long time, @Param("qty")Double qty);

	public Item getValues(@Param("id")long id, @Param("interval")long interval);

	public List<Long> getValuesBySection(@Param("idSection")long idSection, @Param("interval")long interval);

}
