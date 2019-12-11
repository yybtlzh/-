package com.yybt.algorithm.other.ch01;

import java.math.BigInteger;

/**
 * 大数取模，广泛应用于RSA
 * 蒙哥马利幂模运算(a * b) % p = a % p * b % p % p

 * @author liuzehong
 */
public class Montgomery {
	
	
	/**
	 * 这里用int实现一遍主要是方便看 下面代码，大数用下边的就可以
	 * @param base 底数
	 * @param exponent  指数
	 * @param mod  模
	 * @return
	 */
	public static int montgomery(int base, int exponent, int mod){ // 快速计算 (n ^ e) % m 的值，与power算法极类似
	    int r = base % mod; // 这里的r可不能省   r =余数
	    int k = 1;             // k=a/b
	    while (exponent > 1){
	        if ((exponent & 1)!=0){
	            k = (k * r) % mod; // 直接取模
	        }
	        r = (r * r) % mod; // 同上
	        exponent /= 2;
	    }
	    return (r * k) % mod; // 还是同上
	}
	
	/**
	 * 
	 * @param base 底数
	 * @param exponent  指数
	 * @param mod  模
	 * @return
	 */
	public static BigInteger montgomery(BigInteger base, BigInteger exponent, BigInteger mod){ // 快速计算 (n ^ e) % m 的值，与power算法极类似
		BigInteger r = base.mod(mod);
		BigInteger k =BigInteger.ONE ;
	    while (exponent.compareTo(BigInteger.ONE)>0){
	       // if ((p & 1)!=0){
	    	//如果是奇数
	    	if(exponent.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
	            k = k.multiply(r).mod(mod); // 直接取模
	        }
	        r = (r.multiply(r).mod(mod)); // 同上
	        exponent =exponent.divide(BigInteger.valueOf(2));
	    }
	    return r.multiply(k).mod(mod); // 还是同上
	}
	
	public static void main(String[] args) {
		System.out.println(Montgomery.montgomery(15,2,17));
		System.out.println(Montgomery.montgomery(new BigInteger("15"),
		new BigInteger("2"), new BigInteger("17")));
		
	}

}

