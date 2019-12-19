/**
 * @Title: ProviderThread.java
 * @Package com.yybt.example.thread.ch02
 */
package com.yybt.example.thread.ch02;

import java.util.concurrent.LinkedBlockingQueue;

/**
  * @ClassName: ProviderThread
  * @Description:模拟生产者
  * @author liuzehong
 **/
public class ProviderThread<T> extends Thread {
	
	public  LinkedBlockingQueue<T> queue;

	private T T;

    
    public ProviderThread(LinkedBlockingQueue<T> queue, T t) {  
        this.queue = queue; 
        this.T =t;
    }  
  
    @Override  
    public void run() {  
        try {  
            Thread.sleep(200);//生产处理时间  
            queue.put(T); 
            System.out.println("机器出票："+T);
        } catch (InterruptedException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    
	 
	 
}
