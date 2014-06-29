// Created by Luzius on Apr 28, 2014

package com.abs.firm;

import java.util.Random;

import com.abs.World;
import com.abs.market.EGood;
import com.abs.market.IPriceMakerMarket;
import com.abs.market.PurchaseOffer;
import com.abs.market.SaleOffer;
import com.abs.market.Stock;
import com.abs.market.Wallet;
import com.abs.util.Numbers;

public abstract class Firm {

	public static double LOW_PROD;
	public static double HIGH_PROD;
	
	{
		adaptProductivity(4);
	}
	
	private IPrice price;
	private IPrice salaryF, salaryP;
	private double offeredLaborF, offeredLaborP;

	private Stock stock;
	private Stock laborP, laborF;
	private Wallet money;

	public Firm(Random rand, Wallet money, EGood good) {
		this.money = money;
		this.stock = new Stock(good);
		this.laborF = new Stock(EGood.LEISURE_F);
		this.laborP = new Stock(EGood.LEISURE_P);
		this.salaryF = new AdaptablePrice();
		this.salaryP = new AdaptablePrice();
		this.price = new AdaptablePrice();
	}
	
	public static void adaptProductivity(double high){
		HIGH_PROD = high;
		LOW_PROD = 10 - high;
	}

	public void offer(IPriceMakerMarket market) {
		double totSalaries = money.getAmount() / 4;
		double shareK = totSalaries * getSwissProd() / (getSwissProd() + getItalianProd());
		double shareP = totSalaries * getItalianProd() / (getSwissProd() + getItalianProd());
		offeredLaborF = shareK / salaryF.getPrice();
		offeredLaborP = shareP / salaryP.getPrice();
		if (totSalaries > 0) {
			market.offer(new PurchaseOffer(money, laborF, salaryF.getPrice(), offeredLaborF));
			market.offer(new PurchaseOffer(money, laborP, salaryP.getPrice(), offeredLaborP));
		}
		market.offer(new SaleOffer(money, stock, price.getPrice()));
	}

	protected abstract double getItalianProd();
	
	protected abstract double getSwissProd();

	public double produce() {
		double production = Math.max(1, getSwissProd() * Math.log(Math.max(laborF.getAmount(), 1)) + getItalianProd() * Math.log(Math.max(laborP.getAmount(), 1))); // always produce at least one good for model sanity
		Stock prod = new Stock(stock.getGood(), production + stock.getAmount() * World.INVENTORY_PERSISTENCE);
//		printStats(prod);
		adaptPrice();
		adaptSalary();
		stock = prod;
		laborP = new Stock(laborP.getGood());
		laborF = new Stock(laborF.getGood());
		return production;
	}

	protected void printStats(Stock prod) {
		System.out.println("Produced " + prod + ", selling for " + price + " and paying " + Numbers.toString(offeredLaborF) + "/" + Numbers.toString(offeredLaborP) + " workers " + salaryF + "/" + salaryP);
	}

	private void adaptSalary() {
		boolean increaseSalaryP = laborP.getAmount() < offeredLaborP;
		salaryP.adapt(increaseSalaryP);
		boolean increaseSalaryK = laborF.getAmount() < offeredLaborF;
		salaryF.adapt(increaseSalaryK);
	}

	private void adaptPrice() {
		boolean increasePrice = stock.getAmount() == 0;
		price.adapt(increasePrice);
	}
	
	public EGood getGood() {
		return stock.getGood();
	}

	@Override
	public String toString() {
		return stock + " for sale at " + price + ", salary p " + salaryP + ", salary k " + salaryF + ", jobs: " + (offeredLaborF + offeredLaborP);
	}

}
