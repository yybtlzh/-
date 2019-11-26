package com.yybt.datastructure.hashtable.ch02;
/*
 * 链表，相当于火车
 */
public class LinkList {
	//头结点(火车头)
	private Node head;
	
	public LinkList() {
		head = null;
	}
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertHead(Info info) {
		Node node = new Node(info);
		node.next = head;
		head = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node deletehead() {
		Node tmp = head;
		head = tmp.next;
		return tmp;
	}
	
	
	/**
	 * 查找方法
	 */
	public Node find(String key) {
		Node current = head;
		while(!key.equals(current.info.getKey())) {
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
	public Node delete(String key) {
		Node current = head;
		Node previous = head;
		while(!key.equals(current.info.getKey())) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}
		if(current == head) {
			head = head.next;
		} else {
			previous.next = current.next;
		}
		return current;
		
	}
}
