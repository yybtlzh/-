package com.yybt.datastructure.linkedlist;

/**
 * 
  * @ClassName: CylceLinkList
  * @Description:  ѭ������
  * @author liuzehong
 *
 */
public class CylceLinkList<T extends Comparable<T>> {
	// ͷ���
	public Node<T> first;

	public CylceLinkList() {
		first=new Node<>();
		first.data=null;
		first.next=first;
	}

	/**
	 * ����һ�����
	 */
	public void insert(T value) {
		Node<T> node = new Node<T>(value);
		if (first.data == null) {
			first=node;
			first.next = first;
		} else {
			Node<T> temp = first;
			while (temp.next!= first)
				temp = temp.next;
			temp.next = node;
			node.next=first;

		}
	}

	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print(first.data + "\t");
		Node<T> current = first;
		while (current.next!= first) {
			current = current.next;
			System.out.print(current.data + "\t");
		}
		System.out.println();
	}

	/**
	 * ���ҷ���
	 */
	public Node<T> find(T value) {
		Node<T> current = first.next;
		while (current.next != first) {
			if (current.data.compareTo(value) == 0) {
				break;
			}
			current = current.next;
		}
		//�������һ�����ݺ˶�
		return current.data.compareTo(value) == 0?current:null;
	}

	
	/**
	 * ɾ������������������������ɾ��
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
			Node<T> node=first;
			while(node.next!=first) {
				node=node.next;
			}
			first = first.next;
			node.next=first;
		}else if(current.next==first) {
			previous.next=first;
		} else {
			previous.next = current.next;
		}
		return current;
		
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return (first.next == first);
	}

	public static void main(String[] args) {
		CylceLinkList<String> linklist = new CylceLinkList<>();
		for (int i = 0; i < 5; i++) {
			linklist.insert("����" + i);
		}
		linklist.display();
		linklist.delete("����0");
		System.out.println(linklist.find("����4").next.data);
		linklist.display();
		linklist.display();
		Node<String> node = linklist.find("����433");
		System.out.println(node);

	}
}
