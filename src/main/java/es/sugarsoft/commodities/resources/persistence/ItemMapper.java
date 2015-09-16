package es.sugarsoft.commodities.resources.persistence;

import java.util.List;

import es.sugarsoft.commodities.resources.Item;

public interface ItemMapper {

	public void add(long pairId, long time, Double qty);

	public Item getValues(long id, long interval);

	public List<Long> getValuesBySection(long idSection, long interval);

}
