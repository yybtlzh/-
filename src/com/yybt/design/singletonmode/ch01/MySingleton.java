package com.yybt.design.singletonmode.ch01;

/**
 * 懒汉模式(改进)
 * @author liuzehong
 *
 */
public class MySingleton {
	

	private static MySingleton mySingleton = null;
	
	/**
	 * 同样私有化构造方法
	 */
	private MySingleton() {
	}
	
	
	private static MySingleton SingletonBuild(){
		if(mySingleton == null){
			mySingleton = new MySingleton();
		}
		return mySingleton;
	}
	
	public static MySingleton getInstance(){
		
		return SingletonBuild();
		
	}

	
	public static void main(String[] args) {
		
		System.out.println("#########懒汉式单例模式#########");
		System.out.println("实例1");
		MySingleton s1 = MySingleton.getInstance();
		System.out.println("实例2");
		MySingleton s2 = MySingleton.getInstance();
		if(s1==s2){
			System.out.println("说明是同一个实例");
		}
		System.out.println();
	}
	

}
