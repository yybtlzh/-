/**
 * @Title: MyStackByQueue.java
 * @Package com.yybt.datastructure.stack
 */
package com.yybt.datastructure.stack;

import com.yybt.datastructure.queue.MyCycleQueue;

/**
  * @ClassName: MyStackByQueue
  * @Description: ������������(�������ѭ������)ʵ��ջ
  * @author liuzehong
 **/
public class MyStackByQueue<T> {
	
	private T top;
	
	private MyCycleQueue<T> queue1;
	
	private MyCycleQueue<T> queue2;
	
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public MyStackByQueue() {
		this(10);
	}
	
	/**
	 * ���������췽��������Ϊ�����ʼ����С
	 */
	public MyStackByQueue(int maxsize) {
		queue1=new MyCycleQueue<>(maxsize);
		queue2=new MyCycleQueue<>(maxsize);
	}
	
	/**
	 * �������
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
	 * �Ƴ�����
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
	 * �鿴����
	 */
	public T peek() {
		return top;
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return queue1.isEmpty()&&queue1.isEmpty();
	}
	
	/**
	 * �ж��Ƿ�����
	 */
	public boolean isFull() {
		return queue1.isFull()||queue2.isFull();
	}
	
	public static void main(String[] args) {
		MyStackByQueue<String> stack=new MyStackByQueue<>();
		 for (int i = 0; i < 10; i++) {
			 stack.push("����"+i);
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
		 stack.push("����"+11);
		 stack.push("����"+12);
		 stack.push("����"+13);
		 stack.push("����"+14);
		 stack.push("����"+15);
		 System.out.println(stack.pop());
		 System.out.println(stack.pop());
		 System.out.println(stack.pop());
		 
		 System.out.println(stack.isFull());
		 System.out.println(stack.isEmpty());
		 
	}
	


}
