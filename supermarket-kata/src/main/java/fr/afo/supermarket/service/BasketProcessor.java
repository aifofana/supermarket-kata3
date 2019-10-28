package fr.afo.supermarket.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.afo.supermarket.model.ShoppingBasket;
import fr.afo.supermarket.model.ShoppingItem;
import lombok.NoArgsConstructor;

/**
 * Class in charge of the shopping basket computing and the receipt output
 * 
 * @author afofana
 *
 */
@Service

@NoArgsConstructor

public class BasketProcessor {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(BasketProcessor.class);
	private final OfferProcessorManager offerProcessorManager = new OfferProcessorManager();

	private final int COLUMN_SIZE = 20;

	/**
	 * output the total purchase of the shopping basket: The price of each item is
	 * computed and the corresponding special offer is applied .
	 * 
	 * @param basket
	 * @return String the output displayed in the console;
	 */
	public String outputBasketReceipt(ShoppingBasket shoppingBasket) {
		/* header of the output */
		StringBuilder output = new StringBuilder("ITEM");
		String whiteSpace = "               ";

		output.append(whiteSpace);
		output.append("QUANTITY");
		output.append(whiteSpace);
		output.append("PRICE  \n");

		try {

			List<ShoppingItem> listItems = shoppingBasket.getItemsList();

			if (!listItems.isEmpty()) {
				double totalBasketPrice = 0;
				for (ShoppingItem item : listItems) {
					Optional<OfferProcessor> offerProcessor = offerProcessorManager.getOfferProcessor(item);

					if (offerProcessor.isPresent()) {

						output.append(item.getProduct().getName());
						output.append(whiteSpace);
						output.append(item.getQuantity());
						output.append(whiteSpace);
						output.append(offerProcessor.get().apply(item.getQuantity(), item.getProduct().getUnitPrice()));
						output.append(whiteSpace + "\n");

						totalBasketPrice += offerProcessor.get().apply(item.getQuantity(),
								item.getProduct().getUnitPrice());

					}
				}
				output.append("-------------------------------------------------------- \n");
				output.append("TOTAL PRICE :                      " + totalBasketPrice);

			}
		} catch (IllegalArgumentException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(e.getMessage());
			}
		}
		System.out.append(output);
		return output.toString();

	}

	/**
	 * Compute the shopping basket total price
	 * 
	 * @param shoppingBasket
	 * @return
	 */
	public double scanBasket(ShoppingBasket shoppingBasket) {
		double total = 0;

		List<ShoppingItem> listItems = shoppingBasket.getItemsList();

		if (!listItems.isEmpty()) {
			for (ShoppingItem item : listItems) {
				Optional<OfferProcessor> offerProcessor = offerProcessorManager.getOfferProcessor(item);

				if (offerProcessor.isPresent()) {
					total += offerProcessor.get().apply(item.getQuantity(), item.getProduct().getUnitPrice());

				}
			}

			System.out.append("TOTAL " + total);

		}
		return total;
	}

}
