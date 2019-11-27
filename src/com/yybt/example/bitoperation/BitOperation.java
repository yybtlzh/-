package com.yybt.example.bitoperation;

/**
 * 一些位运算的简单例子
 * 
 * @author liuzehong
 *
 */
public class BitOperation {

	/**
	 * 判断v是否是奇数
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isOddNum(int v) {
		return (v & 1) == 1;
	}

	/**
	 * 判断v是否是偶数
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isEvenNum(int v) {
		// return (v&1)== 0;
		return !isOddNum(v);
	}

	/**
	 * 返回v第k位上的数
	 * 
	 * @param v
	 * @param k从1开始，从左往右
	 * @return
	 */
	public static int getK(int v, int k) {
		// return (v&1)== 0;
		return v >> k & 1;
	}

	/**
	 * 返回X,Y 的平均值
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int average(int x, int y) {
		return (x & y) + ((x ^ y) >> 1);
	}

	/**
	 * 对于一个数 x >= 0，推断他是不是2的幂
	 * 
	 * @param x
	 * @return
	 */
	public static boolean power2(int x) {

		return x < 0 ? false : ((x & (x - 1)) == 0) && (x != 0);
	}

	/**
	 * 不用temp交换两个整数
	 * 
	 * @param x
	 * @param y
	 */
	public static void swap(int x, int y) {
		x ^= y;
		y ^= x;
		x ^= y;
		System.out.println("x:" + x + " " + "y:" + y);
	}

	/**
	 * 计算绝对值
	 * 
	 * @param x
	 * @return
	 */
	public static int abs(int x) {
		int y;
		y = x >> 31;
		return (x ^ y) - y; // or: (x+y)^y
	}

	/**
	 * 计算x相反数
	 * 
	 * @param x
	 * @return
	 */
	public static int OppositeNum(int x) {

		return (~x + 1);

	}

	/**
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
	 * 
	 * @param A
	 * @return 原理：a^a=0,而 a^0=a,并且位运算是满足交换律的，两两异或得0，最后就只剩下那个唯一出现的数了。
	 */
	public static int singleNumber(int[] A) {
		int num = 0;
		for (int i = 0; i < A.length; i++) {
			num ^= A[i];
		}
		return num;
	}

	/**
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现三次。找出那个只出现了一次的元素。
	 * 
	 * @param nums
	 * @return 原理：在java中，int 整型共有32位，用num[32]存储这n个数据每个二进制位上1出现的个数，
	 *         再%3，如果为1，那说明这一位是要找元素二进制表示中为1 的那一位。每次内部计算出1所在的位数， 最后再还原给result
	 */
	public static int singleNumberThree(int[] nums) {
		int len = nums.length, result = 0;
		for (int i = 0; i < 32; i++) {
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += (nums[j] >> i) & 1;
			}
			result |= (sum % 3) << i;
		}
		return result;
	}
	/**
	 * 大神给出的代码
	 * @param A
	 * @return
	 */
	public static int singleNumberThree2(int[] A) {
		 int ones = 0;// 记录只出现过1次的bits
		 int twos = 0;// 记录只出现过2次的bits
		 int threes;
		for (int i = 0; i < A.length; i++) {
			int t = A[i];
			twos |= ones & t;// 要在更新ones前面更新twos
			ones ^= t;
			threes = ones & twos;// ones和twos中都为1即出现了3次
			ones &= ~threes;// 抹去出现了3次的bits
			twos &= ~threes;
		}
		 return ones;
   }

	public static void main(String[] args) {
		/*
		 * System.out.println(3&5); System.out.println((3^5)>>1);
		 */
		/* System.out.println(OppositeNum(123444)); */
		/*
		 * int[] nums = { 11,11,23,45,33,45,33}; System.out.println(singleNumber(nums));
		 */
		int[] nums = { 11, 11, 11, 23, 45, 33, 33, 45, 45, 33 };
		System.out.println(singleNumberThree2(nums));
	}

}
