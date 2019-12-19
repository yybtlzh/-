/**
 * @Title: ConsumerThread.java
 * @Package com.yybt.example.thread.ch02
 */
package com.yybt.example.thread.ch02;

import java.util.concurrent.LinkedBlockingQueue;

/**
  * @ClassName: ConsumerThread
  * @Description: 模拟消费者
  * @author liuzehong
 **/
public class ConsumerThread <T> extends Thread {
	
	public  LinkedBlockingQueue<T> queue;

	public ConsumerThread(LinkedBlockingQueue<T> queue) {  
        this.queue = queue;
       
    }  
  
    @Override  
    public void run() {  
        try {  
            Thread.sleep(1000);//业务处理时间  
            T take = queue.take();
            System.out.println("\t消费者买到票："+take);
        } catch (InterruptedException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
} 
	 