package com.epam.vasilevsky.exchanger.service.coursenbrb;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class CodeCurrency {
	public static String getCurrencyFromName(CurrencyName currency) {
		switch (currency) {
		case USD:
			return "145";
		case EUR:
			return "19";
		case PLZ:
			return "219";
		case RUB:
			return "190";
		default:
			return null;
		}
	}
}
