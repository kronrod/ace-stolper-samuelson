// Created by Luzius on May 6, 2014

package com.abs.utility;

import com.abs.market.EGood;
import com.abs.market.Price;
import com.abs.market.Stock;
import com.abs.market.Wallet;
import com.abs.util.Numbers;

public class LogUtil implements IUtility {

	private double[] weights;

	public LogUtil(double... weights) {
		this.weights = weights;
		assert weights.length == EGood.values().length;
	}

	public double getUtility(Stock... goods) {
		assert goods.length == weights.length;
		double u = 0.0;
		for (int i = 0; i < goods.length; i++) {
			assert goods[i].getGood() == EGood.values()[i];
			if (weights[i] != 0) {
				u += Math.log(goods[i].getAmount()) * weights[i];
			}
		}
		return u;
	}

	public double[] getOptimalAllocation(Wallet money, Stock[] goods, Price[] prices) {
		assert goods.length == prices.length;
		assert getWeight(money.getGood()) == 0.0;
		if (goods.length == 0) {
			return new double[]{};
		} else if (goods.length == 1) {
			if (goods[0].getGood().canBuy()) {
				return new double[] { goods[0].getAmount() + prices[0].getAmountAt(money.getAmount()) };
			} else {
				return new double[] { goods[0].getAmount() };
			}
		} else {
			double[] weights = new double[goods.length];
			double totweight = 0.0;
			for (int i = 0; i < goods.length; i++) {
				double weight = getWeight(goods[i].getGood());
				totweight += weight;
				weights[i] = weight;
			}
			double endowment = calcEndowment(goods, prices) + money.getAmount();
			double[] targetAmounts = new double[goods.length];
			for (int i = 0; i < weights.length; i++) {
				double target = weights[i] * endowment / totweight / prices[i].price;
				targetAmounts[i] = target;
			}
			for (int i = 0; i < weights.length; i++) {
				if (Numbers.isBigger(targetAmounts[i], goods[i].getAmount()) && !goods[i].getGood().canBuy()) {
					return insert(getOptimalAllocation(money, filter(goods, i), filter(prices, i)), goods[i], i);
				}
			}
			for (int i = 0; i < weights.length; i++) {
				if (Numbers.isSmaller(targetAmounts[i], goods[i].getAmount()) && !goods[i].getGood().canSell()) {
					return insert(getOptimalAllocation(money, filter(goods, i), filter(prices, i)), goods[i], i);
				}
			}

			return targetAmounts;
		}
	}

	private Price[] filter(Price[] prices, int i) {
		return filter(prices, new Price[prices.length - 1], i);
	}

	private Stock[] filter(Stock[] goods, int i) {
		return filter(goods, new Stock[goods.length - 1], i);
	}

	private double[] insert(double[] optimalAllocation, Stock stock, int i) {
		double[] ext = new double[optimalAllocation.length + 1];
		System.arraycopy(optimalAllocation, 0, ext, 0, i);
		ext[i] = stock.getAmount();
		System.arraycopy(optimalAllocation, i, ext, i + 1, optimalAllocation.length - i);
		return ext;
	}

	private <T> T[] filter(T[] goods, T[] red, int i) {
		System.arraycopy(goods, 0, red, 0, i);
		System.arraycopy(goods, i + 1, red, i, red.length - i);
		return red;
	}

	private double getWeight(EGood good) {
		return weights[good.ordinal()];
	}

	private double calcEndowment(Stock[] goods, Price[] prices) {
		double endow = 0.0;
		for (int i = 0; i < goods.length; i++) {
			if (goods[i] != null) {
				assert goods[i].getGood() == prices[i].good;
				endow += goods[i].getAmount() * prices[i].price;
			}
		}
		return endow;
	}

}
