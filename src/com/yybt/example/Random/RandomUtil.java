package com.yybt.example.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 
  * @ClassName: RandomUtil
  * @Description: ����Ȩ�����������(��������������)
  * @author liuzehong
 *
 */
public class RandomUtil {
    
	/**
	 * 
	  * weightRandom(����Ȩ�����)
	  * @Title: weightRandom
	  * @param @param weightMap(����ʾֵ��ֵ����Ȩ��)
	  * @param @return    �趨�ļ�
	  * @return String    ��������
	  * @throws
	 */
	public static String weightRandom(Map<String, Integer> weightMap) { 

        if(weightMap== null||weightMap.size()==0) {
        	return null;  
        }
        int sum = 0;  
        //�������
        for (Integer value : weightMap.values()) { 
             sum += value;  
        }  
        //1��sum��Χ�� �������һ����
        int rand = new Random().nextInt(sum) + 1;  
        //wtf  valueԽ��rand<0�ĸ��ʾ�Խ�󣬴Ӷ��ﵽȨ������ĵ�Ŀ��
        for (Map.Entry<String, Integer> entry : weightMap.entrySet()) {
            rand -= entry.getValue();
            if (rand <= 0) {
            	String item = entry.getKey();
                return item;
            }
        } 
        return null;  
   }
	
	public static void main(String[] args) {
		Map<String, Integer> weightMap = new HashMap<String,Integer>();
		//Ȩ��������value�ı�ֵ
		weightMap.put("10Ԫ���",1);
		weightMap.put("100Ԫ���",10);
		weightMap.put("1000Ԫ���",50);
		weightMap.put("10000Ԫ���",100);
		weightMap.put("1000000Ԫ���",100000);
		
		Map<String, Integer> results = new HashMap<String,Integer>();
		//���Գ��ָ���
        for (int i = 0; i < 10000000; i++) {
            String result = weightRandom(weightMap);
            if (results.containsKey(result)) {
            	results.put(result, (results.get(result)+1));
            } else {
            	results.put(result, 1);
            }
        }
        System.out.println(results);
	}

}
