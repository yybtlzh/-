package com.yybt.example.bitoperation;

/**
 * һЩλ����ļ�����
 * 
 * @author liuzehong
 *
 */
public class BitOperation {

	/**
	 * �ж�v�Ƿ�������
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isOddNum(int v) {
		return (v & 1) == 1;
	}

	/**
	 * �ж�v�Ƿ���ż��
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isEvenNum(int v) {
		// return (v&1)== 0;
		return !isOddNum(v);
	}

	/**
	 * ����v��kλ�ϵ���
	 * 
	 * @param v
	 * @param k��1��ʼ����������
	 * @return
	 */
	public static int getK(int v, int k) {
		// return (v&1)== 0;
		return v >> k & 1;
	}

	/**
	 * ����X,Y ��ƽ��ֵ
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int average(int x, int y) {
		return (x & y) + ((x ^ y) >> 1);
	}

	/**
	 * ����һ���� x >= 0���ƶ����ǲ���2����
	 * 
	 * @param x
	 * @return
	 */
	public static boolean power2(int x) {

		return x < 0 ? false : ((x & (x - 1)) == 0) && (x != 0);
	}

	/**
	 * ����temp������������
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
	 * �������ֵ
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
	 * ����x�෴��
	 * 
	 * @param x
	 * @return
	 */
	public static int OppositeNum(int x) {

		return (~x + 1);

	}

	/**
	 * ����һ���ǿ��������飬����ĳ��Ԫ��ֻ����һ�����⣬����ÿ��Ԫ�ؾ��������Ρ��ҳ��Ǹ�ֻ������һ�ε�Ԫ��
	 * 
	 * @param A
	 * @return ԭ��a^a=0,�� a^0=a,����λ���������㽻���ɵģ���������0������ֻʣ���Ǹ�Ψһ���ֵ����ˡ�
	 */
	public static int singleNumber(int[] A) {
		int num = 0;
		for (int i = 0; i < A.length; i++) {
			num ^= A[i];
		}
		return num;
	}

	/**
	 * ����һ���ǿ��������飬����ĳ��Ԫ��ֻ����һ�����⣬����ÿ��Ԫ�ؾ��������Ρ��ҳ��Ǹ�ֻ������һ�ε�Ԫ�ء�
	 * 
	 * @param nums
	 * @return ԭ����java�У�int ���͹���32λ����num[32]�洢��n������ÿ��������λ��1���ֵĸ�����
	 *         ��%3�����Ϊ1����˵����һλ��Ҫ��Ԫ�ض����Ʊ�ʾ��Ϊ1 ����һλ��ÿ���ڲ������1���ڵ�λ���� ����ٻ�ԭ��result
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
	 * ��������Ĵ���
	 * @param A
	 * @return
	 */
	public static int singleNumberThree2(int[] A) {
		 int ones = 0;// ��¼ֻ���ֹ�1�ε�bits
		 int twos = 0;// ��¼ֻ���ֹ�2�ε�bits
		 int threes;
		for (int i = 0; i < A.length; i++) {
			int t = A[i];
			twos |= ones & t;// Ҫ�ڸ���onesǰ�����twos
			ones ^= t;
			threes = ones & twos;// ones��twos�ж�Ϊ1��������3��
			ones &= ~threes;// Ĩȥ������3�ε�bits
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
