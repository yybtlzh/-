package com.yybt.example.recursion;


/**
 * 汉诺塔（d递归经典应用）
 * @author lx
 *
 */
public class HanoiTower {
	
	/**
	 * 移动盘子
	 * topN:移动的盘子数
	 * from:起始塔座
	 * inter:中间塔座
	 * to:目标塔座
	 */
	public static void doTower(int topN,char from,char inter,char to) {
		if(topN == 1) {
			System.out.println("盘子1,从"+ from + "塔座到" + to + "塔座");
		} else {
			doTower(topN - 1, from, to, inter);
			System.out.println("盘子" + topN +",从" + from + "塔座到" + to + "塔座" );
			doTower(topN - 1, inter, from, to);
		}
	}
	
	public static void main(String[] args) {
		HanoiTower.doTower(10, 'A', 'B', 'C');
	}
}
