package com.yybt.algorithm.hash.ch01;

/**
 * 
 * @ClassName: Student
 * 
 **/

public class Student {

	// 学号，这里用来作为key

	private String key;

	// 学生姓名

	private String name;

	public Student() {

		super();

	}

	public Student(String key, String name) {

		super();

		this.key = key;

		this.name = name;

	}

	public String getKey() {

		return key;

	}

	public void setKey(String key) {

		this.key = key;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

}