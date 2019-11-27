package com.yybt.example.topk;

import java.util.Arrays;
import java.util.Random;

import com.yybt.datastructure.tree.ch01.Node;
import com.yybt.datastructure.tree.ch01.Tree;

/**
 * ������ʵ��
 * @author lx
 *
 */
public class TopK2 {
	// �洢����
	private Tree tree;

	private int minV = -1;
	// ����������ǰk��Ԫ�ء�Ҳ����tree�����size��
	private int k;
	// Ԫ�ظ���
	private int size = 0;
	// �洢��ӡ����
	private int[] arr;

	public TopK2(int k) {
		this.tree = new Tree();
		arr = new int[k];
		this.k = k;
	}

	// ����Ԫ��
	public void insert(int v) {
		// ���sizeС��k��ֱ���������Ԫ��
		if (this.size < this.k) {
			tree.insert(v);
			// �������һ��Ԫ�أ�minV����ʼֵ
			if (++size == 1) {
				minV = v;
			} else {
				// �������Ԫ��С��minV,����minV
				if (minV > v) {
					minV = v;
				}
			}
			return;
		}
		// �����СֵС�ڲ����Ԫ�أ�ɾ����С��Ԫ�أ������µ�Ԫ�أ������ҳ����ϵ� ��СԪ�ظ�ֵ��minV
		if (minV < v) {
			// ɾ����Сֵ
			tree.delete(minV);
			// ���Ԫ��
			tree.insert(v);
			// ����Сֵ����ֵ
			minV = getMinV(tree.root);
		}

	}

	/**
	 * ��ȡ ��СԪ��ֵ��ֵ��minV
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
	 * ��ӡ����
	 */
	public void print() {
		inOrder(tree.root);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * �������
	 */
	private void inOrder(Node localNode) {
		if (localNode != null) {
			// �������������
			inOrder(localNode.leftChild);
			// ���ʸ��ڵ�
			arr[--k] = localNode.data;
			// ��Ѯ����������
			inOrder(localNode.rightChild);
		}
	}

	
	/**
	 * ���������������ʵ
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
		 System.out.println("��ʱ��"+(b-a)+"����");
		 }
}