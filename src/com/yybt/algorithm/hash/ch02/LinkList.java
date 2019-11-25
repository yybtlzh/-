package com.yybt.algorithm.hash.ch02;

import com.yybt.algorithm.hash.ch01.Student;

/**
 * 
 * @ClassName: LinkList
 * 
 * @date 2018��12��28��
 * 
 **/

public class LinkList {

	// ͷ���

	private Node nodeHead = null;

	/**
	 * 
	 * ��nodeHead����ڵ�
	 * 
	 */

	public void insert(Student stu) {

		Node node = new Node(stu);

		node.next = nodeHead;

		nodeHead = node;

	}

	/**
	 * 
	 * ���ҷ���
	 * 
	 */

	public Node getNodeBykey(String key) {

		Node currNode = nodeHead;

		while (!key.equals(currNode.student.getKey())) {

			if (currNode.next == null) {

				return null;

			}

			currNode = currNode.next;

		}

		return currNode;

	}

}


