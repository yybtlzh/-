package com.yybt.datastructure.linkedlist;

public class TestLinkList {
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		linkList.insertFirst(34);
		linkList.insertFirst(23);
		linkList.insertFirst(12);
		linkList.insertFirst(0);
		linkList.insertFirst(-1);
		
		 linkList.delete(34);
		System.out.println();
		linkList.display();
	}
}
