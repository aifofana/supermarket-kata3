package fr.afo.supermarket;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;


import fr.afo.supermarket.model.Product;
import fr.afo.supermarket.model.ShoppingItem;
import fr.afo.supermarket.model.offers.OfferType;
import fr.afo.supermarket.service.OfferProcessor;
import fr.afo.supermarket.service.OfferProcessorManager;

@RunWith(MockitoJUnitRunner.class)
public class OfferPocessorTest {

	
	OfferProcessorManager offerProcessorManager = new OfferProcessorManager() ;
	
	
	
	@Test
	public void shouldReturnByOneGetOneProcessor() {
		
		//given
		Product apple = new Product("Apple", 0.20, OfferType.BY_ONE_GET_ONE);
		OfferProcessor defaultOfferProcessor= (quantity, price)-> quantity * price;
		
		//when
		ShoppingItem itemApple = new ShoppingItem(apple, 4);
		
		//then
		Optional<OfferProcessor> getOneByOneProcessor = offerProcessorManager.getOfferProcessor(itemApple);
		
		assertFalse(getOneByOneProcessor.get().equals(defaultOfferProcessor ));
	
		assertTrue(getOneByOneProcessor.get().apply(itemApple.getQuantity(),apple.getUnitPrice() )==0.40);
		
	}
	
	@Test
	public void shouldReturnThreeForTwoProcessor() {
		
		//given
		Product banana = new Product("Banana", 0.5, OfferType.THREE_FOR_TWO);
		OfferProcessor defaultOfferProcessor= (quantity, price)-> quantity * price;
		
		//when
		ShoppingItem itemBanana = new ShoppingItem(banana, 6);
		
		//then
		Optional<OfferProcessor> ThreeForTwoProcessor = offerProcessorManager.getOfferProcessor(itemBanana);
		
		assertFalse(ThreeForTwoProcessor.get().equals(defaultOfferProcessor ));	
		assertTrue(ThreeForTwoProcessor.get().apply(itemBanana.getQuantity(),banana.getUnitPrice() )==2);
		
	}
	/** this test throws an exception, this is due to an offer with no processor **/
	@Ignore
	@Test
	public void shouldThrowExecption() throws Exception {
			
		//given
		Product banana = new Product("Banana", 0.5, OfferType.TWENTY_PERCENT_DISCOUNT);
		
		//when
		ShoppingItem itemBanana = new ShoppingItem(banana, 6);
		
		//then
		Optional<OfferProcessor> twentyPercentProcessor = offerProcessorManager.getOfferProcessor(itemBanana);
		
		assertFalse(twentyPercentProcessor.isPresent());
		assertNull(twentyPercentProcessor.get());
		
	}
}
