// Created by Luzius on Apr 22, 2014

package com.abs.market;

public interface IPriceTakerMarket {

	public boolean trade(Wallet wallet, Stock good, double amount);

	public Price[] getAvailableGoods();
	
}
