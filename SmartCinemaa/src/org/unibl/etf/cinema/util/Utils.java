package org.unibl.etf.cinema.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {

	public static boolean isIntegerNumber(String text) {
		try {
			Long.parseLong(text);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDecimalNumber(String text) {
		try {
			Double.parseDouble(text);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String formatDecimalNumber(double number) {
		NumberFormat formatter = new DecimalFormat("#0.00");     
		return formatter.format(number).replace(',', '.');
	}
}
