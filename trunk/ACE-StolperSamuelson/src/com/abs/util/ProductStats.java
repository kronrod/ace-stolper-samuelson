// Created by Luzius on May 12, 2014

package com.abs.util;

import com.abs.market.EGood;

public class ProductStats {
	
	private EGood good;
	private double supply;
	private double production;
	private double consumption;
	private double turnover;

	public ProductStats(EGood good) {
		this.good = good;
	}

	public EGood getGood() {
		return good;
	}
	
	public void notifyProduced(double production) {
		this.production += production;
	}

	public void notifyOffered(double amount) {
		this.supply += amount;
	}

	public void notifyTraded(double actuallyTraded, double price) {
		this.consumption += actuallyTraded;
		this.turnover += price * actuallyTraded;
	}

	public double getProduction() {
		return production;
	}
	
	public double getSupply() {
		return supply;
	}

	public double getConsumption() {
		return consumption;
	}

	public double getPrice() {
		return consumption == 0.0 ? 0.0 : turnover / consumption;
	}
	
	@Override
	public String toString(){
		return good + " consumed " + consumption + " of " + supply + " at " + getPrice();
	}

}
