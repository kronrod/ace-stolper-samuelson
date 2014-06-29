// Created by Luzius on Apr 28, 2014

package com.abs.market;

public abstract class AbstractOffer implements Comparable<AbstractOffer> {
	
	protected Wallet wallet;
	protected Stock stock;
	private double price;
	
	public AbstractOffer(Wallet wallet, Stock stock, double price){
		this.wallet = wallet;
		this.stock = stock;
		this.price = price;
		assert price >= 0;
	}
	
	public abstract double getAmount();
	
	public double getPrice(){
		return price;
	}
	
	public EGood getGood() {
		return stock.getGood();
	}
	
	public abstract double accept(Wallet source, Stock target, double amount);
	
	public int compareTo(AbstractOffer o) {
		return Double.compare(price, o.price);
	}
	
	@Override
	public String toString(){
		return stock.getGood() + " at " + price;
	}

}
