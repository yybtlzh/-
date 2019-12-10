package com.yybt.datastructure.stack;

import java.util.Vector;

/**
 *  ջ�����÷��ͣ�
 * @author liuzehong
 *
 */
public class MyStack<T> {
	
	private int top;
	private Vector<T> data;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public MyStack() {
		this(10);
	}
	
	/**
	 * ���������췽��������Ϊ�����ʼ����С
	 */
	public MyStack(int maxsize) {
		data=new Vector<>(maxsize);
		top = -1;
	}
	
	/**
	 * �������
	 */
	public void push(T value) {
		data.add(++top, value);
	}
	
	/**
	 * �Ƴ�����
	 */
	public T pop() {
		return data.remove(top--);
	}
	
	/**
	 * �鿴����
	 */
	public T peek() {
		return data.get(top);
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * �ж��Ƿ�����
	 */
	public boolean isFull() {
		return top == data.size()-1;
	}
	

}
