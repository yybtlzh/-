package com.yybt.example.filter;

import java.util.BitSet;

/**
 * 布隆过滤
 * @author liuzehong
 *
 */
public class BloomFilter {
	
	// 二进制向量的位数，相当于能存储1亿条url左右，误报率为亿分之一
	private static final int BIT_SIZE = 2 << 29;
	// 利用8个质数生成信息mark
	private static final int[] seeds = new int[] { 2, 3, 5, 7, 11, 13, 31, 37 };
	private BitSet bits = new BitSet(BIT_SIZE);
	// 用于存储8个随机哈希值对象
	private MyHash[] hash = new MyHash[seeds.length];

	public BloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			hash[i] = new MyHash(BIT_SIZE, seeds[i]);
		}
	}

	/**
	 * 像过滤器中添加字符串
	 */
	public void addValue(String value) {
		// 将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1
		if (value != null) {
			for (MyHash h : hash)
				bits.set(h.hashCode(value), true);
		}
	}

	/**
	 * 判断字符串是否包含在布隆过滤器中
	 */
	public boolean contains(String value) {
		if (value == null)
			return false;
		boolean bool = true;
		// 将要比较的字符串重新以上述方法计算hash值，再与布隆过滤器比对
		for (MyHash h : hash)
			bool = bool && bits.get(h.hashCode(value));
		return bool;
	}

	/**
	 * 随机哈希值对象
	 */
	class MyHash {
		private int size;// 二进制向量数组大小
		private int mark;// 随机数种子

		public MyHash(int cap, int mark) {
			this.size = cap;
			this.mark = mark;
		}

		/**
		 * 计算哈希值(可以是其他自定义哈希函数)
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
		System.out.println("耗时：" + (end - start) + "毫秒");
	}
}