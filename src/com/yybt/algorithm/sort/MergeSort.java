package com.yybt.algorithm.sort;

import java.util.Arrays;
/**
 * �鲢����
 * @author liuzehong
 *
 */
public class MergeSort {
	
	/**
	 * 
	 * @param arr
	 * @param start
	 *            ��ʼ�±�
	 * @param end
	 *            �����±�
	 * @return
	 */
	public int[] sort(int[] arr, int start, int end) {
		// ���ڣ����ɻ���Ϊֹ����start=end
		if (start < end) {
			// ��arr��һ�����֣���Ҫ�ҵ�arr�Ļ���λ��
			int mid = start + ((end - start) >> 1);
			// ��arr��벿�ֽ��л���
			sort(arr, start, mid);
			// ��arr�Ұ벿�ֽ��л���
			sort(arr, mid + 1, end);
			// �Ի��ֺõļ��Ͻ��кϲ�����
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
		// ��ʱ���飬���ڴ�Ž�С��Ԫ��
		int[] temp = new int[end - start + 1];
		int i = start;
		int j = mid + 1;
		int k = 0;
		// �ؼ�������С�Ĵ������ʱ����
		while (i <= mid && j <= end) {
			if (arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}
		// ���鼯��ʣ��Ԫ���������δ������ʱ����
		// �����ʣ�������������
		while (i <= mid)
			temp[k++] = arr[i++];
		// ���ұ߱�ʣ�������������
		while (j <= end) {
			temp[k++] = arr[j++];
		}
		for (int s = 0; s < temp.length; s++)
			arr[start + s] = temp[s];
	}

	public static void main(String[] args) {
		
		int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
		System.out.println("�����" + Arrays.toString(a));
		int[] sort = new MergeSort().sort(a, 0, a.length - 1);
		System.out.println("�����" + Arrays.toString(sort));

	}
}
