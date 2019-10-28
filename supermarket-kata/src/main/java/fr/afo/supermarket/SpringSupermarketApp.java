package fr.afo.supermarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.afo.supermarket.model.Product;
import fr.afo.supermarket.model.ShoppingBasket;
import fr.afo.supermarket.model.ShoppingItem;
import fr.afo.supermarket.model.offers.OfferType;
import fr.afo.supermarket.service.BasketProcessor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SpringSupermarketApp 
{
	
	 private static BasketProcessor basketProcessor = new BasketProcessor();
	 

	static ShoppingBasket shoppingBasket = new ShoppingBasket();
	 
	private static void initData() {
	      
		    final Product Apple = new Product("Apple", 0.20, OfferType.BY_ONE_GET_ONE);
		 	final Product Orange = new Product("Orange", 0.50, null);
		 	final Product Watermelon= new Product("Watermelon", 0.80, OfferType.THREE_FOR_TWO);
		   
		 	final ShoppingItem item1 = new ShoppingItem(Apple, 4);
		 	final ShoppingItem item2 = new ShoppingItem(Orange, 3);
		 	final ShoppingItem item3 = new ShoppingItem(Watermelon, 5);
		 	
		 	 //shopping basket initialisation
		 	shoppingBasket.addShoppingItem(item1);
		 	shoppingBasket.addShoppingItem(item2);
		 	shoppingBasket.addShoppingItem(item3);
	    }
	
	public static void main( String[] args )
    {
    	 log.info("kata supermaket : Start of the basket purchase output ....") ;
      
    	System.out.println( "kata supermaket : Start of the basket purchase output ...." );
    	initData();
    	String output = basketProcessor.outputBasketReceipt(shoppingBasket);
    }
}
