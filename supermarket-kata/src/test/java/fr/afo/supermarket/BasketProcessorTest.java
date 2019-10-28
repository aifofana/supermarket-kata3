package fr.afo.supermarket;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.Ignore;

import fr.afo.supermarket.model.Product;
import fr.afo.supermarket.model.ShoppingBasket;
import fr.afo.supermarket.model.ShoppingItem;
import fr.afo.supermarket.model.offers.OfferType;
import fr.afo.supermarket.service.BasketProcessor;
import fr.afo.supermarket.service.OfferProcessorManager;

@RunWith(MockitoJUnitRunner.class)
public class BasketProcessorTest {

	@Mock
	OfferProcessorManager offerProcessorManager;
	
    private BasketProcessor basketProcessor = new BasketProcessor();
    final ShoppingBasket shoppingBasket = new ShoppingBasket();
    
    private void initData() {
	      
	    final Product Apple = new Product("Apple", 0.20, OfferType.BY_ONE_GET_ONE);
	 	final Product Orange = new Product("Orange", 0.50, null);
	 	final Product Watermelon= new Product("Watermelon", 0.80, OfferType.THREE_FOR_TWO);
	   
	 	final ShoppingItem item1 = new ShoppingItem(Apple, 4);
	 	final ShoppingItem item2 = new ShoppingItem(Orange, 3);
	 	final ShoppingItem item3 = new ShoppingItem(Watermelon, 5);
	 	
	 	 //initialisation of shopping basket
	 	shoppingBasket.addShoppingItem(item1);
	 	shoppingBasket.addShoppingItem(item2);
	 	shoppingBasket.addShoppingItem(item3);
    }
	

	@Test
	public void shouldComputeAndOutputBasketPrice() {
		
		//given
		initData();
	 
	 	//when 
	 	String output = basketProcessor.outputBasketReceipt(shoppingBasket);
	 	//then
	 	List<ShoppingItem> basketItems = shoppingBasket.getItemsList();
	 	
	 	assertTrue(basketItems.size()==3);
	 	assertTrue(output.length()>4);
	 	assertTrue(output.contains("3.2"));
	 	assertTrue(output.contains("0.4"));
	 	assertTrue(output.contains("1.5"));
	 	
	 	
	 	
	}
}
