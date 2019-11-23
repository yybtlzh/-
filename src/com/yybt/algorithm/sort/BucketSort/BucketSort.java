package com.yybt.algorithm.sort.BucketSort;

/**
 * 认识桶排序
 * @author liuzehong
 *
 */
public class BucketSort {
	// 桶
	int[] buckets;
	// 排序数组
	int[] arr;
	/*
	 * size:桶的个数 array:排序数组
	 */
	public BucketSort(int size, int[] array) {
		this.buckets = new int[size];
		this.arr = array;
	}

	public void sort() {
		if (arr != null && arr.length > 1) {
			for (int i = 0; i < arr.length; i++) {
				//遍历数组,桶负责进行计数
				buckets[arr[i]-90]++;
			}
			for (int i = 0; i < buckets.length; i++) {
				for (int j = 0; j < buckets[i]; j++) {
					  System.out.print((i+90)+" ");
				}
			}
		}
	}
	public static void main(String[] args) {
		int[] array = { 94, 95, 94, 97, 97, 95, 98, 92, 93, 93, 98, 92, 95, 96, 100 };
		BucketSort bucketSort = new BucketSort(11, array);
		bucketSort.sort();
	}
}