package com.yybt.algorithm.sort.BucketSort;

/**
 * ��ʶͰ����
 * @author liuzehong
 *
 */
public class BucketSort {
	// Ͱ
	int[] buckets;
	// ��������
	int[] arr;
	/*
	 * size:Ͱ�ĸ��� array:��������
	 */
	public BucketSort(int size, int[] array) {
		this.buckets = new int[size];
		this.arr = array;
	}

	public void sort() {
		if (arr != null && arr.length > 1) {
			for (int i = 0; i < arr.length; i++) {
				//��������,Ͱ������м���
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