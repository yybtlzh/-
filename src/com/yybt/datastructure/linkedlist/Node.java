package com.yybt.datastructure.linkedlist;

/**
 * ����㣬�൱���ǳ���
 * @author liuzehong
 *
 */

public class Node<T> {
	//������
	public T data;
	//ָ����
	public Node<T> next;
	public Node<T> previous;
	
	public Node(T value) {
		this.data = value;
	}
	
	/**
	  * ����һ���µ�ʵ�� Node. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public Node() {
	}
	
	public boolean hasNext() {
		return next!=null;
	} 

	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print(data + " ");
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + ", previous=" + previous + "]";
	}
	
	
}
