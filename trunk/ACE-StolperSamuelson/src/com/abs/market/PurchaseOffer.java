// Created by Luzius on Apr 28, 2014

package com.abs.market;

import com.abs.util.Numbers;

public class PurchaseOffer extends AbstractOffer {
	
	private double quantity;
	
	public PurchaseOffer(Wallet wallet, Stock stock, double price, double quantity){
		super(wallet, stock, price);
		this.quantity = quantity;
		assert wallet.getAmount() - quantity * price >= -Numbers.EPSILON;
		assert quantity > 0;
	}
	
	@Override
	public double getAmount(){
		return quantity;
	}
	
	@Override
	public double accept(Wallet seller, Stock sellerStock, double targetAmount){
		assert sellerStock.getAmount() >= targetAmount;
		double amount = Math.min(targetAmount, quantity);
		assert amount >= 0;
		double total = amount * getPrice();
		sellerStock.remove(amount);
		seller.add(total);
		wallet.remove(total);
		stock.add(amount);
		quantity -= amount;
		return amount;
	}
	
	@Override
	public int compareTo(AbstractOffer o) {
		return -super.compareTo(o);
	}

}
