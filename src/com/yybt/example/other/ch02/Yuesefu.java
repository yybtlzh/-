/**
 * @Title: Yuesefu.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.example.other.ch02;

import com.yybt.datastructure.linkedlist.CylceLinkList;
import com.yybt.datastructure.linkedlist.Node;

/**
  * @ClassName: Yuesefu
  * @Description: 循环链表的应用：约瑟夫问题
  * @author liuzehong
 **/
public class Yuesefu {
	

	public  void deal(int total,int n) {
		CylceLinkList<Integer> linkList=new CylceLinkList<>();
		//初始化,编号1-total
		for (int i = 1; i <= total; i++) {
			linkList.insert(i);
		}
		Node<Integer> first=linkList.first;
		Node<Integer> temp=first;
		while (temp!=temp.next) {
			/*for (int i = 1; i <n; i++) {
				temp=temp.next;
			}*/
			//小改进：减少了一次循环，搜嘎
			temp=temp.next.next;
			//自杀：也就是从链表中删除
			System.out.print(linkList.delete(temp.data).data+"\t");
			temp=temp.next;
		}
		System.out.println();
		System.out.print("最后死的那位是：");linkList.display();
		System.out.println("结束");
		
	}
	
	public static void main(String[] args) {
		new Yuesefu().deal(41, 3);
	}
   
}
