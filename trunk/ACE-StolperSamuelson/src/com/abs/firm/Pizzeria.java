// Created by Luzius on Apr 28, 2014

package com.abs.firm;

import java.util.Random;

import com.abs.market.EGood;
import com.abs.market.Wallet;

public class Pizzeria extends Firm {

	public Pizzeria(Random rand, Wallet money) {
		super(rand, money, EGood.PIZZA);
	}
	
	@Override
	protected double getItalianProd() {
		return HIGH_PROD;
	}

	@Override
	protected double getSwissProd() {
		return LOW_PROD;
	}

}
