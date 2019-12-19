/**
 * @Title: MyStackByQueue.java
 * @Package com.yybt.datastructure.stack
 */
package com.yybt.datastructure.stack;

import com.yybt.datastructure.queue.MyCycleQueue;

/**
  * @ClassName: MyStackByQueue
  * @Description: 利用两个队列(必须得是循环队列)实现栈
  * @author liuzehong
 **/
public class MyStackByQueue<T> {
	
	private T top;
	
	private MyCycleQueue<T> queue1;
	
	private MyCycleQueue<T> queue2;
	
	/**
	 * 默认的构造方法
	 */
	public MyStackByQueue() {
		this(10);
	}
	
	/**
	 * 带参数构造方法，参数为数组初始化大小
	 */
	public MyStackByQueue(int maxsize) {
		queue1=new MyCycleQueue<>(maxsize);
		queue2=new MyCycleQueue<>(maxsize);
	}
	
	/**
	 * 添加数据
	 */
	public void push(T value) {
		if (!queue1.isEmpty()) {
			queue1.insert(value);
		}else {
			queue2.insert(value);
		}
		top=value;
	}
	
	/**
	 * 移除数据
	 */
	public T pop() {
		if (queue1.isEmpty()) {
			while (queue2.size()>1) {
				T remove = queue2.remove();
				queue1.insert(remove);
				
			}
			return queue2.remove();
		}else {
			while (queue1.size()>1) {
				T remove = queue1.remove();
				queue2.insert(remove);
				
			}
			return queue1.remove();
		}
		
	}
	
	/**
	 * 查看数据
	 */
	public T peek() {
		return top;
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return queue1.isEmpty()&&queue1.isEmpty();
	}
	
	/**
	 * 判断是否满了
	 */
	public boolean isFull() {
		return queue1.isFull()||queue2.isFull();
	}
	
	public static void main(String[] args) {
		MyStackByQueue<String> stack=new MyStackByQueue<>();
		 for (int i = 0; i < 10; i++) {
			 stack.push("张三"+i);
		}
		 System.out.println(stack.isFull());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		 System.out.println(stack.isFull());
		 System.out.println(stack.isEmpty());
		 stack.push("张三"+11);
		 stack.push("张三"+12);
		 stack.push("张三"+13);
		 stack.push("张三"+14);
		 stack.push("张三"+15);
		 System.out.println(stack.pop());
		 System.out.println(stack.pop());
		 System.out.println(stack.pop());
		 
		 System.out.println(stack.isFull());
		 System.out.println(stack.isEmpty());
		 
	}
	


}
