// Created by Luzius on Jun 22, 2014

package com.abs.firm;

public class StaticPrice implements IPrice {
	
	private double price;

	public StaticPrice(double  price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void adapt(boolean increasePrice) {
	}

}
