package com.yybt.cache.principle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map缓存管理类
 * 
 * @author lx
 *
 */
public class MapCache {
	
	private static Map<String, Object> cacheMap = new HashMap<>();              //存储实体对象      键-值

	private static Map<String, Object> entityMap = new HashMap<>();          //存储实体属性  键-属性

	private static MapCache mapCache = null;

	private MapCache() {

	}
	/**
	 * 获取一个缓存管理类实例(单例)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static MapCache getInstance() {
		if (mapCache == null) {
			mapCache = new MapCache();
			Thread t = new ClearCache();
			t.start();
			try {
			//	t.sleep(1000*60*60);   //每隔1小时清除一次过期缓存
				t.sleep(1000);   //每隔1小时清除一次过期缓存
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return mapCache;
	}
	/**
	 * 
	  * addCache(添加缓存)
	  * @Title: addCache
	  * @param @param key
	  * @param @param value
	  * @param @param cacheEntity
	  * @param @return    设定文件
	  * @return boolean    返回类型
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
	  * getValue(获取缓存实体)
	  * @Title: getValue
	  * @param @param key
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	public Object getValue(String key) {
		return cacheMap.get(key);
	}
	/**
	 * 
	  * getSize(获取缓存数量)
	  * @Title: getSize
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int getSize() {
		return cacheMap.size();
	}
	/**
	 * 
	  * removeCache(删除缓存)
	  * @Title: removeCache
	  * @param @param key
	  * @param @return    设定文件
	  * @return boolean    返回类型
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
	 * 清空缓存类
	 * 
	 * @author lx
	 *
	 */
	private static class ClearCache extends Thread {
		public void run() {
			//遍历entityMap
			 for (Entry<String, Object> entry : entityMap.entrySet()) {
				//获取缓存实体
				String key = entry.getKey();
				CacheEntity cacheEntity = (CacheEntity) entityMap.get(key);
				if (!cacheEntity.isForever()) {   //非持久缓存
					if ((new Date().getTime() - cacheEntity.getBeginTime()) 
							>= cacheEntity.getDurableTime() * 60 * 1000) {  //判断缓存是否过期
						cacheMap.remove(key);
						entityMap.remove(key);
					}
				}
	         }
		}
	}
	/**
	 * 测试类
	 * @param args
	 */
	public static void main(String[] args) {
			System.out.println("进入加载缓存addData()………………。");
			MapCache mapCache=MapCache.getInstance();
			CacheEntity cModel=new CacheEntity();
			Date d=new Date();
			cModel.setBeginTime(d.getTime());
			cModel.setDurableTime(60);
			cModel.setForever(false);
			mapCache.addCache("kk", "123", cModel);//在缓存加值
			System.out.println("kk="+mapCache.getValue("kk"));
	}
}