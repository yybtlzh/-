package com.yybt.example.Random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 
  * @ClassName: RandomUtil
  * @Description: 根据权重生成随机数(可以是任意类型)
  * @author liuzehong
 *
 */
public class RandomUtil {
    
	/**
	 * 
	  * weightRandom(生成权重随机)
	  * @Title: weightRandom
	  * @param @param weightMap(键表示值，值代表权重)
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String weightRandom(Map<String, Integer> weightMap) { 

        if(weightMap== null||weightMap.size()==0) {
        	return null;  
        }
        int sum = 0;  
        //比例求和
        for (Integer value : weightMap.values()) { 
             sum += value;  
        }  
        //1到sum范围内 随机生成一个数
        int rand = new Random().nextInt(sum) + 1;  
        //wtf  value越大，rand<0的概率就越大，从而达到权重随机的的目的
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
		//权重是所有value的比值
		weightMap.put("10元红包",1);
		weightMap.put("100元红包",10);
		weightMap.put("1000元红包",50);
		weightMap.put("10000元红包",100);
		weightMap.put("1000000元红包",100000);
		
		Map<String, Integer> results = new HashMap<String,Integer>();
		//测试出现概率
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
