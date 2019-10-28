package fr.afo.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor /* génère le constructeur public avec tous les arguments */

/**
 * 
 * @author AFO : model class represents one product and the quantity add in
 *         the shopping basket.
 *
 */
public class ShoppingItem {

	private Product product = null;

	private double quantity = 0;

}
