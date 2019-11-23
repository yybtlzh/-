package com.yybt.algorithm.sort.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * ͨ��ӳ�䵽��ͬ��Ͱ����Ͱ���������ڷֲ��ȽϷ�ɢ��һ������
 * @author liuzehong
 *
 */
public class BucketSort3 {
    // ��������
	private int[] arr;
	//Ͱ����
	private ArrayList<ArrayList<Integer>> buckets;
	private int size;
	//Ͱ����
	public BucketSort3(int size, int[] array) {
		this.arr = array;
		this.size=size;
	}

	/**
	 * 
	 */
	private void sort() {
		if (arr== null || arr.length < 1) {
			return ;
		}
		 int max = 0;
		 int min = 0;
		 for(int i = 0; i < arr.length; i++){
		      max = Math.max(max, arr[i]);
		      min = Math.min(min, arr[i]);
		 }
		 //Ͱ�ĸ���
		 int num = (max - min) / this.size + 1;
		//��ʼ��Ͱ����
		this.buckets=new ArrayList<>(num);
		for(int i = 0; i < num; i++){
	        buckets.add(new ArrayList<Integer>());
	    }
		//Ԫ��ӳ�䵽Ͱ����10-15   15-20   5      3/5   0
		//ͨ����ֵӳ��
	    for(int i = 0; i < arr.length; i++){
	    	int a = (arr[i] - min) /size;
	        buckets.get(a).add(arr[i]);
	    }
	    for (int i = 0; i < buckets.size(); i++) {
	    	//�������дӴ�С���򣬿�����дcompare()��ʵ��
	    	 Collections.sort( buckets.get(i), new Comparator< Integer >() {
	             @Override
	             public int compare(Integer lhs, Integer rhs) {
	                 if ( lhs > rhs ) {
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
		int[] array = {13,100,20,40,50,32,44,12,24,30,44,62,640};
		BucketSort3 bucketSort = new BucketSort3(200, array);
		System.out.println("Ͱ����:200");
	    System.out.println("����ǰ��");
	    System.out.println(Arrays.toString(array));
	    System.out.println("�����");
		bucketSort.sort();
	}
}