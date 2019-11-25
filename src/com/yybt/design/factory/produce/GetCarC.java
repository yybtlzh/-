package com.yybt.design.factory.produce;

import com.yybt.design.factory.CarC;

/**
 * 抽象工厂模式
 * @author lx
 *
 */
public class GetCarC implements produce{
	
	@Override
	public CarC get() {
		return new CarC();
	}

}
