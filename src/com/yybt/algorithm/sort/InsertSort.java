package com.yybt.algorithm.sort;

/**
 * ≤Â»Î≈≈–Ú
 * @author liuzehong
 *
 */
public class InsertSort {
	
	public static void sort(long[] arr) {
		long tmp = 0;
		
		for(int i = 1; i < arr.length; i++) {
			tmp = arr[i];
			int j = i;
			while(j > 0 && arr[j] >= tmp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = tmp;
		}
	}
}
