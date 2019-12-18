package com.yybt.example.topk;

import com.yybt.datastructure.heap.MyHeap;

/**
 * ��С��ʵ��topk
 * @author liuzehong
 *
 */
public class TopK {
	
	// �洢����
	private MyHeap<Integer> myHeap;

	// ����������ǰk��Ԫ�ء�Ҳ����tree�����size��
	private int k;
	// Ԫ�ظ���
	private int size = 0;

	public TopK(int k) {
		this.myHeap = new MyHeap<Integer>(this.k=k);
	}

	// ����Ԫ��
	public void insert(int v) {
		// ���sizeС��k��ֱ���������Ԫ��
		if (this.size < this.k) {
			myHeap.insert(v);
			size++;
			return;
		}else {
			//����������ֵ����v����ɾ�����ֵ������v
			if (myHeap.get(0) >v) {
				myHeap.delete(0);
				myHeap.insert(v);
			}
		}

	}
	
	public void show() {
		myHeap.show();
	}
	
	
	
	/**
	 * ����
	 * @param args
	 */
	public static void main(String[] args) {
		 long a=System.currentTimeMillis();
		 TopK topK = new TopK(10);
		 for (int i = 10000; i >= 1; i--) {
		   topK.insert(i);
		 }
		   topK.show();
		 long b=System.currentTimeMillis();
		 System.out.println("��ʱ��"+(b-a)+"����");
	}

	
}