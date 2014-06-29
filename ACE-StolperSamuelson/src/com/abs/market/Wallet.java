// Created by Luzius on Apr 22, 2014

package com.abs.market;

public class Wallet extends Stock {
	
	public Wallet(int amount) {
		super(EGood.MONEY, amount);
	}

	public Wallet() {
		super(EGood.MONEY);
	}

}
