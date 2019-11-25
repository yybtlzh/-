package com.yybt.algorithm.hash.ch02;

import com.yybt.algorithm.hash.ch01.Student;

/**
 * ����HashTable
 * 
 * @ClassName: HashCode
 * 
 * @date 2018��12��28��
 * 
 **/

public class HashTable {

	// ������

	private LinkList[] array;

	/**
	 * 
	 * ��ʼ��HashTable.
	 * 
	 */

	public HashTable() {

		array = new LinkList[100];

	}

	public void insert(Student stu) {

		// �õ���ǰkey�Ĺ�ϣֵ

		int hashVal = hashcode(stu.getKey());

		if (array[hashVal] == null) {

			array[hashVal] = new LinkList();

		}

		array[hashVal].insert(stu);

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
	 * ����ѧ��
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 */

	public String getNameByKey(String key) {

		// �õ���ǰkey�Ĺ�ϣֵ

		int hashVal = hashcode(key);

		Student student = array[hashVal].getNodeBykey(key).student;

		if (student != null) {

			return student.getName();

		}

		return null;

	}

	public int hashcode(String key) {

		int hashVal = 0;

		for (int i = 0; i < key.length() - 1; i++) {

			int val = key.charAt(i);

			hashVal += val;

		}

		// Ϊ�˱�����ʾ��ϣ��ͻ������ϣֵȡģ��ѹ����0-9

		return hashVal % 10;

	}

	public static void main(String[] args) {

		HashTable hashTable = new HashTable();

		Student stu1 = new Student("201801", "����");

		Student stu2 = new Student("201802", "����");

		Student stu3 = new Student("201803", "����");

		hashTable.insert(stu1, stu2, stu3);

		System.out.println(hashTable.getNameByKey("201802"));

		System.out.println(hashTable.getNameByKey("201803"));

		System.out.println(hashTable.getNameByKey("201801"));

	}
}
