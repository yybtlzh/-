package com.yybt.algorithm.sort;

import java.util.Arrays;
/**
 * 归并排序
 * @author liuzehong
 *
 */
public class MergeSort {
	
	/**
	 * 
	 * @param arr
	 * @param start
	 *            开始下标
	 * @param end
	 *            结束下标
	 * @return
	 */
	public int[] sort(int[] arr, int start, int end) {
		// 出口：不可划分为止，即start=end
		if (start < end) {
			// 对arr进一步划分，需要找到arr的划分位置
			int mid = start + ((end - start) >> 1);
			// 对arr左半部分进行划分
			sort(arr, start, mid);
			// 对arr右半部分进行划分
			sort(arr, mid + 1, end);
			// 对划分好的集合进行合并工作
			mergesoft(arr, start, mid, end);
		}
		return arr;
	}

	/**
	 * @param arr
	 * @param start
	 * @param mid
	 * @param end
	 */
	private void mergesoft(int[] arr, int start, int mid, int end) {
		// 临时数组，用于存放较小的元素
		int[] temp = new int[end - start + 1];
		int i = start;
		int j = mid + 1;
		int k = 0;
		// 关键：将较小的存放于临时数组
		while (i <= mid && j <= end) {
			if (arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}
		// 两组集合剩余元素有序，依次存放于临时数组
		// 把左边剩余的数移入数组
		while (i <= mid)
			temp[k++] = arr[i++];
		// 把右边边剩余的数移入数组
		while (j <= end) {
			temp[k++] = arr[j++];
		}
		for (int s = 0; s < temp.length; s++)
			arr[start + s] = temp[s];
	}

	public static void main(String[] args) {
		
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
		System.out.println("排序后：" + Arrays.toString(a));
		int[] sort = new MergeSort().sort(a, 0, a.length - 1);
		System.out.println("排序后：" + Arrays.toString(sort));

	}
}
