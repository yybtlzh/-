package com.yybt.example.bitoperation;

/**
 * 一些位运算的简单例子
 * @author liuzehong
 *
 */
public class BitOperation {
	
	/**
	 * 判断v是否是奇数
	 * @param v
	 * @return
	 */
	public static  boolean  isOddNum(int v) {
		return (v&1)== 1;
	}
	
	/**
	 * 判断v是否是偶数
	 * @param v
	 * @return
	 */
	public static  boolean  isEvenNum(int v) {
		//return (v&1)== 0;
		return !isOddNum(v);
	}
	
	/**
	 * 返回v第k位上的数
	 * @param v
	 * @param k从1开始，从左往右
	 * @return
	 */
	public static  int  getK(int v,int k) {
		//return (v&1)== 0;
		return v>>k&1 ;
	}
	/**
	 * 返回X,Y 的平均值 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int average(int x, int y){    
	    return (x&y)+((x^y)>>1); 
	} 
	
	
	/**
	 * 对于一个数 x >= 0，推断他是不是2的幂 
	 * @param x
	 * @return
	 */
	public static boolean power2(int x){ 
		
	    return  x<0?false: ((x&(x-1))==0)&&(x!=0);
	} 
	
	 
	/**
	 * 不用temp交换两个整数 
	 * @param x
	 * @param y
	 */
	public static void swap(int x , int y) { 
	    x ^= y; 
	    y ^= x; 
	    x ^= y; 
	    System.out.println("x:"+x+" "+"y:"+y);
	} 
	
	/**
	 * 计算绝对值
	 * @param x
	 * @return
	 */
	public static int abs( int x ){ 
		int y ; 
		y = x >> 31 ; 
		return (x^y)-y ;        //or: (x+y)^y 
	} 
	
	
	/**
	 * 计算x相反数
	 * @param x
	 * @return
	 */
	public static int OppositeNum(int x) {
		
		return (~x+1);

	}
	
	
	public static void main(String[] args) {
		/*System.out.println(3&5);
		System.out.println((3^5)>>1);*/
		System.out.println(OppositeNum(123444));
	}
	
	

}
