package com.yybt.example.topk;

import java.util.Arrays;
import java.util.Random;

import com.yybt.datastructure.tree.ch01.Node;
import com.yybt.datastructure.tree.ch01.Tree;

/**
 * 二叉树实现
 * @author lx
 *
 */
public class TopK2 {
	// 存储数据
	private Tree tree;

	private int minV = -1;
	// 百亿数据找前k个元素。也就是tree的最大size。
	private int k;
	// 元素个数
	private int size = 0;
	// 存储打印数据
	private int[] arr;

	public TopK2(int k) {
		this.tree = new Tree();
		arr = new int[k];
		this.k = k;
	}

	// 插入元素
	public void insert(int v) {
		// 如果size小于k，直接往里添加元素
		if (this.size < this.k) {
			tree.insert(v);
			// 当插入第一个元素，minV赋初始值
			if (++size == 1) {
				minV = v;
			} else {
				// 当插入的元素小于minV,更新minV
				if (minV > v) {
					minV = v;
				}
			}
			return;
		}
		// 如果最小值小于插入的元素，删除最小的元素，插入新的元素，并查找出树上的 最小元素赋值给minV
		if (minV < v) {
			// 删除最小值
			tree.delete(minV);
			// 添加元素
			tree.insert(v);
			// 给最小值赋新值
			minV = getMinV(tree.root);
		}

	}

	/**
	 * 获取 最小元素值赋值给minV
	 * 
	 */
	public int getMinV(Node node) {
		Node current = node;
		while (!(current.leftChild == null)) {
			current = current.leftChild;
		}
		return current.data;
	}

	/**
	 * 打印数据
	 */
	public void print() {
		inOrder(tree.root);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 中序遍历
	 */
	private void inOrder(Node localNode) {
		if (localNode != null) {
			// 中序遍历左子树
			inOrder(localNode.leftChild);
			// 访问根节点
			arr[--k] = localNode.data;
			// 中旬遍历右子树
			inOrder(localNode.rightChild);
		}
	}

	
	/**
	 * 插入随机数，更真实
	 * @param args
	 */
	public static void main(String[] args) {
		 long a=System.currentTimeMillis();
		 TopK2 topK = new TopK2(10);
		 Random rand = new Random();
		 for (int i = 10000; i >= 1; i--) {
		 topK.insert(rand.nextInt(10000));
		 }
		 topK.print();
		 long b=System.currentTimeMillis();
		 System.out.println("耗时："+(b-a)+"毫秒");
		 }
}