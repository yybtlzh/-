/**
 * @Title: TreeNode.java
 * @Package liu.tree
 */
package com.yybt.datastructure.tree.RBTree;

/**
  * @author liuzehong
 **/
public class TreeNode<T> {
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