package com.yybt.algorithm.hash.ch02;

import com.yybt.algorithm.hash.ch01.Student;

/**
 * 改造HashTable
 * 
 * @ClassName: HashCode
 * 
 * @date 2018年12月28日
 * 
 **/

public class HashTable {

	// 数据域

	private LinkList[] array;

	/**
	 * 
	 * 初始化HashTable.
	 * 
	 */

	public HashTable() {

		array = new LinkList[100];

	}

	public void insert(Student stu) {

		// 得到当前key的哈希值

		int hashVal = hashcode(stu.getKey());

		if (array[hashVal] == null) {

			array[hashVal] = new LinkList();

		}

		array[hashVal].insert(stu);

	}

	/**
	 * 
	 * 批量添加学生
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
	 * 查找学生
	 * 
	 * @param key
	 * 
	 * @return
	 * 
	 */

	public String getNameByKey(String key) {

		// 得到当前key的哈希值

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

		// 为了便于演示哈希冲突，将哈希值取模，压缩至0-9

		return hashVal % 10;

	}

	public static void main(String[] args) {

		HashTable hashTable = new HashTable();

		Student stu1 = new Student("201801", "张三");

		Student stu2 = new Student("201802", "李四");

		Student stu3 = new Student("201803", "王五");

		hashTable.insert(stu1, stu2, stu3);

		System.out.println(hashTable.getNameByKey("201802"));

		System.out.println(hashTable.getNameByKey("201803"));

		System.out.println(hashTable.getNameByKey("201801"));

	}
}
