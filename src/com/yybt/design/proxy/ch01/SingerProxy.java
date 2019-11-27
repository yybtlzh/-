package com.yybt.design.proxy.ch01;

/**
 * 静态代理
 * @author liuzehong
 *
 */
public class SingerProxy implements BaseSinger {

	Singer singer;

	public SingerProxy(Singer singer) {
		this.singer = singer;
	}

	@Override
	public void sing() {
		System.out.println("先来一首一丝不挂");
		singer.sing();
		System.out.println("最后来一首十年");
	}
	
	public static void main(String[] args) {
		new SingerProxy(new Singer()).sing();
		System.out.println("实现了对Singer的扩展");
	}

}
