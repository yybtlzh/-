package com.yybt.datastructure.queue;

import com.yybt.datastructure.stack.MyStack;

/**
  * @ClassName: MyQueue
  * @Description: 利用两个栈实现队列
  * @author liuzehong
 **/
public class  MyQueueByStack<T>{
	
	private int max_size;
	
	private int elements;
	
	private MyStack<T> stack1;
	
	private MyStack<T> stack2;
	
	private T front;
	
	/**
	 * 默认构造方法
	 */
	public MyQueueByStack() {
		this(10);
	}
	
	/**
	 * 带参数的构造方法，参数为数组的大小
	 */
	public MyQueueByStack(int maxsize) {
		stack1=new MyStack<>();
		stack2=new MyStack<>();
		elements=0;
		max_size=maxsize;
	}
	
	/**
	 * 添加数据
	 */
	public void insert(T value) {
		stack1.push(value);
		elements++;
		front=value;
	}
	
	/**
	 * 删除数据
	 */
	public T remove() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				T data = stack1.pop();
				stack2.push(data);
			}
		}
		T pop = stack2.pop();
		elements--;
		return pop;
	}
	
	public T peek() {
		return front;
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	/**
	 * 判断是否满了
	 */
	public boolean isFull() {
		return elements == max_size;
	}
	
	public int size() {
		return elements;
		
	}
	
	
	public static void main(String[] args) {
		MyQueueByStack<String> queue=new MyQueueByStack<>();
		for (int i = 0; i < 10; i++) {
			queue.insert("张三"+i);
			System.out.println(queue.peek());
		}
		System.out.println(queue.remove());
		queue.insert("张三"+5);
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		queue.insert("张三"+5);
		queue.insert("张三"+5);
		queue.insert("张三"+5);
		queue.insert("张三"+5);
		queue.insert("张三"+5);
	}
	
	
	
	
} 