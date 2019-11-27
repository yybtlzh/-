package com.yybt.example.topk;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆实现
 * @author lx
 *
 */
public class Topk {

	private int[] data;

	private int k;

	private int size = 0;

	// 有效数据的大小
	// private int elements;

	public Topk(int k) {
		this.k = k;
		data = new int[k];
	}

	/**
	 * 返回父节点下标
	 * 
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return (i - 1) >> 1;
	}

	/**
	 * 交换 数组下标为i ,j的俩元素
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
	 * 上浮
	 * 
	 * @param index
	 */
	public void shift_up(int index) {
		// 根节点不存在上浮操作
		if (index > 0) {
			int parent = parent(index);
			// 如果父亲节点比index的数值小，就交换二者的数值
			if (data[parent] < data[index]) {
				// 交换父子节点
				exChange(parent, index);
				// 递归调用
				shift_up(parent);
			}
		}
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public int[] insert(int value) {
		// 新增一个节点
		if (size < k) {
			data[size++] = value;
			shift_up(size - 1);
		}
		return data;
	}

	/**
	 * 删除数据
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
	 * 下沉
	 * 
	 * @param index
	 */
	public void shift_down(int index) {
		// 下沉首先必须得找到最大子节点
		int n = index << 1;
		// 没有孩子节点
		if (n + 3 > size)
			return;
		// 定义child用来存储最大子节点下标
		int child = -1;
		// 有两个孩子
		if (n + 2 < size) {
			child = n + 1;
			if (data[n + 1] < data[n + 2]) {
				++child;
			}
		}
		// 有左孩子
		else if ((n + 2) == size) {
			child = n + 1;
		}
		// 比较
		if (data[index] < data[child]) {
			exChange(index, child);
			// 递归调用
			shift_down(child);
		}
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public int[] add(int value) {
		// 新增一个节点
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
		System.out.println("耗时：" + (b - a) + "毫秒");
	}

}