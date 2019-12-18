package com.yybt.datastructure.linkedlist;

/**
 * ˫������
 * @author liuzehong
 *
 */
public class FirstLastLinkList<T extends Comparable<T>> {
	//ͷ���
	protected Node<T> first;
	//β���
	protected Node<T> last;
	
	public FirstLastLinkList() {
		first = null;
		last = null;
	}
	
	/**
	 * ����һ����㣬��ͷ������в���
	 */
	public void insertFirst(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			last = node;
		}
		node.next = first;
		first = node;
	}
	
	/**
	 * ����һ����㣬��β�����в���
	 */
	public void insertLast(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
	}
	
	/**
	 * ɾ��һ����㣬��ͷ�������ɾ��
	 */
	public Node<T> deleteFirst() {
		Node<T> tmp = first;
		if(!first.hasNext()) {
			last = null;
		}
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * ��ʾ����
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
	 * ���ҷ���
	 */
	public Node<T> find(T value) {
		
		Node<T> current = first;
		while(current!=null&&current.data.compareTo(value)!=0) {
			if(!current.hasNext()) {
				return null;
			}
			current = current.next;
		}
		return current;
	}
	
	/**
	 * ɾ������������������������ɾ��
	 */
	public Node<T> delete(T value) {
		Node<T> current = first;
		Node<T> previous = first;
		while(current!=null&&current.data.compareTo(value)!=0) {
			if(!current.hasNext()) {
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
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return (first == null);
	}
}
