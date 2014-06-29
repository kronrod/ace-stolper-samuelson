// Created by Luzius on Apr 22, 2014

package com.abs.consumer;

import com.abs.market.EGood;
import com.abs.market.IPriceTakerMarket;
import com.abs.market.Price;
import com.abs.market.Stock;
import com.abs.market.Wallet;
import com.abs.util.Numbers;
import com.abs.utility.IUtility;
import com.abs.utility.LogUtil;

public class Consumer {

	private static final int HOURS_PER_DAY = 24;

	private static final double TOTAL_PREF = 10;
	private static final double LEISURE_PREFERENCE = HOURS_PER_DAY - 10;

	private Wallet money;
	private Stock leisure;
	private Stock pizza;
	private Stock fondue;

	private EGood type;
	private IUtility utility;

	public Consumer(EGood type, Wallet money) {
		this.type = type;
		this.money = money;
		adaptPreferences(8, 8);
		init();
	}

	public void adaptPreferences(double ipizza, double spizza) {
		if (isSwiss()) {
			adaptPreferences(spizza, true);
		} else {
			adaptPreferences(ipizza, false);
		}
	}

	private void adaptPreferences(double pizza, boolean swiss) {
		this.utility = new LogUtil(0, pizza, TOTAL_PREF - pizza, swiss ? LEISURE_PREFERENCE : 0, swiss ? 0 : LEISURE_PREFERENCE);
	}

	public boolean isSwiss() {
		return type == EGood.LEISURE_F;
	}

	public void maximizeUtility(IPriceTakerMarket market) {
		boolean trading = true;
		while (trading) {
			trading = false;
			Price[] available = market.getAvailableGoods();
			Stock[] inStock = new Stock[available.length];
			for (int i = 0; i < available.length; i++) {
				inStock[i] = getStock(available[i].good);
			}
			double[] allocs = utility.getOptimalAllocation(money, inStock, available);
			boolean completedSales = true;
			for (int i = 0; i < allocs.length; i++) {
				double difference = allocs[i] - inStock[i].getAmount();
				if (difference < -Numbers.EPSILON) {
					assert inStock[i].getGood().isTime();
					completedSales &= market.trade(money, leisure, -difference);
					trading = true;
				}
			}
			if (!completedSales) {
				continue; // try working as much as planned before spending money
			}
			for (int i = 0; i < allocs.length; i++) {
				double difference = allocs[i] - inStock[i].getAmount();
				if (difference > Numbers.EPSILON) {
					assert !inStock[i].getGood().isTime();
					market.trade(money, getStock(inStock[i].getGood()), difference);
					trading = true;
				}
			}
		}
	}

	private Stock getStock(EGood good) {
		switch (good) {
		case PIZZA:
			return pizza;
		case LEISURE_P:
		case LEISURE_F:
			return leisure.getGood() == good ? leisure : new Stock(good);
		case FONDUE:
			return fondue;
		case MONEY:
		default:
			return money;
		}
	}

	private double prevUtil = 0.0;

	public double consume() {
		double u = utility.getUtility(money, pizza, fondue, leisure.getGood().ordinal() == 3 ? leisure : new Stock(EGood.LEISURE_F), leisure.getGood().ordinal() == 4 ? leisure : new Stock(
				EGood.LEISURE_P));
		prevUtil = u;
		assert !Double.isNaN(u);
		init();
		if (Double.isInfinite(u)) {
			return -1;
		} else {
			return u;
		}
	}

	private void init() {
		leisure = new Stock(type, HOURS_PER_DAY);
		pizza = new Stock(EGood.PIZZA);
		fondue = new Stock(EGood.FONDUE);
	}

	@Override
	public String toString() {
		return "Consumer with " + leisure + ", util " + prevUtil;
	}

}
