package com.yybt.datastructure.tree.ch01;
/**
 * �������ڵ�
 * @author liuzehong
 *
 */
public class Node {
	//������൱�ڽڵ��index
	public long data;
	//����������������data
	public String sData;
	//���ӽڵ�
	public Node leftChild;
	//���ӽڵ�
	public Node rightChild;
	
	/**
	 * ���췽��
	 * @param data
	 */
	public Node(long data,String sData) {
		this.data = data;
		this.sData = sData;
	}
	
}
