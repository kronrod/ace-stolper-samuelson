// Created by Luzius on Apr 28, 2014

package com.abs.market;

import java.util.PriorityQueue;

import com.abs.util.ProductStats;

public class SubMarket {

	private EGood good;
	private ProductStats stats;
	private PriorityQueue<AbstractOffer> offers;

	public SubMarket(EGood good) {
		this.offers = new PriorityQueue<AbstractOffer>();
		this.good = good;
		this.stats = new ProductStats(good);
	}

	public boolean hasOffers() {
		return offers.size() > 0;
	}

	public boolean hasOffers(int amount) {
		if (offers.isEmpty()) {
			return false;
		} else if (offers.peek().getAmount() >= amount) {
			return true;
		} else {
			for (AbstractOffer o : offers) {
				amount -= o.getAmount();
				if (amount <= 0) {
					return true;
				}
			}
			return false;
		}
	}

	public boolean trade(Wallet wallet, Stock stock, double amount) {
		AbstractOffer best = offers.poll();
		double actuallyTraded = best.accept(wallet, stock, amount);
		stats.notifyTraded(actuallyTraded, best.getPrice());
		if (best.getAmount() > 0) {
			offers.add(best); // recycle
			return true;
		} else {
			return false;
		}
	}

	public void offer(AbstractOffer offer) {
		assert offer.getGood() == good;
		offers.add(offer);
		stats.notifyOffered(offer.stock.getAmount());
	}

	public EGood getGood() {
		return good;
	}

	public Price getPrice() {
		AbstractOffer best = offers.peek();
		return best == null ? null : new Price(good, best.getPrice());
	}

	public ProductStats getStats() {
		return stats;
	}
	
	@Override
	public String toString(){
		return getPrice() + ", left: " + (stats.getProduction() - stats.getConsumption());
	}

}
