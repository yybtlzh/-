package com.yybt.example.pack;
/**
 * 回溯法解决背包问题
 * @author liuzehong
 *
 */
public class Pack {
	int n = 8;  //物品个数
	int W = 110;  //背包总容量
	int[] Weights = {1,11,21,23,33,43,45,32};  //重量数组
    int[] Values = {11,21,31,33,43,53,55,108};  //价值数组
	int bestValue = 0;   //获得的最大价值
	//回溯搜索
	public void BackTrack(int depth,int preWeights,int preValues){
		int currentWeight = preWeights;
		int currentValue = preValues;
		//出口
		if(depth >= n){ //达到最大深度
			if(bestValue < currentValue){
				bestValue = currentValue;
			}
			return ;
		}
		if(currentWeight+Weights[depth] < W){  //是否满足约束条件
			currentWeight +=Weights[depth];
			currentValue +=Values[depth];
			//选取了第i件物品
			BackTrack(depth+1,currentWeight,currentValue);   //递归求解下一个物品
			//恢复背包的容量和价值
			currentWeight -= Weights[depth];
			currentValue -= Values[depth];
		}
		//不选取第i件物品
		BackTrack(depth+1,currentWeight,currentValue);
	}
	
	public int GetBestValue(){
		BackTrack(0,0,0);
		return bestValue;
	}
 



	public static void main(String[] args) {

		Pack pack = new Pack();

		int bestValue = pack.GetBestValue();

		System.out.println("背包内最大物品价值为："+bestValue);

	}

}
