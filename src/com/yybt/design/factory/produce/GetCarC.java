package com.yybt.design.factory.produce;

import com.yybt.design.factory.CarC;

/**
 * ���󹤳�ģʽ
 * @author lx
 *
 */
public class GetCarC implements produce{
	
	@Override
	public CarC get() {
		return new CarC();
	}

}
