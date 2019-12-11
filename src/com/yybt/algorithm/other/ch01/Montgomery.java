package com.yybt.algorithm.other.ch01;

import java.math.BigInteger;

/**
 * ����ȡģ���㷺Ӧ����RSA
 * �ɸ�������ģ����(a * b) % p = a % p * b % p % p

 * @author liuzehong
 */
public class Montgomery {
	
	
	/**
	 * ������intʵ��һ����Ҫ�Ƿ��㿴 ������룬�������±ߵľͿ���
	 * @param base ����
	 * @param exponent  ָ��
	 * @param mod  ģ
	 * @return
	 */
	public static int montgomery(int base, int exponent, int mod){ // ���ټ��� (n ^ e) % m ��ֵ����power�㷨������
	    int r = base % mod; // �����r�ɲ���ʡ   r =����
	    int k = 1;             // k=a/b
	    while (exponent > 1){
	        if ((exponent & 1)!=0){
	            k = (k * r) % mod; // ֱ��ȡģ
	        }
	        r = (r * r) % mod; // ͬ��
	        exponent /= 2;
	    }
	    return (r * k) % mod; // ����ͬ��
	}
	
	/**
	 * 
	 * @param base ����
	 * @param exponent  ָ��
	 * @param mod  ģ
	 * @return
	 */
	public static BigInteger montgomery(BigInteger base, BigInteger exponent, BigInteger mod){ // ���ټ��� (n ^ e) % m ��ֵ����power�㷨������
		BigInteger r = base.mod(mod);
		BigInteger k =BigInteger.ONE ;
	    while (exponent.compareTo(BigInteger.ONE)>0){
	       // if ((p & 1)!=0){
	    	//���������
	    	if(exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
	            k = k.multiply(r).mod(mod); // ֱ��ȡģ
	        }
	        r = (r.multiply(r).mod(mod)); // ͬ��
	        exponent =exponent.divide(BigInteger.valueOf(2));
	    }
	    return r.multiply(k).mod(mod); // ����ͬ��
	}
	
	public static void main(String[] args) {
		System.out.println(Montgomery.montgomery(15,2,17));
		System.out.println(Montgomery.montgomery(new BigInteger("15"),
		new BigInteger("2"), new BigInteger("17")));
		
	}

}

