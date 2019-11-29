package com.yybt.design.proxy.ch02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.yybt.design.proxy.ch01.BaseSinger;
import com.yybt.design.proxy.ch01.Singer;

/**
 * ��̬����
 * @author liuzehong
 *
 */
public class SingProxy implements InvocationHandler {
	
	// �������������֮������Object����Ϊ����Java��������������Զ�����ĳ��ࣩ
	private Object target;

	// ����������󣨸��ݱ������������
	public Object bind(Object target) {
		this.target = target;
		// ʹ��Proxy�ഴ��һ����̬�������
		Object proxy = Proxy.newProxyInstance(
				target.getClass()
				 .getClassLoader(), 
			 	target.getClass()
				 .getInterfaces(),
				this);
		return proxy;
	}

	// ����ִ��ҵ��ķ���
	/**
	 * proxy: ��������� method�����������ķ��� args:��������󷽷��Ĳ���
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("����ҵ��Ķ�����������������");
		System.out.println("�ȳ�һ��ңԶ����");
		// ����ҵ�񷽷�
		method.invoke(target);
		System.out.println("�����һ�׸���");
		System.out.println("������ʵ����ԭ���ܵ���չ");
		return null;
	}

	public static void main(String[] args) {
		Singer singer = new Singer();
		SingProxy sp = new SingProxy();
		BaseSinger bs = (BaseSinger) sp.bind(singer);
		bs.sing();

	}

}
