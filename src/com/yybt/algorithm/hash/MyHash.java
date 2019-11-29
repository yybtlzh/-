package com.yybt.algorithm.hash;

/**
 * 一个最简单的hash算法
 * 
 * @author liuzehong
 *
 */
public class MyHash {

	public static int hash(String key, int tablesize) {
		
		int hashVal = 0;
		
		for (int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
		}
		hashVal %= tablesize;
		if (hashVal < 0) {
			hashVal += tablesize;
		}
		return hashVal;

	}

}
