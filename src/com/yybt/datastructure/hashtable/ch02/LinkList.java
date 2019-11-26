package com.yybt.datastructure.hashtable.ch02;
/*
 * �����൱�ڻ�
 */
public class LinkList {
	//ͷ���(��ͷ)
	private Node head;
	
	public LinkList() {
		head = null;
	}
	/**
	 * ����һ����㣬��ͷ������в���
	 */
	public void insertHead(Info info) {
		Node node = new Node(info);
		node.next = head;
		head = node;
	}
	
	/**
	 * ɾ��һ����㣬��ͷ�������ɾ��
	 */
	public Node deletehead() {
		Node tmp = head;
		head = tmp.next;
		return tmp;
	}
	
	
	/**
	 * ���ҷ���
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
	 * ɾ������������������������ɾ��
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
