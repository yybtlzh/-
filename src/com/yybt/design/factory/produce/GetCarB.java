package com.yybt.design.factory.produce;

import com.yybt.design.factory.CarB;

/**
 * ���󹤳�ģʽ
 * @author lx
 *
 */
public class GetCarB implements produce{
	
	@Override
	public CarB get() {
		return new CarB();
	}

}
