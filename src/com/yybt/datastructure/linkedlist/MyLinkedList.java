package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: MyLinkedList
  * @Description: 
  * @author liuzehong
  *  ���� 1-��2-��3����4  ����3��������Ϊ1-��2-��4����3 ����1 ��������Ϊ2-��4����3-��1
 **/
public class MyLinkedList <T extends Comparable<T>>{

	//ͷ���
	protected Node<T> first;
	//β���
	private Node<T> last;
	
	public MyLinkedList() {
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
		} else {
			first.previous = node;
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
			node.previous = last;
		}
		last = node;
	}
	
	/**
	 * ɾ��һ����㣬��ͷ�������ɾ����ͬʱҲ����last����Ԫ�ء�
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
	 * ɾ����㣬��β������ɾ����ͬʱҲ���������Ԫ�ء�
	 */
	public Node<T>deleteLast() {
		if(!first.hasNext()) {
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return last;
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
		while(current.data .compareTo(value)!=0) {
			if(!current.hasNext()) {
				return null;
			}
			current = current.next;
		}
		if (current==last) {
		}else if(current==first){
			first=current.next;
			current.next.previous=first;
			last.next=current;
			last=current;
			last.next=null;
		}else  {
			current.previous.next=current.next;
			current.next.previous=current.previous;
			last.next=current;
			last=current;
			last.next=null;
		}
		return current;
	}
	
	/**
	 * ɾ������������������������ɾ��
	 */
	public Node<T> delete(T value) {
		Node<T> current = first;
		while(current.data.compareTo(value)!=0) {
			if(current.next == null) {
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
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return (first == null);
	}

	
	public static void main(String[] args) {
		MyLinkedList<String> linkList = new MyLinkedList<>();
		linkList.insertFirst("����");
		linkList.insertFirst("����");
		linkList.insertFirst("����");
		linkList.insertFirst("����");
		linkList.insertFirst("����");
		linkList.display();
		System.out.println("last��    "+linkList.last.data);
		linkList.find("����");
		//linkList.display();
		System.out.println("last��    "+linkList.last.data);
		linkList.find("����");
		System.out.println("last��    "+linkList.last.data);
		//linkList.display();
		linkList.find("����");
		System.out.println("last��    "+linkList.last.data);
		//linkList.display();
	}
	
	
}
