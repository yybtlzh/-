package com.yybt.design.proxy.ch02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.yybt.design.proxy.ch01.BaseSinger;
import com.yybt.design.proxy.ch01.Singer;

/**
 * 动态代理
 * @author liuzehong
 *
 */
public class SingProxy implements InvocationHandler {
	
	// 声明被代理对象（之所以用Object是因为他在Java中是所有类包含自定义类的超类）
	private Object target;

	// 创建代理对象（根据被代理对象需求）
	public Object bind(Object target) {
		this.target = target;
		// 使用Proxy类创建一个动态代理对象
		Object proxy = Proxy.newProxyInstance(
				target.getClass()
				 .getClassLoader(), 
			 	target.getClass()
				 .getInterfaces(),
				this);
		return proxy;
	}

	// 具体执行业务的方法
	/**
	 * proxy: 代理类对象 method：被代理对象的方法 args:被代理对象方法的参数
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("具体业务改动就在这个方法里进行");
		System.out.println("先唱一首遥远的她");
		// 调用业务方法
		method.invoke(target);
		System.out.println("最后来一首浮夸");
		System.out.println("这样就实现了原功能的扩展");
		return null;
	}

	public static void main(String[] args) {
		Singer singer = new Singer();
		SingProxy sp = new SingProxy();
		BaseSinger bs = (BaseSinger) sp.bind(singer);
		bs.sing();

	}

}
