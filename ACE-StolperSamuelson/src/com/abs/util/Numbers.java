// Created by Luzius on Apr 28, 2014

package com.abs.util;

import java.text.DecimalFormat;


public class Numbers {

	private static final DecimalFormat FORMATTER = new DecimalFormat("#.##");
	public static final double EPSILON = 0.000001;

	public static boolean isBigger(double bigger, double smaller) {
		return bigger - smaller > -EPSILON;
	}

	public static boolean isSmaller(double smaller, double bigger) {
		return isBigger(bigger, smaller);
	}
	
	public static String toString(double d){
		return FORMATTER.format(d);
	}
	
}
