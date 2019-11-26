package com.yybt.example.cache.impl;

/**
 * 缓存对象
 * @author lx
 * @param <K>
 * @param <V>
 */
public class CacheObj<K, V> {
	
	protected final K key;
	protected final V obj;
	
	/** 上次访问时间 */
	private long lastAccess=0; 
	/** 访问次数 */
	protected long accessCount=0;
	/** 对象存活时长，0表示永久存活*/
	private long durableTime=0;
	
	/**
	 * 构造
	 * @param key 键
	 * @param obj 值
	 * @param ttl 超时时长
	 */
	protected CacheObj(K key, V obj, long durableTime) {
		this.key = key;
		this.obj = obj;
		this.durableTime = durableTime;
		this.lastAccess = System.currentTimeMillis();
	}
	
	/**
	 * @return 是否过期
	 */
	boolean isExpired() {
		//这里针对有存活周期的缓存
		if(this.durableTime > 0) {
			final long expiredTime = this.lastAccess + this.durableTime;
			if(expiredTime > 0 && expiredTime < System.currentTimeMillis()) {
			    //当前时间超过过期时间，表示过期
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取值
	 * @param isUpdateLastAccess 是否更新最后访问时间
	 * @return 获得对象
	 */
	V get(boolean isUpdateLastAccess) {
		if(isUpdateLastAccess) {
			lastAccess = System.currentTimeMillis();
		}
		accessCount++;
		return obj;
	}
	
	/**
	 * 获取键
	 * @return 返回键
	 */
	public K getKey() {
		return this.key;
	}
	
	/**
	 * 获取值
	 * @return 值
	 */
	public V getValue() {
		return this.obj;
	}

	@Override
	public String toString() {
		return "CacheObj [key=" + key + ", obj=" + obj + ", lastAccess=" + lastAccess + ", accessCount=" + accessCount
				+ ", durableTime=" + durableTime + "]";
	}
	
	
}
