// Created by Luzius on Apr 28, 2014

package com.abs.firm;

import java.util.Random;

import com.abs.market.EGood;
import com.abs.market.Wallet;

public class Chalet extends Firm {

	public Chalet(Random rand, Wallet money) {
		super(rand, money, EGood.FONDUE);
	}

	@Override
	protected double getItalianProd() {
		return LOW_PROD;
	}

	@Override
	protected double getSwissProd() {
		return HIGH_PROD;
	}

}
