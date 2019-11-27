package com.yybt.example.filter;

import java.util.BitSet;

/**
 * ��¡����
 * @author liuzehong
 *
 */
public class BloomFilter {
	
	// ������������λ�����൱���ܴ洢1����url���ң�����Ϊ�ڷ�֮һ
	private static final int BIT_SIZE = 2 << 29;
	// ����8������������Ϣmark
	private static final int[] seeds = new int[] { 2, 3, 5, 7, 11, 13, 31, 37 };
	private BitSet bits = new BitSet(BIT_SIZE);
	// ���ڴ洢8�������ϣֵ����
	private MyHash[] hash = new MyHash[seeds.length];

	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			hash[i] = new MyHash(BIT_SIZE, seeds[i]);
		}
	}

	/**
	 * �������������ַ���
	 */
	public void addValue(String value) {
		// ���ַ���value��ϣΪ8������������Ȼ������Щ������bit�ϱ�Ϊ1
		if (value != null) {
			for (MyHash h : hash)
				bits.set(h.hashCode(value), true);
		}
	}

	/**
	 * �ж��ַ����Ƿ�����ڲ�¡��������
	 */
	public boolean contains(String value) {
		if (value == null)
			return false;
		boolean bool = true;
		// ��Ҫ�Ƚϵ��ַ���������������������hashֵ�����벼¡�������ȶ�
		for (MyHash h : hash)
			bool = bool && bits.get(h.hashCode(value));
		return bool;
	}

	/**
	 * �����ϣֵ����
	 */
	class MyHash {
		private int size;// ���������������С
		private int mark;// ���������

		public MyHash(int cap, int mark) {
			this.size = cap;
			this.mark = mark;
		}

		/**
		 * �����ϣֵ(�����������Զ����ϣ����)
		 */
		public int hashCode(String key) {
			int hashVal = 0;
			for (int i = 0; i < key.length() - 1; i++) {
				hashVal = mark * hashVal + key.charAt(i);
			}
			return (size - 1) & hashVal;
		}
	}

	public static void main(String[] args) {
		BloomFilter b = new BloomFilter();
		long start = System.currentTimeMillis();
		for (int i = 10000000; i >= 1; i--) {
			b.addValue("www.sougou.com" + i);
		}
		System.out.println(b.contains("www.sougou.com100"));
		System.out.println(b.contains("www.sougou.com100000001"));
		long end = System.currentTimeMillis();
		System.out.println("��ʱ��" + (end - start) + "����");
	}
}