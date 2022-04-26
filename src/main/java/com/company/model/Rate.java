package com.company.model;


public class Rate {

	public double EUR;
	public double UAH;
	public double USD;

	public double getUAH() {
		return UAH;
	}

	@Override
	public String toString() {
		return "Rate{" +
				"UAH=" + UAH +
				", USD=" + USD +
				'}';
	}
}
