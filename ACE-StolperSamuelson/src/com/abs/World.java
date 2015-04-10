// Created by Luzius on Apr 22, 2014

package com.abs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.abs.consumer.Consumer;
import com.abs.firm.Pizzeria;
import com.abs.firm.Firm;
import com.abs.firm.Chalet;
import com.abs.market.EGood;
import com.abs.market.Market;
import com.abs.market.Wallet;
import com.abs.util.DataRecorder;

// The world 2
public class World {

	private Random rand;

	private static final int INITIAL_MONEY_FIRM = 1000;
	private static final int INITIAL_MONEY_CONSUMER = 0;

	public static boolean SMART_PRICE_ADAPTION = false;
	public static double INVENTORY_PERSISTENCE = 0;

	private int day;
	private ArrayList<Firm> firms;
	private ArrayList<Consumer> consumers;
	private DataRecorder recorder;

	public World(int seed, int consumers, int firmsPerGood, double inventoryPersistence, int smartPrices) {
		INVENTORY_PERSISTENCE = inventoryPersistence;
		SMART_PRICE_ADAPTION = smartPrices == 1;
		this.rand = new Random(seed);
		this.recorder = new DataRecorder(1, EGood.TRADABLES, "Avg Italian Utility", "Avg Swiss Utility");
		this.consumers = new ArrayList<Consumer>();
		for (int i = 0; i < consumers; i++) {
			this.consumers.add(new Consumer(EGood.LEISURE_F, new Wallet(INITIAL_MONEY_CONSUMER)));
		}
		for (int i = 0; i < consumers; i++) {
			this.consumers.add(new Consumer(EGood.LEISURE_P, new Wallet(INITIAL_MONEY_CONSUMER)));
		}
		this.firms = new ArrayList<Firm>();
		for (int i = 0; i < firmsPerGood; i++) {
			firms.add(new Pizzeria(rand, new Wallet(INITIAL_MONEY_FIRM)));
			firms.add(new Chalet(rand, new Wallet(INITIAL_MONEY_FIRM)));
		}
		this.day = 0;
	}

	public DataRecorder getData() {
		return recorder;
	}

	public void step(int days) {
		int target = this.day + days;
		for (; day < target; day++) {
			Market market = new Market();
			Collections.shuffle(firms, rand);
			for (Firm firm : firms) {
				firm.offer(market);
			}
			Collections.shuffle(consumers, rand);
			for (Consumer c : consumers) {
				c.maximizeUtility(market);
			}
			double swissUtil = 0.0;
			double italianUtil = 0.0;
			for (Consumer c : consumers) {
				if (c.isSwiss()) {
					swissUtil += c.consume();
				} else {
					italianUtil += c.consume();
				}
			}
			for (Firm firm : firms) {
				double prod = firm.produce();
				market.notifyProduced(firm.getGood(), prod);
			}
			if (recorder.wantsRecord(day)) {
				double avgSwiss = swissUtil / (consumers.size() / 2);
				double avgItalian = italianUtil / (consumers.size() / 2);
				recorder.record(market.getProductStats(), avgItalian, avgSwiss);
			}
		}
	}

	public void updatePreferences(double italianPizzaPref, double swissPizzaPref, double intenseProductivity) {
		Firm.adaptProductivity(intenseProductivity);
		for (Consumer c : consumers) {
			c.adaptPreferences(italianPizzaPref, swissPizzaPref);
		}
	}

	public static void main(String[] args) {
		World world = new World(313, 100, 10, 0.95, 1);
		for (int i = 0; i < 5000; i++) {
			System.out.println("Step " + i);
			world.step(1);
		}
	}
}
