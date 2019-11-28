package com.yybt.example.thread.ch01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ģ���վ�ര����Ʊ,����������
 * 
 * @author liuzehong
 *
 */
public class Window2 extends Thread {

	// ͨ�����췽�����߳����ָ�ֵ
	public Window2(String name) {
		super(name);
	}

	// Ϊ�˱���Ʊ����һ�£�Ʊ��Ҫ��̬
	static int tick = 200;

	/**
	 * ������
	 */
	private final Lock r_Lock = new ReentrantLock();

	// ��дrun������ʵ����Ʊ����
	@Override
	public void run() {
		while (tick > 0) {
			r_Lock.lock();
			// ��ȥ���˻��Կ���������ϣ�������Ű�Կ�����ó���
			if (tick > 0) {
				System.out.println(getName() + "�����˵�" + tick-- + "��Ʊ");
			} else {
				System.out.println("Ʊ������");
			}
			r_Lock.unlock();

			try {
				sleep(1000);// ��Ϣһ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			Window2 window = new Window2("����" + i);
			window.start();
			/**
			 * ��֤����һ���߳���������м���
			 */
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}