package com.yybt.algorithm.hash.ch03;

import java.util.Random;

public class CuckooHashTable<E> {
	/**
	 * �������ӣ�0.4
	 */
	private static final float MAX_LOAD = 0.4f;

	private static final int ALLOWED_REHASHES = 1;
	/**
	 * ����Ĭ�ϱ�Ĵ�С
	 */
	private static final int DEFAULT_TABLE_SIZE = 101;
	/**
	 * ����ɢ�м���
	 */
	private final HashFamily<? super E> hashFunctions;
	/**
	 * ����ɢ�к�������
	 */
	private final int numHashFunctions;
	/**
	 * ���嵱ǰ��
	 */
	private Object[] array;
	/**
	 * ���嵱ǰ���С
	 */
	private int currentSize;

	private int rehashes = 0;

	/**
	 * ����Ĭ������
	 * 
	 * @param hf
	 */
	public CuckooHashTable(HashFamily<E> hashFamily) {
		this(hashFamily, DEFAULT_TABLE_SIZE);
	}

	private Random random = new Random();

	/**
	 * �Զ����С
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
	 * ���ɢ��
	 */
	public void clear() {
		doClear();
	}

	/**
	 * ��ѯ�Ƿ����Ԫ��e
	 * 
	 * @param e
	 * @return
	 */
	public boolean contains(E e) {
		return getIndex(e) != -1;
	}

	/**
	 * ɾ��Ԫ��e���Ȳ�ѯ�����Ƿ���ڸ�Ԫ�أ������ڣ������ɾ����Ԫ��
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
	 * ���룺���жϸ�Ԫ���Ƿ���ڣ������ڣ����жϱ�Ĵ�С�Ƿ�ﵽ����أ� ���ﵽ���������չ��������insertHelper�������в���Ԫ��
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
		// ��¼ѭ����������
		final int COUNT_LIMIT = 100;
		while (true) {
			// ��¼��һ��Ԫ��λ��
			int lastPos = -1;
			int pos;
			// ���в��Ҳ���
			for (int count = 0; count < COUNT_LIMIT; count++) {
				for (int i = 0; i < numHashFunctions; i++) {
					pos = hash(e, i);
					// ���ҳɹ���ֱ�ӷ���
					if (array[pos] == null) {
						array[pos] = e;
						currentSize++;
						return true;
					}
				}
				// ����ʧ�ܣ������滻���������������λ�ã���������λ�ò�����ԭ����λ����ͬ
				int i = 0;
				do {
					pos = hash(e, random.nextInt(numHashFunctions));
				} while (pos == lastPos && i++ < 5);
				// �����滻����
				@SuppressWarnings("unchecked")
				E temp = (E) array[lastPos = pos];
				array[pos] = e;
				e = temp;
			}
			// �������������ǲ���ʧ�ܣ�����������rehash����
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
	 * ��ѯԪ�ص�λ�ã����ҵ�Ԫ�أ��򷵻��䵱ǰλ�ã����򷵻�-1
	 * 
	 * @param e
	 * @return
	 */
	private int getIndex(E e) {
		// ����ɢ�к������ϣ���Ϊ��ȷ��Ԫ�����õ�ɢ�к���Ϊ�ĸ�
		for (int i = 0; i < numHashFunctions; i++) {
			// ��ȡ����ǰhashֵ
			int index = hash(e, i);
			// �жϱ����Ƿ���ڵ�ǰԪ��
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
	 *            ��ǰ��Ԫ��
	 * @param which
	 *            ѡȡ��ɢ�к�����Ӧ��λ��
	 * @return
	 */
	private int hash(E e, int which) {
		// ����ɢ�к��������е�hash������ȡ��hashֵ
		int hashVal = hashFunctions.hash(e, which);
		// ����һ���Ĵ���
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
		// ���岼����ɢ��
		CuckooHashTable<String> table = new CuckooHashTable<String>(new MyHashFamily<String>());
		String[] strs = { "����", "����", "����", "����" };
		// ����
		for (int i = 0; i < strs.length; i++) {
			table.add(strs[i]);
		}
		// ��ӡ��
		table.printArray();
	}

}
