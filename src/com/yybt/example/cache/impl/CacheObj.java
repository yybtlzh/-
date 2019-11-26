package com.yybt.example.cache.impl;

/**
 * �������
 * @author lx
 * @param <K>
 * @param <V>
 */
public class CacheObj<K, V> {
	
	protected final K key;
	protected final V obj;
	
	/** �ϴη���ʱ�� */
	private long lastAccess=0; 
	/** ���ʴ��� */
	protected long accessCount=0;
	/** ������ʱ����0��ʾ���ô��*/
	private long durableTime=0;
	
	/**
	 * ����
	 * @param key ��
	 * @param obj ֵ
	 * @param ttl ��ʱʱ��
	 */
	protected CacheObj(K key, V obj, long durableTime) {
		this.key = key;
		this.obj = obj;
		this.durableTime = durableTime;
		this.lastAccess = System.currentTimeMillis();
	}
	
	/**
	 * @return �Ƿ����
	 */
	boolean isExpired() {
		//��������д�����ڵĻ���
		if(this.durableTime > 0) {
			final long expiredTime = this.lastAccess + this.durableTime;
			if(expiredTime > 0 && expiredTime < System.currentTimeMillis()) {
			    //��ǰʱ�䳬������ʱ�䣬��ʾ����
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ��ȡֵ
	 * @param isUpdateLastAccess �Ƿ����������ʱ��
	 * @return ��ö���
	 */
	V get(boolean isUpdateLastAccess) {
		if(isUpdateLastAccess) {
			lastAccess = System.currentTimeMillis();
		}
		accessCount++;
		return obj;
	}
	
	/**
	 * ��ȡ��
	 * @return ���ؼ�
	 */
	public K getKey() {
		return this.key;
	}
	
	/**
	 * ��ȡֵ
	 * @return ֵ
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
