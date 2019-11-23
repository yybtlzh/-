package com.yybt.example.thread.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替执行打印 1~100
 * @author liuzehong
 */
public class TwoThread {

    private int i = 1;

    /**
     * volatile保证了flag对所有线程的可见性，这点很重要
     */
    private  volatile  boolean flag = false;

    /**
     * 重入锁
     */
    private final Lock r_Lock = new ReentrantLock();

    public static void main(String[] args) {
    	
        TwoThread twoThread = new TwoThread();
        //保证你每次调用都从奇数开始打印
        twoThread.flag=false;
        Thread thread1 = new Thread(twoThread.new Thread1(twoThread));
        thread1.setName("线程1");
        Thread thread2 = new Thread(twoThread.new Thread2(twoThread));
        thread2.setName("线程2");
        thread1.start();
        thread2.start();
    }

    /**
     * 线程1
     */
    public class Thread1 implements Runnable {
    	
        private TwoThread twoThread;

        public Thread1(TwoThread twoThread) {
            this.twoThread = twoThread;
        }
        
        @Override
        public void run() {
            while (twoThread.i <= 1000) {
                if (twoThread.flag) {
                    try {
                    	r_Lock.lock();
                        System.out.println(Thread.currentThread().getName() + "   " + twoThread.i++);
                        twoThread.flag = false;
                    } finally {
                    	r_Lock.unlock();
                    }
                }
            }
        }
    }

    /**
     * 线程2
     */
    public  class Thread2 implements Runnable {

        private TwoThread twoThread;

        public Thread2(TwoThread twoThread) {
            this.twoThread = twoThread;
        }

        @Override
        public void run() {
            while (twoThread.i <= 1000) {

                if (!twoThread.flag) {
                    try {
                    	r_Lock.lock();
                        System.out.println(Thread.currentThread().getName() + "  " + twoThread.i++);
                        twoThread.flag = true;
                    } finally {
                    	r_Lock.unlock();
                    }
                }
            }
        }
    }
}
