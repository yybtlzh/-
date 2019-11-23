package com.yybt.algorithm.sort;
/**
 * 分析快排步骤：{1,23,4,445,37,4,155,13,9999,20,0}
 *            [1, 0, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            重复上述步骤完成对key左边和对key右边数的排序
 * @author lx
 */
public class QuickSort {

	/**
	 * 划分数组
	 */
	public static int partition(long arr[],int left, int right,long point) {
		int leftPtr = left - 1;
		int rightPtr = right;
		while(true) {
			//循环,将比关键字小的留在左端
			while(leftPtr < rightPtr && arr[++leftPtr] < point);
			//循环，将比关键字大的留在右端
			while(rightPtr > leftPtr && arr[--rightPtr] > point);
			if(leftPtr >= rightPtr) {
				break;
			} else {
				long tmp = arr[leftPtr];
				arr[leftPtr] =  arr[rightPtr];
				arr[rightPtr] = tmp;
			}
		}
		//将关键字和当前leftPtr所指的这一个进行交换
		long tmp = arr[leftPtr];
		arr[leftPtr] =  arr[right];
		arr[right] = tmp;
		return leftPtr;
	}
	
	public static void displayArr(long[] arr) {
		System.out.print("[");
		for(long num : arr) {
			System.out.print(num + " ");
		}
		System.out.print("]");
		System.out.println();
	}
	
	public static void sort(long[] arr, int left, int right) {
		if(right - left <= 0) {
			return;
		} else {
			//设置关键字
			long point = arr[right];
			//获得切入点，同时对数组进行划分
			int partition = partition(arr, left, right, point);
			//对左边的子数组进行快速排序
			sort(arr,left,partition - 1);
			//对右边的子数组进行快速排序
			sort(arr,partition + 1, right);
		}
	}
}
