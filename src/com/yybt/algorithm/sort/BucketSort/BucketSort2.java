package com.yybt.algorithm.sort.BucketSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * 通过映射到不同的桶进行桶排序，适用于分布比较均匀的一组数据
 * @author liuzehong
 *
 */
public class BucketSort2 {
	// 排序数组
	private int[] arr;
	// 桶集合
	private ArrayList<ArrayList<Integer>> buckets;
	// 桶个数
	int size;

	public BucketSort2(int size, int[] array) {
		this.arr = array;
		this.buckets = new ArrayList<>(size);
		this.size = size;
	}

	/**
	 * 
	 */
	private void sort() {
		if (arr == null || arr.length < 1) {
			return;
		}
		// 初始化桶集合
		for (int i = 0; i < size; i++) {
			buckets.add(new ArrayList<Integer>());
		}
		// 元素映射到桶集合
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i] / (100 / size + 1);
			buckets.get(num).add(arr[i]);
		}
		for (int i = 0; i < buckets.size(); i++) {
			// 如果需进行从大到小排序，可以重写compare()来实现
			Collections.sort(buckets.get(i), new Comparator<Integer>() {
				@Override
				public int compare(Integer lhs, Integer rhs) {
					if (lhs > rhs) {
						return 1;
					} else {
						return -1;
					}
				}
			});
		}
		System.out.println(buckets.toString());
	}
    

	public static void main(String[] args) {
		int[] array = { 13, 100, 20, 40, 50, 32, 44, 12, 24, 30, 44, 62, 64 };
		BucketSort2 bucketSort = new BucketSort2(5, array);
		bucketSort.sort();
	}
}