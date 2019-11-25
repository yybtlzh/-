package com.yybt.design.factory.produce;

import com.yybt.design.factory.CarA;

/**
 * 抽象工厂模式
 * @author lx
 *
 */
public class GetCarA implements produce{
	
	@Override
	public CarA get() {
		return new CarA();
	}

}
