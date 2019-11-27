package com.yybt.datastructure.tree.ch01;

/**
 * 二叉树节点
 * 
 * @author liuzehong
 *
 */
public class Node {
	// 数据项
	public int data;
	// 左子节点
	public Node leftChild;
	// 右子节点
	public Node rightChild;

	/**
	 * 构造方法
	 * 
	 * @param data
	 */
	public Node(int data) {
		this.data = data;
	}

}