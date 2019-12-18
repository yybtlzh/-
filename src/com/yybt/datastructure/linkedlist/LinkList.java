package com.yybt.datastructure.linkedlist;

/**
 * 链表，相当于火车
 * @author liuzehong
 *
 */
public class LinkList<T  extends Comparable<T>> {
	//头结点
	private Node<T> first;
	
	public LinkList() {
		first = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(T value) {
		Node<T> node = new Node<T>(value);
		node.next = first;
		first = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node<T> deleteFirst() {
		Node<T> tmp = first;
		first = tmp.next;
		return tmp;
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
		while(current.data.compareTo(value) !=0 ) {
			if(current.next == null) {
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
		Node<T> previous = first;
		while(current.data .compareTo(value)!=0) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}
		
		if(current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;
		
	}
	
	public static void main(String[] args) {
		LinkList<Integer> linkList = new LinkList<>();
		linkList.insertFirst(34);
		linkList.insertFirst(23);
		linkList.insertFirst(12);
		linkList.insertFirst(0);
		linkList.insertFirst(-1);
		
		 linkList.delete(34);
		System.out.println(linkList.find(12));
		linkList.display();
	}
	

}
