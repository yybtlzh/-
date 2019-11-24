package com.yybt.design.factory;

import java.lang.reflect.Field;

import com.yybt.utils.ReflectHelper;

/**
 * 工厂模式
 * @author lx
 *
 */
public class GetCar {
	public Car get(String name){
		
        Car x = null ;
        if ( name.equals("CarA")) {
            x = StaticFactory.getA();
        }else if ( name.equals("CarB")){
            x = StaticFactory.getB();
        }else if ( name.equals("CarC")){
            x = StaticFactory.getC();
        }
        
        return x;
        
        
    }

}
