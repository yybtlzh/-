package com.yybt.algorithm.RSA;

public class RSA {
	/**
	 * * ���ܡ������㷨 * 
	 * @param key ��Կ����Կ * 
	 * @param message ���� * 
	 * @return
	 */
	public static long rsa(int baseNum, int key, long message) {
		if (baseNum < 1 || key < 1) 
			return 0L;
		
		// ���ܻ��߽���֮�������
		long rsaMessage = 0L;
		// ���ܺ����㷨
		rsaMessage = Math.round(Math.pow(message, key)) % baseNum;
		return rsaMessage;
	}

	public static void main(String[] args) {
		// ����
		int baseNum = 3 * 11;
		// ��Կ
		int keyE = 3;
		// ��Կ 
		int keyD = 7;
		// δ���ܵ����� 
		long msg = 24L;
		// ���ܺ������ 
		long encodeMsg = rsa(baseNum, keyE, msg);
		// ���ܺ������ 
		long decodeMsg = rsa(baseNum, keyD, encodeMsg);
		System.out.println("����ǰ��" + msg); 
		System.out.println("���ܺ�" + encodeMsg);
		System.out.println("���ܺ�" + decodeMsg); 
	}

}