package com.yybt.algorithm.sort;

import java.util.Arrays;  
/**
 * �������Ų��裺{1,23,4,445,37,4,155,13,9999,20,0}
 *            [1, 0, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            �ظ�����������ɶ�key��ߺͶ�key�ұ���������
 * @author lx
 */
public class FastSortTest {  
	/**
	 * ����ʵ��
	 */
	/**
	 * ���ÿ��ŷ������
	 * @param a
	 */
    public static void FastSort(int[] a) {  
        if(a.length>0) {  
        	FastSort(a, 0 , a.length-1);  
        }  
    } 
    /**
     * ���ŷ���
     * @param a
     * @param low
     * @param high
     */
    private static void FastSort(int[] a, int low, int high) {
    	//�ݹ����
        if( low > high) {
            return;  
        }  
        //i,j��Ϊ��ʱ������ʼΪlow��high��
        int i = low;  
        int j = high;  
        //�Ե�һ������Ԫ����Ϊ�ؼ����ݣ���ֵ��key 
        int key = a[low];  
        //��ʼ����  
        while( i< j) {  
            //���������ҵ���һ��С��key����  
            while(i<j && a[j] > key){
                j--;  
            }  
            //���������ҵ���һ������key����  
            while( i<j && a[i] <= key) {
                i++;  
            }  
            //����  
            if(i<j&&a[i]>a[j]) {
                int temp = a[i];  
                a[i] = a[j];  
                a[j] = temp;  
            }  
        }  
        
        //����key��λ��  
        int temp = a[i];  
        a[i] = a[low];  
        a[low] = temp;  
        //�ݹ�ʵ�ֶ�key������ݿ���  
        FastSort(a, low, i-1 );  
        //�ݹ�ʵ�ֶ�key�ұ����ݿ��� 
        FastSort(a, i+1, high); 
    }  
    /**
     * ���Ե���
     * @param args
     */
    public static void main(String[] args) {  
        int[] a = {1,23,4,445,37,4,155,13,9999,20,0}; 
        System.out.println("����ǰ:"+Arrays.toString(a));  
        FastSort(a);  
        System.out.println("�����:"+Arrays.toString(a));  
    }  
}  
  