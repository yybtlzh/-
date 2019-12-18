package com.yybt.datastructure.linkedlist;

/**
 * 链结点，相当于是车厢
 * @author liuzehong
 *
 */

public class Node<T> {
	//数据域
	public T data;
	//指针域
	public Node<T> next;
	public Node<T> previous;
	
	public Node(T value) {
		this.data = value;
	}
	
	/**
	  * 创建一个新的实例 Node. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  */
	public Node() {
	}
	
	public boolean hasNext() {
		return next!=null;
	} 

	/**
	 * 显示方法
	 */
	public void display() {
		System.out.print(data + " ");
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + ", previous=" + previous + "]";
	}
	
	
}
