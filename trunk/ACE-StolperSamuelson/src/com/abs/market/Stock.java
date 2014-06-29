// Created by Luzius on Apr 22, 2014

package com.abs.market;

import com.abs.util.Numbers;

public class Stock {
	
	private EGood good;
	private double amount;
	
	public Stock(EGood good){
		this(good, 0);
	}

	public Stock(EGood good, double initial) {
		this.good = good;
		this.amount = initial;
	}
	
	public EGood getGood(){
		return good;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void remove(double quantity) {
		if (quantity > amount && quantity - amount < Numbers.EPSILON){
			quantity = amount; // prevent negative values due to rounding errors
		}
		assert this.amount >= quantity;
		this.amount -= quantity;
	}

	public void add(double quantity) {
		assert this.amount >= -quantity;
		this.amount += quantity;
	}
	
	@Override
	public String toString(){
		return Numbers.toString(amount) + " " + good.name().toLowerCase();
	}

}
