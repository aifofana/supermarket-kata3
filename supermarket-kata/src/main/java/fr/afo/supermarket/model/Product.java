package fr.afo.supermarket.model;

import fr.afo.supermarket.model.offers.OfferType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

	private final String name;
	private final double unitPrice;
	private final OfferType specialOffer;

}
