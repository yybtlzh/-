package com.yybt.datastructure.stack;

import java.util.Vector;

/**
 *  栈（改用泛型）
 * @author liuzehong
 *
 */
public class MyStack<T> {
	
	private int top;
	private Vector<T> data;
	
	/**
	 * 默认的构造方法
	 */
	public MyStack() {
		this(10);
	}
	
	/**
	 * 带参数构造方法，参数为数组初始化大小
	 */
	public MyStack(int maxsize) {
		data=new Vector<>(maxsize);
		top = -1;
	}
	
	/**
	 * 添加数据
	 */
	public void push(T value) {
		data.add(++top, value);
	}
	
	/**
	 * 移除数据
	 */
	public T pop() {
		return data.remove(top--);
	}
	
	/**
	 * 查看数据
	 */
	public T peek() {
		return data.get(top);
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 判断是否满了
	 */
	public boolean isFull() {
		return top == data.size()-1;
	}
	

}
