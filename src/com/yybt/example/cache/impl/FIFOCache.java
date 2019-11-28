package com.yybt.example.cache.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
/**
 *  FIFO(first in first out) �Ƚ��ȳ�����
 * @author lx
 * @param <K>
 * @param <V>
 */
public class FIFOCache<K, V> extends AbstractCache<K, V> {

	/**
	 * ���죬Ĭ�϶��󲻹���
	 * @param capacity ����
	 */
	public FIFOCache(int capacity) {
		this(capacity, 0);
	}

	/**
	 * ����
	 * @param capacity ����
	 * @param timeout ����ʱ��
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
	 * �Ƚ��ȳ����������<br>
	 * �ȱ�������������ڵĻ�������������������ģ���ɾ����һ���������
	 */
	@Override
	protected int pruneCache() {
		int count = 0;
		CacheObj<K, V> first = null;
		//������ڶ����ҳ�����ͷ��Ԫ�أ�����Ԫ�أ�
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
		//������������������ģ���ɾ����һ��������Ķ���
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
		FIFOCache.put("zhangsan", "����");
		FIFOCache.put("lisi", "����");
		FIFOCache.put("wangwu", "����");
		FIFOCache.put("zhaoliu", "����");
		Iterator<Object> iterator = FIFOCache.iterator();
		while (iterator.hasNext()) {
		System.out.println(iterator.next());
			
		}
	}
}
