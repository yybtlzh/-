package com.yybt.example.topk;

import com.yybt.datastructure.heap.MyHeap;

/**
 * 最小堆实现topk
 * @author liuzehong
 *
 */
public class TopK {
	
	// 存储数据
	private MyHeap<Integer> myHeap;

	// 百亿数据找前k个元素。也就是tree的最大size。
	private int k;
	// 元素个数
	private int size = 0;

	public TopK(int k) {
		this.myHeap = new MyHeap<Integer>(this.k=k);
	}

	// 插入元素
	public void insert(int v) {
		// 如果size小于k，直接往里添加元素
		if (this.size < this.k) {
			myHeap.insert(v);
			size++;
			return;
		}else {
			//如果堆中最大值大于v，则删除最大值，插入v
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
	 * 测试
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
		 System.out.println("耗时："+(b-a)+"毫秒");
	}

	
}