package com.yybt.datastructure.linkedlist;

/**
 * ����㣬�൱���ǳ���
 * @author liuzehong
 *
 */

public class Node {
	//������
	public long data;
	//ָ����
	public Node next;
	public Node previous;
	
	public Node(long value) {
		this.data = value;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print(data + " ");
	}
}
