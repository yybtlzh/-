package com.yybt.algorithm.sort;
/**
 * �������Ų��裺{1,23,4,445,37,4,155,13,9999,20,0}
 *            [1, 0, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            �ظ�����������ɶ�key��ߺͶ�key�ұ���������
 * @author lx
 */
public class QuickSort {

	/**
	 * ��������
	 */
	public static int partition(long arr[],int left, int right,long point) {
		int leftPtr = left - 1;
		int rightPtr = right;
		while(true) {
			//ѭ��,���ȹؼ���С���������
			while(leftPtr < rightPtr && arr[++leftPtr] < point);
			//ѭ�������ȹؼ��ִ�������Ҷ�
			while(rightPtr > leftPtr && arr[--rightPtr] > point);
			if(leftPtr >= rightPtr) {
				break;
			} else {
				long tmp = arr[leftPtr];
				arr[leftPtr] =  arr[rightPtr];
				arr[rightPtr] = tmp;
			}
		}
		//���ؼ��ֺ͵�ǰleftPtr��ָ����һ�����н���
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
			//���ùؼ���
			long point = arr[right];
			//�������㣬ͬʱ��������л���
			int partition = partition(arr, left, right, point);
			//����ߵ���������п�������
			sort(arr,left,partition - 1);
			//���ұߵ���������п�������
			sort(arr,partition + 1, right);
		}
	}
}
