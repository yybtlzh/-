package com.yybt.design.singletonmode.ch01;

/**
 * ��ģʽ
 * @author liuzehong
 *
 */
public class MySingleton2 {
	

	private static MySingleton2 mySingleton = new MySingleton2() ;
	
	/**
	 * ͬ��˽�л����췽��
	 */
	private MySingleton2() {
	}
	
	public static MySingleton2 getInstance(){
		
		return mySingleton;
	}

	
	public static void main(String[] args) {
		
		System.out.println("#########����ʽ����ģʽ#########");
		System.out.println("ʵ��1");
		MySingleton2 s1 = MySingleton2.getInstance();
		System.out.println("ʵ��2");
		MySingleton2 s2 = MySingleton2.getInstance();
		if(s1==s2){
			System.out.println("˵����ͬһ��ʵ��");
		}
		System.out.println();
	}
	

}
