package com.yybt.example.thread.Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class JobCallableTask<T> implements Callable<Integer> {
	
	private  Integer  tVData;
	
	private CountDownLatch countDownLatch;
	
	private  Integer  result=null;
	
	public JobCallableTask() {
		super();
	}

	public JobCallableTask(CountDownLatch countDownLatch,Integer i) {
		this.countDownLatch = countDownLatch;
		this.tVData=i;
	}
	
    /**
     * 具体业务处理
     */
	@Override
	public Integer call() throws Exception {
		Thread.sleep(100);
		countDownLatch.countDown();
		result=tVData;
		return result;
		
	}
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);  
		
	    List<Future<String>> callableList = new ArrayList<Future<String>>();
	    
		Callable<Integer> myCallable = new JobCallableTask();    // 创建MyCallable对象
		FutureTask<Integer> ft = new FutureTask<Integer>(myCallable); //使用FutureTask来包装MyCallable对象
		Thread thread = new Thread(ft);   //FutureTask对象作为Thread对象的target创建新的线程
		thread.start();                      //线程进入到就绪状态
		try {
		   Integer integer = ft.get();          //取得新创建的新线程中的call()方法返回的结果
			System.out.println("vData = " + integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
             System.out.println("end");
	}

}
