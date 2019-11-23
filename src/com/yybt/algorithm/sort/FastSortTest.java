package com.yybt.algorithm.sort;

import java.util.Arrays;  
/**
 * 分析快排步骤：{1,23,4,445,37,4,155,13,9999,20,0}
 *            [1, 0, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            [0, 1, 4, 445, 37, 4, 155, 13, 9999, 20, 23]
 *            重复上述步骤完成对key左边和对key右边数的排序
 * @author lx
 */
public class FastSortTest {  
	/**
	 * 代码实现
	 */
	/**
	 * 调用快排方法入口
	 * @param a
	 */
    public static void FastSort(int[] a) {  
        if(a.length>0) {  
        	FastSort(a, 0 , a.length-1);  
        }  
    } 
    /**
     * 快排方法
     * @param a
     * @param low
     * @param high
     */
    private static void FastSort(int[] a, int low, int high) {
    	//递归出口
        if( low > high) {
            return;  
        }  
        //i,j作为临时变量初始为low，high。
        int i = low;  
        int j = high;  
        //以第一个数组元素作为关键数据，赋值给key 
        int key = a[low];  
        //开始排序  
        while( i< j) {  
            //从右往左找到第一个小于key的数  
            while(i<j && a[j] > key){
                j--;  
            }  
            //从左往右找到第一个大于key的数  
            while( i<j && a[i] <= key) {
                i++;  
            }  
            //交换  
            if(i<j&&a[i]>a[j]) {
                int temp = a[i];  
                a[i] = a[j];  
                a[j] = temp;  
            }  
        }  
        
        //调整key的位置  
        int temp = a[i];  
        a[i] = a[low];  
        a[low] = temp;  
        //递归实现对key左边数据快排  
        FastSort(a, low, i-1 );  
        //递归实现对key右边数据快排 
        FastSort(a, i+1, high); 
    }  
    /**
     * 测试调用
     * @param args
     */
    public static void main(String[] args) {  
        int[] a = {1,23,4,445,37,4,155,13,9999,20,0}; 
        System.out.println("排序前:"+Arrays.toString(a));  
        FastSort(a);  
        System.out.println("排序后:"+Arrays.toString(a));  
    }  
}  
  