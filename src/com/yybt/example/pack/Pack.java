package com.yybt.example.pack;
/**
 * ���ݷ������������
 * @author liuzehong
 *
 */
public class Pack {
	int n = 8;  //��Ʒ����
	int W = 110;  //����������
	int[] Weights = {1,11,21,23,33,43,45,32};  //��������
    int[] Values = {11,21,31,33,43,53,55,108};  //��ֵ����
	int bestValue = 0;   //��õ�����ֵ
	//��������
	public void BackTrack(int depth,int preWeights,int preValues){
		int currentWeight = preWeights;
		int currentValue = preValues;
		//����
		if(depth >= n){ //�ﵽ������
			if(bestValue < currentValue){
				bestValue = currentValue;
			}
			return ;
		}
		if(currentWeight+Weights[depth] < W){  //�Ƿ�����Լ������
			currentWeight +=Weights[depth];
			currentValue +=Values[depth];
			//ѡȡ�˵�i����Ʒ
			BackTrack(depth+1,currentWeight,currentValue);   //�ݹ������һ����Ʒ
			//�ָ������������ͼ�ֵ
			currentWeight -= Weights[depth];
			currentValue -= Values[depth];
		}
		//��ѡȡ��i����Ʒ
		BackTrack(depth+1,currentWeight,currentValue);
	}
	
	public int GetBestValue(){
		BackTrack(0,0,0);
		return bestValue;
	}
 



	public static void main(String[] args) {

		Pack pack = new Pack();

		int bestValue = pack.GetBestValue();

		System.out.println("�����������Ʒ��ֵΪ��"+bestValue);

	}

}
