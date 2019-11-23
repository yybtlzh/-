package com.yybt.design.factory;

public class StaticFactory {

	private StaticFactory() {
	}

	public static Car getA() {
		return new CarA();
	}

	public static Car getB() {
		return new CarB();
	}

	public static Car getC() {
		return new CarC();
	}
}
