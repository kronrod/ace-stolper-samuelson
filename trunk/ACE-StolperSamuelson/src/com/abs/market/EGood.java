// Created by Luzius on Apr 22, 2014

package com.abs.market;

public enum EGood {

	MONEY, PIZZA, FONDUE, LEISURE_F, LEISURE_P;

	public static final EGood[] CONSUMABLES = new EGood[] { PIZZA, FONDUE };
	public static final EGood[] TRADABLES = new EGood[] { PIZZA, FONDUE, LEISURE_F, LEISURE_P };

	public boolean canSell() {
		return this == MONEY || isTime();
	}

	public boolean canBuy() {
		return this == MONEY || this == PIZZA || this == FONDUE;
	}

	public String getName() {
		switch (this) {
		case LEISURE_F:
			return "Swiss hour";
		case LEISURE_P:
			return "Italian hour";
		default:
			return name().toLowerCase();
		}
	}

	public boolean isTime() {
		return this == LEISURE_F || this == LEISURE_P;
	}

	public boolean isTradable() {
		for (EGood t : TRADABLES) {
			if (t == this) {
				return true;
			}
		}
		return false;
	}

}
