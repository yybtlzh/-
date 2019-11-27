package com.yybt.datastructure.tree.ch01;
/**
 * 二叉树节点
 * @author liuzehong
 *
 */
public class Node {
	//数据项：相当于节点的index
	public long data;
	//数据项：具体的数据项data
	public String sData;
	//左子节点
	public Node leftChild;
	//右子节点
	public Node rightChild;
	
	/**
	 * 构造方法
	 * @param data
	 */
	public Node(long data,String sData) {
		this.data = data;
		this.sData = sData;
	}
	
}
