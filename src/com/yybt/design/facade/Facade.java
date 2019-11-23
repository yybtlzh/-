package com.yybt.design.facade;
/**
 * 外观模式
 * @author lx
 *
 */
public class Facade {
	
    private subSystem1 subSystem1 = new subSystem1();
    private subSystem2 subSystem2 = new subSystem2();
    private subSystem3 subSystem3 = new subSystem3();
    
    public void startSystem(){
        subSystem1.start();
        subSystem2.start();
        subSystem3.start();
    }
    
    public void stopSystem(){
        subSystem1.stop();
        subSystem2.stop();
        subSystem3.stop();
    }
}