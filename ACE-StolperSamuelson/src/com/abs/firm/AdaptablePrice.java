// Created by Luzius on May 10, 2014

package com.abs.firm;

import com.abs.World;
import com.abs.util.Numbers;

public class AdaptablePrice implements IPrice {

	private static final double MAX_ADAPTION_FACTOR = 0.05;
	private static final double MIN_ADAPTION_FACTOR = Numbers.EPSILON * 1000;
	private static final double INITIAL_ADAPTION_FACTOR = MAX_ADAPTION_FACTOR;

	private double price;

	private double factor;
	private boolean direction;
	private int sameDirectionInARow;

	public AdaptablePrice() {
		this.price = 10.0;
		this.factor = INITIAL_ADAPTION_FACTOR / 4;
	}

	public void adapt(boolean increase) {
		if (World.SMART_PRICE_ADAPTION) {
			if (increase == direction) {
				sameDirectionInARow++;
				if (sameDirectionInARow >= 1) {
					factor = Math.min(MAX_ADAPTION_FACTOR, factor * 2);
					sameDirectionInARow = 0;
				}
			} else {
				sameDirectionInARow = 0;
				direction = increase;
				factor = Math.max(MIN_ADAPTION_FACTOR, factor / 2);
			}
		}
		if (increase) {
			price *= (1.0 + factor);
		} else {
			price /= (1.0 + factor);
		}
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return Numbers.toString(price);
	}

}
