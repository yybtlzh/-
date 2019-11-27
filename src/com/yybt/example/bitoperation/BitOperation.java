package com.yybt.example.bitoperation;

/**
 * һЩλ����ļ�����
 * @author liuzehong
 *
 */
public class BitOperation {
	
	/**
	 * �ж�v�Ƿ�������
	 * @param v
	 * @return
	 */
	public static  boolean  isOddNum(int v) {
		return (v&1)== 1;
	}
	
	/**
	 * �ж�v�Ƿ���ż��
	 * @param v
	 * @return
	 */
	public static  boolean  isEvenNum(int v) {
		//return (v&1)== 0;
		return !isOddNum(v);
	}
	
	/**
	 * ����v��kλ�ϵ���
	 * @param v
	 * @param k��1��ʼ����������
	 * @return
	 */
	public static  int  getK(int v,int k) {
		//return (v&1)== 0;
		return v>>k&1 ;
	}
	/**
	 * ����X,Y ��ƽ��ֵ 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int average(int x, int y){    
	    return (x&y)+((x^y)>>1); 
	} 
	
	
	/**
	 * ����һ���� x >= 0���ƶ����ǲ���2���� 
	 * @param x
	 * @return
	 */
	public static boolean power2(int x){ 
		
	    return  x<0?false: ((x&(x-1))==0)&&(x!=0);
	} 
	
	 
	/**
	 * ����temp������������ 
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
	 * �������ֵ
	 * @param x
	 * @return
	 */
	public static int abs( int x ){ 
		int y ; 
		y = x >> 31 ; 
		return (x^y)-y ;        //or: (x+y)^y 
	} 
	
	
	/**
	 * ����x�෴��
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
