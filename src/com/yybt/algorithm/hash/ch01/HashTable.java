package com.yybt.algorithm.hash.ch01;

/**
 * 
 * @ClassName: HashCode
 * @date 2018��12��28��
 * 
 **/

public class HashTable {

	// ������

	private Student[] array;

	/**
	 * 
	 * ��ʼ��HashTable.
	 * 
	 */

	public HashTable() {
		this(16);
	}

	/**
	 * * ��ʼ��HashTable.
	 * 
	 */

	public HashTable(int valsize) {
		this.array = new Student[valsize];

	}

	/**
	 * 
	 * ���ѧ��
	 * 
	 * @param stu
	 * 
	 */

	public void insert(Student stu) {

		array[hashcode(stu.getKey())] = stu;

	}

	/**
	 * 
	 * ɾ��ѧ��
	 * 
	 * @param key
	 * 
	 */

	public void delete(String key) {

		array[hashcode(key)].setName(null);

	}

	/**
	 * 
	 * �������ѧ��
	 * 
	 * @param stu
	 * 
	 */

	public void insert(Student... stu) {

		for (int i = 0; i < stu.length; i++) {

			insert(stu[i]);

		}

	}

	/**
	 * 
	 * �������ѧ��(���ŵ�ַ�������ϣ��ͻ)
	 * 
	 * @param stu
	 * 
	 */

	public void insert2(Student... stu) {

		for (int i = 0; i < stu.length; i++) {

			insert2(stu[i]);

		}

	}

	public String getNameByKey(String key) {

		return array[hashcode(key)].getName();

	}

	/**
	 * 
	 * �Զ����ϣ
	 * 
	 * @param stu
	 * 
	 */

	public int hashcode(String key) {

		int hashVal = 0;

		for (int i = 0; i < key.length() - 1; i++) {

			int val = key.charAt(i);

			hashVal += val;

		}

		// Ϊ�˱�����ʾ��ϣ��ͻ������ϣֵȡģ��ѹ����0-9

		return hashVal % 10;

	}

	/**
	 * 
	 * ���ѧ��(���ŵ�ַ�������ϣ��ͻ)
	 * 
	 * @param stu
	 * 
	 */

	public void insert2(Student stu) {

		// �õ���ǰkey�Ĺ�ϣֵ

		int hashVal = hashcode(stu.getKey());

		while (array[hashVal] != null // ��ϣֵû��ռ��

				&& array[hashVal].getName() != null // ѧ��δ��ɾ��

		) {

			++hashVal;

			// ѭ��

			hashVal %= 10;

		}

		array[hashVal] = stu;

	}

	/**
	 * 
	 * ����ѧ��
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 */

	/*
	 * public String getNameByKey(String key) {
	 * 
	 * return array[hashcode(key)].getName();
	 * 
	 * }
	 */
     /**
      * (���ŵ�ַ�������ϣ��ͻ)
      * @param key
      * @return
      */
	public String getNameByKey2(String key) {

		// �õ���ǰkey�Ĺ�ϣֵ

		int hashVal = hashcode(key);

		while (array[hashVal] != null) { // ��ϣֵ�ж�Ӧ������

			if (array[hashVal].getKey().equals(key)) {

				return array[hashVal].getName();

			}

			++hashVal;

			hashVal %= array.length;

		}

		return null;

	}

	public static void main(String[] args) {
        //δ�����ϣ��ͻǰ
		HashTable hashTable = new HashTable();
		Student stu1 = new Student("201801", "����");
		Student stu2 = new Student("201802", "����");
		Student stu3 = new Student("201803", "����");
		hashTable.insert(stu1, stu2, stu3);
		System.out.println(hashTable.getNameByKey("201802"));
		System.out.println(hashTable.getNameByKey("201803"));
		System.out.println(hashTable.getNameByKey("201801"));

		// ���ÿ��ŵ�ַ�������ϣ��ͻ��
		hashTable = new HashTable();
		hashTable.insert2(stu1, stu2, stu3);
		System.out.println(hashTable.getNameByKey2("201802"));
		System.out.println(hashTable.getNameByKey2("201803"));
		System.out.println(hashTable.getNameByKey2("201801"));
	}
}