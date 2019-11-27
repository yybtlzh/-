package com.yybt.design.singletonmode.ch01;

/**
 * 恶汉模式
 * @author liuzehong
 *
 */
public class MySingleton2 {
	

	private static MySingleton2 mySingleton = new MySingleton2() ;
	
	/**
	 * 同样私有化构造方法
	 */
	private MySingleton2() {
	}
	
	public static MySingleton2 getInstance(){
		
		return mySingleton;
	}

	
	public static void main(String[] args) {
		
		System.out.println("#########饿汉式单例模式#########");
		System.out.println("实例1");
		MySingleton2 s1 = MySingleton2.getInstance();
		System.out.println("实例2");
		MySingleton2 s2 = MySingleton2.getInstance();
		if(s1==s2){
			System.out.println("说明是同一个实例");
		}
		System.out.println();
	}
	

}
