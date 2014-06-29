// Created by Luzius on Apr 22, 2014

package com.abs.utility;

import com.abs.market.Price;
import com.abs.market.Stock;
import com.abs.market.Wallet;


public interface IUtility {
	
	/**
	 * @param quantities: vector of quantities for all goods found in EGood.values in this order
	 * @return
	 */
	public double getUtility(Stock... quantities);

	public double[] getOptimalAllocation(Wallet wallet, Stock[] goods, Price[] prices);

}
