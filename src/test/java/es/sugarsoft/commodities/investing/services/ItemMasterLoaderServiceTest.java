package es.sugarsoft.commodities.investing.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;


import org.junit.Before;
import org.junit.Test;
import org.mockito.verification.VerificationMode;

import es.sugarsoft.commodities.investing.services.impl.ItemMasterLoaderServiceImpl;
import es.sugarsoft.commodities.resources.Item;
import es.sugarsoft.commodities.resources.Section;
import es.sugarsoft.commodities.resources.persistence.ItemMasterDao;

public class ItemMasterLoaderServiceTest {
	
	private ItemMasterLoaderService itemMasterLoaderService;
	
	private ItemMasterDao itemMasterDao = mock(ItemMasterDao.class);
	private SectionService sectionService = mock(SectionService.class);
	private ItemUpdaterService itemUpdaterService = mock(ItemUpdaterService.class);
	
	@Before
	public void init(){		
		itemMasterLoaderService = new ItemMasterLoaderServiceImpl(itemMasterDao, sectionService, itemUpdaterService);
	}
	
	@Test
	public void shouldRetrieveListOfItems(){
		final long id = 3l;
		Section section = new Section();
		section.setUrl("http://es.investing.com/commodities/metales");
		when(sectionService.get(id)).thenReturn(section);
		itemMasterLoaderService.loadTableItemsFromSectionId(id);		
		verify(itemMasterDao,atLeastOnce()).add(any(Item.class), anyLong());
	}

}
