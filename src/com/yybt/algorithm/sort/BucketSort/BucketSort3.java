package com.yybt.algorithm.sort.BucketSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 通过映射到不同的桶进行桶排序，适用于分布比较分散的一组数据
 * @author liuzehong
 *
 */
public class BucketSort3 {
    // 排序数组
	private int[] arr;
	//桶集合
	private ArrayList<ArrayList<Integer>> buckets;
	private int size;
	//桶个数
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
		 //桶的个数
		 int num = (max - min) / this.size + 1;
		//初始化桶集合
		this.buckets=new ArrayList<>(num);
		for(int i = 0; i < num; i++){
	        buckets.add(new ArrayList<Integer>());
	    }
		//元素映射到桶集合10-15   15-20   5      3/5   0
		//通过差值映射
	    for(int i = 0; i < arr.length; i++){
	    	int a = (arr[i] - min) /size;
	        buckets.get(a).add(arr[i]);
	    }
	    for (int i = 0; i < buckets.size(); i++) {
	    	//如果需进行从大到小排序，可以重写compare()来实现
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
		System.out.println("桶容量:200");
	    System.out.println("排序前：");
	    System.out.println(Arrays.toString(array));
	    System.out.println("排序后：");
		bucketSort.sort();
	}
}