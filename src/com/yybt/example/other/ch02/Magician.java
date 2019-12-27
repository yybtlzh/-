/**
 * @Title: Moshushi.java
 * @Package com.yybt.example.other.ch02
 */
package com.yybt.example.other.ch02;

import com.yybt.datastructure.linkedlist.CylceLinkList;
import com.yybt.datastructure.linkedlist.Node;

/**
 * @ClassName: Magician
 * @Description: 循环链表的应用：魔术师发牌问题《详细场景描述可以自行摆渡》
 * @author liuzehong
 **/
public class Magician {

	public void deal() {
		
		CylceLinkList<Integer> linkList = new CylceLinkList<>();
		// 初始化
		for (int i = 0; i < 13; i++) {
			linkList.insert(0);
		}
		Node<Integer> first = linkList.first;
		// 第一张肯定是1
		first.data = 1;
		Node<Integer> temp = first;
		int i = 2;
		//外层控制循环次数，层循环寻找摆放位置
		while (true) {
			for (int j = 0; j < i; j++) {
				temp = temp.next;
				//如果data不是0，说明该位置已经有扑克占用，循环次数需要再增加一次，因此j--
				if (temp.data != 0) {
					j--;
				}
			}
			temp.data = i;
			i++;
			//死循环出口
			if (i > 13) {
				break;
			}
		}
		linkList.display();
		System.out.println("结束");
	}

	public static void main(String[] args) {
		Magician Moshushi = new Magician();
		Moshushi.deal();
	}

}
