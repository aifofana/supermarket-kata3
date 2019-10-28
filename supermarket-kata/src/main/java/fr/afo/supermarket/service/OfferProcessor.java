package fr.afo.supermarket.service;

@FunctionalInterface
public interface OfferProcessor {

	public double apply(double itemQuantity, double itemPrice);

}
