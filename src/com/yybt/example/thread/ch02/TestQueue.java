/**
 * @Title: TestQueue.java
 * @Package com.yybt.example.thread.ch02
 */
package com.yybt.example.thread.ch02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: TestQueue
 * @Description: 测试
 * @author liuzehong
 **/
public class TestQueue {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		//交互队列
		LinkedBlockingQueue<Ticket> queue = new LinkedBlockingQueue<>();
		try {
             for (int i = 0; i < 100; i++) {
				//创建一个生产者，一个消费者
				ProviderThread<Ticket> provider = new ProviderThread<Ticket>(queue, new Ticket("票" + i, i));
				ConsumerThread<Ticket> consumer = new ConsumerThread<>(queue);
				executor.execute(provider);
				executor.execute(consumer);
			}
		} finally {
			executor.shutdown();
		}
	}

}
