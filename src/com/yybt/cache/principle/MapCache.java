package com.yybt.cache.principle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map���������
 * 
 * @author lx
 *
 */
public class MapCache {
	
	private static Map<String, Object> cacheMap = new HashMap<>();              //�洢ʵ�����      ��-ֵ

	private static Map<String, Object> entityMap = new HashMap<>();          //�洢ʵ������  ��-����

	private static MapCache mapCache = null;

	private MapCache() {

	}
	/**
	 * ��ȡһ�����������ʵ��(����)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static MapCache getInstance() {
		if (mapCache == null) {
			mapCache = new MapCache();
			Thread t = new ClearCache();
			t.start();
			try {
			//	t.sleep(1000*60*60);   //ÿ��1Сʱ���һ�ι��ڻ���
				t.sleep(1000);   //ÿ��1Сʱ���һ�ι��ڻ���
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return mapCache;
	}
	/**
	 * 
	  * addCache(��ӻ���)
	  * @Title: addCache
	  * @param @param key
	  * @param @param value
	  * @param @param cacheEntity
	  * @param @return    �趨�ļ�
	  * @return boolean    ��������
	  * @throws
	 */
	public boolean addCache(String key, Object value, CacheEntity cacheEntity) {
		boolean flag = false;
		cacheMap.put(key, value);
		entityMap.put(key, cacheEntity);
		flag = true;
		return flag;
	}
	/**
	 * 
	  * getValue(��ȡ����ʵ��)
	  * @Title: getValue
	  * @param @param key
	  * @param @return    �趨�ļ�
	  * @return Object    ��������
	  * @throws
	 */
	public Object getValue(String key) {
		return cacheMap.get(key);
	}
	/**
	 * 
	  * getSize(��ȡ��������)
	  * @Title: getSize
	  * @param @return    �趨�ļ�
	  * @return int    ��������
	  * @throws
	 */
	public int getSize() {
		return cacheMap.size();
	}
	/**
	 * 
	  * removeCache(ɾ������)
	  * @Title: removeCache
	  * @param @param key
	  * @param @return    �趨�ļ�
	  * @return boolean    ��������
	  * @throws
	 */
	public boolean removeCache(Object key) {
		boolean flag = false;
			cacheMap.remove(key);
			entityMap.remove(key);
			flag = true;
		return flag;
	}
	/**
	 * ��ջ�����
	 * 
	 * @author lx
	 *
	 */
	private static class ClearCache extends Thread {
		public void run() {
			//����entityMap
			 for (Entry<String, Object> entry : entityMap.entrySet()) {
				//��ȡ����ʵ��
				String key = entry.getKey();
				CacheEntity cacheEntity = (CacheEntity) entityMap.get(key);
				if (!cacheEntity.isForever()) {   //�ǳ־û���
					if ((new Date().getTime() - cacheEntity.getBeginTime()) 
							>= cacheEntity.getDurableTime() * 60 * 1000) {  //�жϻ����Ƿ����
						cacheMap.remove(key);
						entityMap.remove(key);
					}
				}
	         }
		}
	}
	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
			System.out.println("������ػ���addData()��������������");
			MapCache mapCache=MapCache.getInstance();
			CacheEntity cModel=new CacheEntity();
			Date d=new Date();
			cModel.setBeginTime(d.getTime());
			cModel.setDurableTime(60);
			cModel.setForever(false);
			mapCache.addCache("kk", "123", cModel);//�ڻ����ֵ
			System.out.println("kk="+mapCache.getValue("kk"));
	}
}