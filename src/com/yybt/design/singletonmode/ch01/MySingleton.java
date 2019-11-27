package com.yybt.design.singletonmode.ch01;

/**
 * ����ģʽ(�Ľ�)
 * @author liuzehong
 *
 */
public class MySingleton {
	

	private static MySingleton mySingleton = null;
	
	/**
	 * ͬ��˽�л����췽��
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
		
		System.out.println("#########����ʽ����ģʽ#########");
		System.out.println("ʵ��1");
		MySingleton s1 = MySingleton.getInstance();
		System.out.println("ʵ��2");
		MySingleton s2 = MySingleton.getInstance();
		if(s1==s2){
			System.out.println("˵����ͬһ��ʵ��");
		}
		System.out.println();
	}
	

}
