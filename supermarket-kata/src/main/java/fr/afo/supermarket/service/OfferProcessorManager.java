package fr.afo.supermarket.service;

import static java.util.Optional.of;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.afo.supermarket.model.ShoppingItem;
import fr.afo.supermarket.model.offers.OfferType;

@Service
public class OfferProcessorManager {

	public Optional<OfferProcessor> getOfferProcessor(ShoppingItem shoppingItem) {

		/* OfferProcessors available in the supermarket) */

		// lambda expression for offer computation
		OfferProcessor byOneGetOne = (quantity, price) -> (quantity / 2) * price + (quantity % 2) * price;

		OfferProcessor threeForTwoOffer = (quantity, price) ->

		// int nbTriplet = (int)quantity /3;
		(int) (quantity / 3) * 2 * price + (int) (quantity % 3) * price;

		OfferProcessor noSpecialOffer = (quantity, price) -> quantity * price;

		OfferType offer = shoppingItem.getProduct().getSpecialOffer();

		// offer Processor by default
		OfferProcessor offerProcessor = noSpecialOffer;

		if (offer != null) {
			switch (offer) {
			case BY_ONE_GET_ONE:
				offerProcessor = byOneGetOne;
				break;
			case THREE_FOR_TWO:
				offerProcessor = threeForTwoOffer;
				break;
			default:

				throw new IllegalArgumentException("Offer not found in the billing system ");
			}
		}
		return of(offerProcessor);

	}
}
