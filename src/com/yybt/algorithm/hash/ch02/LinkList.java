package com.yybt.algorithm.hash.ch02;

import com.yybt.algorithm.hash.ch01.Student;

/**
 * 
 * @ClassName: LinkList
 * 
 * @date 2018年12月28日
 * 
 **/

public class LinkList {

	// 头结点

	private Node nodeHead = null;

	/**
	 * 
	 * 从nodeHead插入节点
	 * 
	 */

	public void insert(Student stu) {

		Node node = new Node(stu);

		node.next = nodeHead;

		nodeHead = node;

	}

	/**
	 * 
	 * 查找方法
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


