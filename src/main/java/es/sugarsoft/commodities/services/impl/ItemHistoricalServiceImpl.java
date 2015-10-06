package es.sugarsoft.commodities.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sugarsoft.commodities.investing.http.connection.ISocketHistoricalConnectionService;
import es.sugarsoft.commodities.investing.http.parser.IHttpHistoryParser;
import es.sugarsoft.commodities.resources.ItemHistory;
import es.sugarsoft.commodities.resources.persistence.ItemHistoryMapper;
import es.sugarsoft.commodities.services.ItemHistoricalService;

@Service("ItemHistoricalService")
public class ItemHistoricalServiceImpl implements ItemHistoricalService{
	
	private ItemHistoryMapper itemHistoryMapper;
	private IHttpHistoryParser httpHistoryParser;
	
	private static final Logger logger = Logger.getLogger(ItemHistoricalServiceImpl.class);

	@Autowired
	public ItemHistoricalServiceImpl(ItemHistoryMapper itemHistoryMapper,
			IHttpHistoryParser httpHistoryParser) {
		this.itemHistoryMapper = itemHistoryMapper;
		this.httpHistoryParser = httpHistoryParser;
	}

	@Override
	public List<ItemHistory> getHistoricalTableBySection(long sectionId) {
		Date yesterday = getYesterday(); 
		loadNotStoredValues(sectionId, yesterday);
		return itemHistoryMapper.getHistoricalTableBySection(sectionId);
	}

	@Override
	public void loadNotStoredValues(long sectionId, Date day) {
		Date endDate = getPreviousDays(7, day);
		List<ItemHistory> notStored = itemHistoryMapper.getNotStoredValues(sectionId, day);
		for( ItemHistory item : notStored){
			List<ItemHistory> rows = httpHistoryParser.getItemDetails(item.getItemId(), day, endDate);
			if(rows != null){
				insertItemHistoryList(rows);
			}
		}
	}
	
	private void insertItemHistoryList(List<ItemHistory> rows) {
		for( ItemHistory item : rows){
			try{
				itemHistoryMapper.create(item);
			}catch(Exception e){
				logger.warn("Error insertando en historico "+item);
			}
		}
	}

	private Date getYesterday(){
		return getPreviousDays(1,new Date());
	}

	private Date getPreviousDays(int n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1*n);
		return cal.getTime();
	}
	
	
}
