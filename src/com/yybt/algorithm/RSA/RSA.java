package com.yybt.algorithm.RSA;

public class RSA {
	/**
	 * * 加密、解密算法 * 
	 * @param key 公钥或密钥 * 
	 * @param message 数据 * 
	 * @return
	 */
	public static long rsa(int baseNum, int key, long message) {
		if (baseNum < 1 || key < 1) 
			return 0L;
		
		// 加密或者解密之后的数据
		long rsaMessage = 0L;
		// 加密核心算法
		rsaMessage = Math.round(Math.pow(message, key)) % baseNum;
		return rsaMessage;
	}

	public static void main(String[] args) {
		// 基数
		int baseNum = 3 * 11;
		// 公钥
		int keyE = 3;
		// 密钥 
		int keyD = 7;
		// 未加密的数据 
		long msg = 24L;
		// 加密后的数据 
		long encodeMsg = rsa(baseNum, keyE, msg);
		// 解密后的数据 
		long decodeMsg = rsa(baseNum, keyD, encodeMsg);
		System.out.println("加密前：" + msg); 
		System.out.println("加密后：" + encodeMsg);
		System.out.println("解密后：" + decodeMsg); 
	}

}