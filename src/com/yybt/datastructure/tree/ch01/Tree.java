package com.yybt.datastructure.tree.ch01;

/**
 * 二叉树
 * 
 * @author liuzehong
 *
 */
public class Tree {
	// 根节点
	public Node root;

	/**
	 * 插入节点
	 * 
	 * @param value
	 */
	public void insert(int value) {
		// 封装节点
		Node newNode = new Node(value);
		// 引用当前节点
		Node current = root;
		// 引用父节点
		Node parent;
		// 如果root为null，也就是第一插入的时候
		if (root == null) {
			root = newNode;
			return;
		} else {
			while (true) {
				// 父节点指向当前节点
				parent = current;
				// 如果当前指向的节点数据比插入的要大,则向左走
				if (current.data > value) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * 删除节点
	 * 
	 * @param value
	 */
	public boolean delete(int value) {
		// 引用当前节点，从根节点开始
		Node current = root;

		// 应用当前节点的父节点
		Node parent = root;
		// 是否为左节点
		boolean isLeftChild = true;

		while (current.data != value) {
			parent = current;
			// 进行比较，比较查找值和当前节点的大小
			if (current.data > value) {
				current = current.leftChild;
				isLeftChild = true;
			} else {
				current = current.rightChild;
				isLeftChild = false;
			}
			// 如果查找不到
			if (current == null) {
				return false;
			}
		}

		// 删除叶子节点，也就是该节点没有子节点
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		} else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		} else {
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}

		return true;

	}

	private Node getSuccessor(Node delNode) {
		Node successor = delNode;
		Node successorParent = delNode;
		Node current = delNode.rightChild;

		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}

		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

}