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
     * ����ҵ����
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
	    
		Callable<Integer> myCallable = new JobCallableTask();    // ����MyCallable����
		FutureTask<Integer> ft = new FutureTask<Integer>(myCallable); //ʹ��FutureTask����װMyCallable����
		Thread thread = new Thread(ft);   //FutureTask������ΪThread�����target�����µ��߳�
		thread.start();                      //�߳̽��뵽����״̬
		try {
		   Integer integer = ft.get();          //ȡ���´��������߳��е�call()�������صĽ��
			System.out.println("vData = " + integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
             System.out.println("end");
	}

}
