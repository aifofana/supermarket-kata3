package fr.afo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasket {

	List<ShoppingItem> itemsList = new ArrayList<>();

	public List<ShoppingItem> getItemsList() {
		return new ArrayList<ShoppingItem>(itemsList);
	}

	/**
	 * Add a new shpoppingItem to the basket.
	 * @param productQuantity
	 */
	public void addShoppingItem(ShoppingItem productQuantity) {
		itemsList.add(productQuantity);

	}

	/**
	 * Method to add a certain quantity of a product in the shopping basket
	 * 
	 * @param Product  : @see <a href>"fr.afo.supermarket.model.Product"</a>
	 * @param quantity :
	 */
	public void addShoppingProduct(Product product, double quantity) {
		// Insertion of one instance of shoppingItem in the itemsList
		itemsList.add(new ShoppingItem(product, quantity));

	}

}
