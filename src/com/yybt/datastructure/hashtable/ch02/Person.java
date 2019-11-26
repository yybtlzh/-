package com.yybt.datastructure.hashtable.ch02;

/*
 * 成员实体
 */
public class Person {
	
	private String id;
	
	private String username;
	
	public Person() {
		super();
	}
	public Person(String id, String username) {
		this.id = id;
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + "]";
	}
	
	

}
