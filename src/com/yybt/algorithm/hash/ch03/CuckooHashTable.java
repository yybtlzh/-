package com.yybt.algorithm.hash.ch03;

import java.util.Random;

public class CuckooHashTable<E> {
	/**
	 * 负载因子：0.4
	 */
	private static final float MAX_LOAD = 0.4f;

	private static final int ALLOWED_REHASHES = 1;
	/**
	 * 定义默认表的大小
	 */
	private static final int DEFAULT_TABLE_SIZE = 101;
	/**
	 * 定义散列集合
	 */
	private final HashFamily<? super E> hashFunctions;
	/**
	 * 定义散列函数个数
	 */
	private final int numHashFunctions;
	/**
	 * 定义当前表
	 */
	private Object[] array;
	/**
	 * 定义当前表大小
	 */
	private int currentSize;

	private int rehashes = 0;

	/**
	 * 采用默认容量
	 * 
	 * @param hf
	 */
	public CuckooHashTable(HashFamily<E> hashFamily) {
		this(hashFamily, DEFAULT_TABLE_SIZE);
	}

	private Random random = new Random();

	/**
	 * 自定义大小
	 * 
	 * @param hf
	 * @param size
	 */
	public CuckooHashTable(HashFamily<? super E> hf, int size) {
		array = new Object[size];
		hashFunctions = hf;
		numHashFunctions = hf.getNumOfFunctions();
	}

	/**
	 * 清空散列
	 */
	public void clear() {
		doClear();
	}

	/**
	 * 查询是否包含元素e
	 * 
	 * @param e
	 * @return
	 */
	public boolean contains(E e) {
		return getIndex(e) != -1;
	}

	/**
	 * 删除元素e：先查询表中是否存在该元素，若存在，则进行删除该元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean remove(E e) {
		int index = getIndex(e);
		if (index != -1) {
			array[index] = null;
			currentSize--;
		}
		return index != -1;
	}

	/**
	 * 插入：先判断该元素是否存在，若存在，在判断表的大小是否达到最大负载， 若达到，则进行扩展，最后调用insertHelper方法进行插入元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean add(E e) {
		if (contains(e)) {
			return false;
		}
		if (currentSize >= array.length * MAX_LOAD) {
			expand();
		}
		return insert(e);
	}

	private boolean insert(E e) {
		// 记录循环的最大次数
		final int COUNT_LIMIT = 100;
		while (true) {
			// 记录上一个元素位置
			int lastPos = -1;
			int pos;
			// 进行查找插入
			for (int count = 0; count < COUNT_LIMIT; count++) {
				for (int i = 0; i < numHashFunctions; i++) {
					pos = hash(e, i);
					// 查找成功，直接返回
					if (array[pos] == null) {
						array[pos] = e;
						currentSize++;
						return true;
					}
				}
				// 查找失败，进行替换操作，产生随机数位置，当产生的位置不能与原来的位置相同
				int i = 0;
				do {
					pos = hash(e, random.nextInt(numHashFunctions));
				} while (pos == lastPos && i++ < 5);
				// 进行替换操作
				@SuppressWarnings("unchecked")
				E temp = (E) array[lastPos = pos];
				array[pos] = e;
				e = temp;
			}
			// 超过次数，还是插入失败，则进行扩表或rehash操作
			if (++rehashes > ALLOWED_REHASHES) {
				expand();
				rehashes = 0;
			} else {
				rehash();
			}
		}
	}

	private void rehash() {
		hashFunctions.generateNewFunctions();
		rehash(array.length);
	}

	private void expand() {
		rehash((int) (array.length / MAX_LOAD));
	}

	private void rehash(int newLength) {
		@SuppressWarnings("unchecked")
		E[] oldArray = (E[]) array;
		array = new Object[(nextPrime(newLength))];
		currentSize = 0;
		for (E e : oldArray) {
			if (e != null)
				add(e);
		}
	}

	private int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		while (!isPrime(n))
			n += 2;
		return n;
	}

	private boolean isPrime(int n) {

		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;

	}

	/**
	 * 查询元素的位置，若找到元素，则返回其当前位置，否则返回-1
	 * 
	 * @param e
	 * @return
	 */
	private int getIndex(E e) {
		// 遍历散列函数集合，因为不确定元素所用的散列函数为哪个
		for (int i = 0; i < numHashFunctions; i++) {
			// 获取到当前hash值
			int index = hash(e, i);
			// 判断表中是否存在当前元素
			if (array[index] != null && array[index].equals(e)) {
				return index;
			}
		}
		return -1;
	}

	private void doClear() {
		currentSize = 0;
		for (int i = 0; i < array.length;)
			array[i++] = null;
	}

	/**
	 *
	 * @param e
	 *            当前的元素
	 * @param which
	 *            选取的散列函数对应的位置
	 * @return
	 */
	private int hash(E e, int which) {
		// 调用散列函数集合中的hash方法获取到hash值
		int hashVal = hashFunctions.hash(e, which);
		// 再做一定的处理
		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;
		return hashVal;
	}

	public void printArray() {
		for (int s = 0; s < array.length; s++) {
			if (array[s] != null) {
				System.out.println(array[s]);
			}

		}

	}

	public static void main(String[] args) {
		// 定义布谷鸟散列
		CuckooHashTable<String> table = new CuckooHashTable<String>(new MyHashFamily<String>());
		String[] strs = { "张三", "李四", "王五", "赵六" };
		// 插入
		for (int i = 0; i < strs.length; i++) {
			table.add(strs[i]);
		}
		// 打印表
		table.printArray();
	}

}
