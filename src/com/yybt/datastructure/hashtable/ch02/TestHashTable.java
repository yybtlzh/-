package com.yybt.datastructure.hashtable.ch02;

public class TestHashTable {
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		Info  info=new Info();
		info.setData(new Person("1","����"));
		info.setKey("����");
		ht.insert(info);
		System.out.println(ht.find("����").getData());
		System.out.println(ht.find("ct").getData());
		System.out.println(ht.find("b").getData());
		System.out.println(ht.find("dt").getData());
		
//		System.out.println(ht.hashCode("a"));
//		System.out.println(ht.hashCode("ct"));
	}
}
