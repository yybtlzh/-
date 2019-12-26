/**
 * @Title: Yuesefu.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.example.other.ch02;

import com.yybt.datastructure.linkedlist.CylceLinkList;
import com.yybt.datastructure.linkedlist.Node;

/**
  * @ClassName: Yuesefu
  * @Description: ѭ�������Ӧ�ã�Լɪ������
  * @author liuzehong
 **/
public class Yuesefu {
	

	public  void deal(int total,int n) {
		CylceLinkList<Integer> linkList=new CylceLinkList<>();
		//��ʼ��,���1-total
		for (int i = 1; i <= total; i++) {
			linkList.insert(i);
		}
		Node<Integer> first=linkList.first;
		Node<Integer> temp=first;
		while (temp!=temp.next) {
			/*for (int i = 1; i <n; i++) {
				temp=temp.next;
			}*/
			//С�Ľ���������һ��ѭ�����Ѹ�
			temp=temp.next.next;
			//��ɱ��Ҳ���Ǵ�������ɾ��
			System.out.print(linkList.delete(temp.data).data+"\t");
			temp=temp.next;
		}
		System.out.println();
		System.out.print("���������λ�ǣ�");linkList.display();
		System.out.println("����");
		
	}
	
	public static void main(String[] args) {
		new Yuesefu().deal(41, 3);
	}
   
}
