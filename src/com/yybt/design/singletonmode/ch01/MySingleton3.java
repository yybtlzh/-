package com.yybt.design.singletonmode.ch01;

/**
 * ����ģʽ
 * @author liuzehong
 *
 */
public class MySingleton3 {
	/**
	 * ͬ��˽�л����췽��
	 */
	private MySingleton3() {
	}
	
	/**
	 * ����ʹ����һ���ڲ��࣬�ô��ǵ��õ�ʱ��Żᱻװ��
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
		
		System.out.println("#########����ʽ����ģʽ�Ľ�#########");
		System.out.println("ʵ��1");
		MySingleton3 s1 = MySingleton3.getInstance();
		System.out.println("ʵ��2");
		MySingleton3 s2 = MySingleton3.getInstance();
		if(s1==s2){
			System.out.println("˵����ͬһ��ʵ��");
		}
		System.out.println();
	}
	

}
