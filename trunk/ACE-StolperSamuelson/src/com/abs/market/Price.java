// Created by Luzius on May 4, 2014

package com.abs.market;

import com.abs.util.Numbers;

public class Price {
	
	public EGood good;
	public double price;
	
	public Price(EGood good, double price) {
		this.good = good;
		this.price = price;
	}

	public double getAmountAt(double money) {
		return money / price;
	}

	@Override
	public String toString(){
		return good + " at " + Numbers.toString(price);
	}
	
}
