package com.yybt.datastructure.linkedlist;

/**
 * 双向链表
 * @author liuzehong
 *
 */
public class DoubleLinkList<T extends Comparable<T>> {
	//头结点
	protected Node<T> first;
	//尾结点
	private Node<T> last;
	
	public DoubleLinkList() {
		first = null;
		last = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			last = node;
		} else {
			first.previous = node;
		}
		node.next = first;
		first = node;
	}
	
	/**
	 * 插入一个结点，从尾结点进行插入
	 */
	public void insertLast(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			first = node;
		} else {
			last.next = node;
			node.previous = last;
		}
		last = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node<T> deleteFirst() {
		Node<T> tmp = first;
		if(!first.hasNext()) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * 删除结点，从尾部进行删除
	 */
	public Node<T>deleteLast() {
		//Node<T> tmp = last;
		if(!first.hasNext()) {
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return last;
	}
	
	/**
	 * 显示方法
	 */
	public void display() {
		Node<T> current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 查找方法
	 */
	public Node<T> find(T value) {
		Node<T> current = first;
		while(current.data .compareTo(value)!=0) {
			if(!current.hasNext()) {
				return null;
			}
			current = current.next;
		}
		return current;
	}
	
	/**
	 * 删除方法，根据数据域来进行删除
	 */
	public Node<T> delete(T value) {
		Node<T> current = first;
		while(current.data.compareTo(value)!=0) {
			if(!current.hasNext()) {
				return null;
			}
			current = current.next;
		}
		
		if(current == first) {
			first = first.next;
		} else {
			current.previous.next = current.next;
		}
		return current;
		
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
