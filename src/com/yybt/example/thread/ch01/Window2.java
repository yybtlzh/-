package com.yybt.example.thread.ch01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟火车站多窗口售票,利用重入锁
 * 
 * @author liuzehong
 *
 */
public class Window2 extends Thread {

	// 通过构造方法给线程名字赋值
	public Window2(String name) {
		super(name);
	}

	// 为了保持票数的一致，票数要静态
	static int tick = 200;

	/**
	 * 重入锁
	 */
	private final Lock r_Lock = new ReentrantLock();

	// 重写run方法，实现买票操作
	@Override
	public void run() {
		while (tick > 0) {
			r_Lock.lock();
			// 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
			if (tick > 0) {
				System.out.println(getName() + "卖出了第" + tick-- + "张票");
			} else {
				System.out.println("票卖完了");
			}
			r_Lock.unlock();

			try {
				sleep(1000);// 休息一秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			Window2 window = new Window2("窗口" + i);
			window.start();
			/**
			 * 保证其中一个线程抢到后进行加锁
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