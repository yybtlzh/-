package com.yybt.example.thread.Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JobCallableTaskTest {
	
	static int  count=100;
	
	private static ExecutorService executor=Executors.newFixedThreadPool(100);
	
	private static CountDownLatch countDownLatch=new CountDownLatch(count);
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		long startTime=System.currentTimeMillis();
		/**
		 * 用于存放Future便于get()返回值;
		 */
		List<Future<Integer>> callableList = new ArrayList<Future<Integer>>(); 
		
		int i=0;
		for (int j = 0; j <count; j++) {
			Future<Integer> ft = executor.submit(new JobCallableTask(countDownLatch,i));
			callableList.add(ft);
			i++;
		}
		   countDownLatch.await();
		   
		for(Future<Integer> future : callableList){  
            try {  
             System.out.println("result："+future.get());//blocked.  
            } catch (InterruptedException | ExecutionException e) {  
                e.printStackTrace();  
            }  
        } 
		
		executor.shutdown();
		
		long endTime=System.currentTimeMillis();
		
		System.out.println(i+"个线程耗时："+(endTime-startTime)+"ms");
	}
	
	
}
