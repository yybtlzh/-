package com.yybt.design.singletonmode;

/**
 * 单例模式
 * @author lx
 *
 */
public class Singleton{

	private Singleton() {
	}

	private static class SingletonBuild {
		private static Singleton value = new Singleton();
	}

	public Singleton getInstance() {
		return SingletonBuild.value;
	}

}