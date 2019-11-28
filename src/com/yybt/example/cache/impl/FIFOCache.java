package com.yybt.example.cache.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
/**
 *  FIFO(first in first out) 先进先出缓存
 * @author lx
 * @param <K>
 * @param <V>
 */
public class FIFOCache<K, V> extends AbstractCache<K, V> {

	/**
	 * 构造，默认对象不过期
	 * @param capacity 容量
	 */
	public FIFOCache(int capacity) {
		this(capacity, 0);
	}

	/**
	 * 构造
	 * @param capacity 容量
	 * @param timeout 过期时长
	 */
	public FIFOCache(int capacity, long timeout) {
		if(Integer.MAX_VALUE == capacity) {
			capacity -= 1;
		}
		this.capacity = capacity;
		this.timeout = timeout;
		cacheMap = new LinkedHashMap<K, CacheObj<K, V>>(capacity + 1, 1.0f, false);
	}

	/**
	 * 先进先出的清理策略<br>
	 * 先遍历缓存清理过期的缓存对象，如果清理后还是满的，则删除第一个缓存对象
	 */
	@Override
	protected int pruneCache() {
		int count = 0;
		CacheObj<K, V> first = null;
		//清理过期对象并找出链表头部元素（先入元素）
		Iterator<CacheObj<K, V>> values = cacheMap.values().iterator();
		CacheObj<K, V> cacheObj=null;
		while (values.hasNext()) {
			cacheObj = values.next();
			if (cacheObj.isExpired()) {
				values.remove();
				count++;
			}
			if (first == null) {
				first = cacheObj;
			}
		}
		//清理结束后依旧是满的，则删除第一个被缓存的对象
		if (isFull() && null != first) {
			cacheMap.remove(first.key);
			count++;
		}
		return count;
	}


	@Override
	public Iterator<V> iterator() {
		return super.iterator();
	}
	
	public static void main(String[] args) {
		
		FIFOCache<Object, Object> FIFOCache=new FIFOCache<>(3,1000);
		FIFOCache.put("zhangsan", "张三");
		FIFOCache.put("lisi", "李四");
		FIFOCache.put("wangwu", "王五");
		FIFOCache.put("zhaoliu", "赵六");
		Iterator<Object> iterator = FIFOCache.iterator();
		while (iterator.hasNext()) {
		System.out.println(iterator.next());
			
		}
	}
}
