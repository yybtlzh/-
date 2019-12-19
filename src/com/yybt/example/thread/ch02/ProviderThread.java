/**
 * @Title: ProviderThread.java
 * @Package com.yybt.example.thread.ch02
 */
package com.yybt.example.thread.ch02;

import java.util.concurrent.LinkedBlockingQueue;

/**
  * @ClassName: ProviderThread
  * @Description:ģ��������
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
            Thread.sleep(200);//��������ʱ��  
            queue.put(T); 
            System.out.println("������Ʊ��"+T);
        } catch (InterruptedException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    
    
	 
	 
}
