// Created by Luzius on Apr 28, 2014

package com.abs.market;

public class SaleOffer extends AbstractOffer {
	
	public SaleOffer(Wallet wallet, Stock stock, double price){
		super(wallet, stock, price);
	}
	
	@Override
	public double getAmount(){
		return stock.getAmount();
	}
	
	@Override
	public double accept(Wallet payer, Stock target, double targetAmount){
		double amount = Math.min(stock.getAmount(), targetAmount);
		double total = amount * getPrice();
		if (total > payer.getAmount()){
			amount = payer.getAmount() / getPrice();
			total = payer.getAmount();
		}
		assert amount >= 0;
		payer.remove(total);
		wallet.add(total);
		stock.remove(amount);
		target.add(amount);
		return amount;
	}

}
