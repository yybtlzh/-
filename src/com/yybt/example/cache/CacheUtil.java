package com.yybt.example.cache;

import com.yybt.example.cache.impl.FIFOCache;

public class CacheUtil {
	
	
	/**
	 * 创建一个FIFO缓存对象
	 * @param capacity
	 * @param timeout
	 * @return
	 */
	public static <K, V> FIFOCache<K, V> createFIFOCache(int capacity, long timeout){
		return new FIFOCache<K, V>(capacity, timeout);
	}
	
	/**
	 * 创建一个FIFO缓存对象
	 * @param capacity
	 * @param timeout
	 * @return
	 */
	public static <K, V> FIFOCache<K, V> createFIFOCache(int capacity){
		return new FIFOCache<K, V>(capacity);
	}

}
