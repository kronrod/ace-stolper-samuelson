// Created by Luzius on Apr 28, 2014

package com.abs.market;

import java.util.Arrays;

import com.abs.util.ProductStats;


public class Market implements IPriceMakerMarket, IPriceTakerMarket {

	private SubMarket[] markets;

	public Market() {
		this.markets = new SubMarket[EGood.values().length];
		for (int i=0; i<markets.length; i++){
			EGood good = EGood.values()[i];
			if (good.isTradable()){
				this.markets[i] = new SubMarket(good);
			}
		}
	}

	private SubMarket get(EGood good) {
		return markets[good.ordinal()];
	}

	public boolean trade(Wallet wallet, Stock good, double amount) {
		return get(good.getGood()).trade(wallet, good, amount);
	}

	public void offer(AbstractOffer offer) {
		get(offer.getGood()).offer(offer);
	}

	public Price[] getAvailableGoods() {
		int count = 0;
		for (SubMarket sub: markets){
			if (sub != null && sub.hasOffers()){
				count++;
			}
		}
		int pos = 0;
		Price[] prices = new Price[count];
		for (SubMarket sub: markets){
			if (sub != null && sub.hasOffers()){
				prices[pos++] = sub.getPrice();
			}
		}
		return prices;
	}
	
	public void notifyProduced(EGood good, double prod) {
		markets[good.ordinal()].getStats().notifyProduced(prod);
	}

	public ProductStats[] getProductStats() {
		ProductStats[] stats = new ProductStats[EGood.TRADABLES.length];
		int pos = 0;
		for (int i=0; i<markets.length; i++){
			if (markets[i] != null){
				stats[pos++] = markets[i].getStats();
			}
		}
		return stats;
	}
	
	@Override
	public String toString(){
		return Arrays.toString(markets);
	}

}
