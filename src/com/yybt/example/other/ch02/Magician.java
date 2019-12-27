/**
 * @Title: Moshushi.java
 * @Package com.yybt.example.other.ch02
 */
package com.yybt.example.other.ch02;

import com.yybt.datastructure.linkedlist.CylceLinkList;
import com.yybt.datastructure.linkedlist.Node;

/**
 * @ClassName: Magician
 * @Description: ѭ�������Ӧ�ã�ħ��ʦ�������⡶��ϸ���������������аڶɡ�
 * @author liuzehong
 **/
public class Magician {

	public void deal() {
		
		CylceLinkList<Integer> linkList = new CylceLinkList<>();
		// ��ʼ��
		for (int i = 0; i < 13; i++) {
			linkList.insert(0);
		}
		Node<Integer> first = linkList.first;
		// ��һ�ſ϶���1
		first.data = 1;
		Node<Integer> temp = first;
		int i = 2;
		//������ѭ����������ѭ��Ѱ�Ұڷ�λ��
		while (true) {
			for (int j = 0; j < i; j++) {
				temp = temp.next;
				//���data����0��˵����λ���Ѿ����˿�ռ�ã�ѭ��������Ҫ������һ�Σ����j--
				if (temp.data != 0) {
					j--;
				}
			}
			temp.data = i;
			i++;
			//��ѭ������
			if (i > 13) {
				break;
			}
		}
		linkList.display();
		System.out.println("����");
	}

	public static void main(String[] args) {
		Magician Moshushi = new Magician();
		Moshushi.deal();
	}

}
