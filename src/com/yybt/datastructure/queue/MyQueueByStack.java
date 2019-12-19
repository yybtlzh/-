package com.yybt.datastructure.queue;

import com.yybt.datastructure.stack.MyStack;

/**
  * @ClassName: MyQueue
  * @Description: ��������ջʵ�ֶ���
  * @author liuzehong
 **/
public class  MyQueueByStack<T>{
	
	private int max_size;
	
	private int elements;
	
	private MyStack<T> stack1;
	
	private MyStack<T> stack2;
	
	private T front;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MyQueueByStack() {
		this(10);
	}
	
	/**
	 * �������Ĺ��췽��������Ϊ����Ĵ�С
	 */
	public MyQueueByStack(int maxsize) {
		stack1=new MyStack<>();
		stack2=new MyStack<>();
		elements=0;
		max_size=maxsize;
	}
	
	/**
	 * �������
	 */
	public void insert(T value) {
		stack1.push(value);
		elements++;
		front=value;
	}
	
	/**
	 * ɾ������
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
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	/**
	 * �ж��Ƿ�����
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
			queue.insert("����"+i);
			System.out.println(queue.peek());
		}
		System.out.println(queue.remove());
		queue.insert("����"+5);
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		queue.insert("����"+5);
		queue.insert("����"+5);
		queue.insert("����"+5);
		queue.insert("����"+5);
		queue.insert("����"+5);
	}
	
	
	
	
} 