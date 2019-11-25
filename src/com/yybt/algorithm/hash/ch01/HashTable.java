package com.yybt.algorithm.hash.ch01;

/**
 * 
 * @ClassName: HashCode
 * @date 2018年12月28日
 * 
 **/

public class HashTable {

	// 数据域

	private Student[] array;

	/**
	 * 
	 * 初始化HashTable.
	 * 
	 */

	public HashTable() {
		this(16);
	}

	/**
	 * * 初始化HashTable.
	 * 
	 */

	public HashTable(int valsize) {
		this.array = new Student[valsize];

	}

	/**
	 * 
	 * 添加学生
	 * 
	 * @param stu
	 * 
	 */

	public void insert(Student stu) {

		array[hashcode(stu.getKey())] = stu;

	}

	/**
	 * 
	 * 删除学生
	 * 
	 * @param key
	 * 
	 */

	public void delete(String key) {

		array[hashcode(key)].setName(null);

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
	 * 批量添加学生(开放地址法解决哈希冲突)
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
	 * 自定义哈希
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

		// 为了便于演示哈希冲突，将哈希值取模，压缩至0-9

		return hashVal % 10;

	}

	/**
	 * 
	 * 添加学生(开放地址法解决哈希冲突)
	 * 
	 * @param stu
	 * 
	 */

	public void insert2(Student stu) {

		// 得到当前key的哈希值

		int hashVal = hashcode(stu.getKey());

		while (array[hashVal] != null // 哈希值没被占用

				&& array[hashVal].getName() != null // 学生未被删除

		) {

			++hashVal;

			// 循环

			hashVal %= 10;

		}

		array[hashVal] = stu;

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

	/*
	 * public String getNameByKey(String key) {
	 * 
	 * return array[hashcode(key)].getName();
	 * 
	 * }
	 */
     /**
      * (开放地址法解决哈希冲突)
      * @param key
      * @return
      */
	public String getNameByKey2(String key) {

		// 得到当前key的哈希值

		int hashVal = hashcode(key);

		while (array[hashVal] != null) { // 哈希值有对应的数据

			if (array[hashVal].getKey().equals(key)) {

				return array[hashVal].getName();

			}

			++hashVal;

			hashVal %= array.length;

		}

		return null;

	}

	public static void main(String[] args) {
        //未解决哈希冲突前
		HashTable hashTable = new HashTable();
		Student stu1 = new Student("201801", "张三");
		Student stu2 = new Student("201802", "李四");
		Student stu3 = new Student("201803", "王五");
		hashTable.insert(stu1, stu2, stu3);
		System.out.println(hashTable.getNameByKey("201802"));
		System.out.println(hashTable.getNameByKey("201803"));
		System.out.println(hashTable.getNameByKey("201801"));

		// 利用开放地址法解决哈希冲突后
		hashTable = new HashTable();
		hashTable.insert2(stu1, stu2, stu3);
		System.out.println(hashTable.getNameByKey2("201802"));
		System.out.println(hashTable.getNameByKey2("201803"));
		System.out.println(hashTable.getNameByKey2("201801"));
	}
}