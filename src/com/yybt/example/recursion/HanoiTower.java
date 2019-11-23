package com.yybt.example.recursion;


/**
 * ��ŵ����d�ݹ龭��Ӧ�ã�
 * @author lx
 *
 */
public class HanoiTower {
	
	/**
	 * �ƶ�����
	 * topN:�ƶ���������
	 * from:��ʼ����
	 * inter:�м�����
	 * to:Ŀ������
	 */
	public static void doTower(int topN,char from,char inter,char to) {
		if(topN == 1) {
			System.out.println("����1,��"+ from + "������" + to + "����");
		} else {
			doTower(topN - 1, from, to, inter);
			System.out.println("����" + topN +",��" + from + "������" + to + "����" );
			doTower(topN - 1, inter, from, to);
		}
	}
	
	public static void main(String[] args) {
		HanoiTower.doTower(10, 'A', 'B', 'C');
	}
}
