package com.yybt.design.singletonmode.ch01;

/**
 * 懒汉模式
 * @author liuzehong
 *
 */
public class MySingleton3 {
	/**
	 * 同样私有化构造方法
	 */
	private MySingleton3() {
	}
	
	/**
	 * 这里使用了一个内部类，好处是调用的时候才会被装载
	 * @author lx
	 *
	 */
	private static class SingletonHandler{
		
        private static MySingleton3 singleton = new MySingleton3();
        
    }
	
	public static MySingleton3 getInstance(){
		
		return SingletonHandler.singleton;
	}

	
	public static void main(String[] args) {
		
		System.out.println("#########懒汉式单例模式改进#########");
		System.out.println("实例1");
		MySingleton3 s1 = MySingleton3.getInstance();
		System.out.println("实例2");
		MySingleton3 s2 = MySingleton3.getInstance();
		if(s1==s2){
			System.out.println("说明是同一个实例");
		}
		System.out.println();
	}
	

}
