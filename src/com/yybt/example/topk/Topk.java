package com.yybt.example.topk;

import java.util.Arrays;
import java.util.Random;

/**
 * ��ʵ��
 * @author lx
 *
 */
public class Topk {

	private int[] data;

	private int k;

	private int size = 0;

	// ��Ч���ݵĴ�С
	// private int elements;

	public Topk(int k) {
		this.k = k;
		data = new int[k];
	}

	/**
	 * ���ظ��ڵ��±�
	 * 
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return (i - 1) >> 1;
	}

	/**
	 * ���� �����±�Ϊi ,j����Ԫ��
	 * 
	 * @param i
	 * @param j
	 */
	private void exChange(int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	/**
	 * �ϸ�
	 * 
	 * @param index
	 */
	public void shift_up(int index) {
		// ���ڵ㲻�����ϸ�����
		if (index > 0) {
			int parent = parent(index);
			// ������׽ڵ��index����ֵС���ͽ������ߵ���ֵ
			if (data[parent] < data[index]) {
				// �������ӽڵ�
				exChange(parent, index);
				// �ݹ����
				shift_up(parent);
			}
		}
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public int[] insert(int value) {
		// ����һ���ڵ�
		if (size < k) {
			data[size++] = value;
			shift_up(size - 1);
		}
		return data;
	}

	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public int[] delete(int index) {
		if (index + 1 > size)
			return data;
		exChange(index, size - 1);
		shift_down(index);
		data[--size] = 0;
		return data;
	}

	/**
	 * �³�
	 * 
	 * @param index
	 */
	public void shift_down(int index) {
		// �³����ȱ�����ҵ�����ӽڵ�
		int n = index << 1;
		// û�к��ӽڵ�
		if (n + 3 > size)
			return;
		// ����child�����洢����ӽڵ��±�
		int child = -1;
		// ����������
		if (n + 2 < size) {
			child = n + 1;
			if (data[n + 1] < data[n + 2]) {
				++child;
			}
		}
		// ������
		else if ((n + 2) == size) {
			child = n + 1;
		}
		// �Ƚ�
		if (data[index] < data[child]) {
			exChange(index, child);
			// �ݹ����
			shift_down(child);
		}
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public int[] add(int value) {
		// ����һ���ڵ�
		if (size < k) {
			insert(value);
		} else {
			if (data[0] > value) {
				delete(0);
				insert(value);
			}
		}
		return data;
	}

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		Topk topk = new Topk(10);
		Random rd=new Random();
		for (int i = 1000000; i >= 1; i--) {
			topk.add(rd.nextInt(1000000));
		}
		System.out.println(Arrays.toString(topk.data));
		long b = System.currentTimeMillis();
		System.out.println("��ʱ��" + (b - a) + "����");
	}

}