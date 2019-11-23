/**
 * @Title: TreeNode.java
 * @Package liu.tree
 */
package com.yybt.datastructure.tree.RBTree;

/**
  * @author liuzehong
 **/
public class TreeNode<T> {
   /**
    * （1）每个节点或者是黑色，或者是红色。
    * （2）根节点是黑色。
    * （3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
    * （4）如果一个节点是红色的，则它的子节点必须是黑色的。
    * （5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
    */
	private static final boolean RED   = false;
	    
	private static final boolean BLACK = true;
		
	private boolean color;        // 颜色               red:true    black：false  
	
	private T data;               //数据
	
	private TreeNode<T> leftChild;          //左孩子
	
	private TreeNode<T> rightChild;       //又孩子
	
	private TreeNode<T> parentNode;    //双亲节点
	
	public TreeNode() {
		super();
	}


	public TreeNode(T data, boolean color, TreeNode<T> parentNode, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        this.data = data;
        this.color = color;
        this.parentNode = parentNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
	
	public TreeNode(T data) {
		this.data = data;
	}


	public boolean getColor() {
		return color;
	}


	public void setColor(boolean color) {
		this.color = color;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public TreeNode<T> getLeftChild() {
		return leftChild;
	}


	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}


	public TreeNode<T> getRightChild() {
		return rightChild;
	}


	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}


	public TreeNode<T> getParentNode() {
		return parentNode;
	}


	public void setParentNode(TreeNode<T> parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public String toString() {
		return "TreeNode [color=" + color + ", data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild
				+ ", parentNode=" + parentNode + "]";
	}
	
}