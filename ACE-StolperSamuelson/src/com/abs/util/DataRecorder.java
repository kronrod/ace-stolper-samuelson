// Created by Luzius on Apr 1, 2014

package com.abs.util;

import java.util.ArrayList;

import com.abs.market.EGood;

public class DataRecorder {

	private int interval;
	private String[] labels;
	private ArrayList<double[]> data;

	public DataRecorder(int interval, String... labels) {
		this.interval = interval;
		this.labels = labels;
		this.data = new ArrayList<double[]>();
	}

	public DataRecorder(int interval, EGood[] tradables, String... additional) {
		this.interval = interval;
		this.data = new ArrayList<double[]>();
		this.labels = new String[additional.length + tradables.length * 3];
		System.arraycopy(additional, 0, labels, 0, additional.length);
		int pos = additional.length;
		for (EGood tradable: tradables){
			labels[pos++] = tradable.getName() + " production";
			labels[pos++] = tradable.getName() + " consumption";
			labels[pos++] = tradable.getName() + " price";
		}
	}

	public boolean wantsRecord(int day) {
		return day % interval == 0;
	}

	public void record(ProductStats[] prods, double... additional) {
		double[] data = new double[prods.length * 3 + additional.length];
		System.arraycopy(additional, 0, data, 0, additional.length);
		int pos = additional.length;
		for (ProductStats prod: prods){
//			data[pos++] = prod.getProduction();
			data[pos++] = prod.getProduction();
			data[pos++] = prod.getConsumption();
			data[pos++] = prod.getPrice();
		}
		assert data.length == labels.length;
		this.data.add(data);
	}

	public String[] getLabels() {
		return labels;
	}

	public double[][] getAllData() {
		return data.toArray(new double[data.size()][]);
	}

}
