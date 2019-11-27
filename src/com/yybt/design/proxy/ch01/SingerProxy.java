package com.yybt.design.proxy.ch01;

/**
 * ��̬����
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
		System.out.println("����һ��һ˿����");
		singer.sing();
		System.out.println("�����һ��ʮ��");
	}
	
	public static void main(String[] args) {
		new SingerProxy(new Singer()).sing();
		System.out.println("ʵ���˶�Singer����չ");
	}

}
