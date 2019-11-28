package com.yybt.example.cache.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �������ʹ���㷨
 * 
 * @author liuzehong
 *
 * @param <K>
 * @param <V>
 */
public class LruCache<K, V> extends AbstractCache<K, V> {

	/**
	 * ���죬Ĭ�϶��󲻹���
	 * @param capacity
	 *            ����
	 */
	public LruCache(int capacity) {
		this(capacity, 0);

	}

	/**
	 * ����
	 * 
	 * @param capacity
	 *            ����
	 * @param timeout
	 *            ����ʱ��
	 */
	public LruCache(int capacity, long timeout) {
		if (Integer.MAX_VALUE == capacity) {
			capacity -= 1;
		}
		this.capacity = capacity;
		this.timeout = timeout;
		//�����Ϳ���ʵ��ɾ��������ٷ��ʵ�Ԫ��
		cacheMap = new MyLinkedHashMap<K, CacheObj<K, V>>(capacity);
	}

	@SuppressWarnings("hiding")
	private class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
		private static final long serialVersionUID = 1L;
		private int capacity;

		public MyLinkedHashMap(int capacity) {
			super(capacity + 1, 0.75f, true);
			this.capacity = capacity;
		}
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			return size() > capacity;
		}
	}

	@Override
	protected int pruneCache() {
		int count = 0;
		Iterator<CacheObj<K, V>> values = cacheMap.values().iterator();
		CacheObj<K, V> cacheObj;
		while (values.hasNext()) {
			cacheObj = values.next();
			if (cacheObj.isExpired()) {
				values.remove();
				count++;
			}
		}
		return count;
	}

	@Override
	public Iterator<V> iterator() {
		return super.iterator();
	}

	public static void main(String[] args) {
		LruCache<Object, Object> LruCache = new LruCache<>(3, 1000);
		LruCache.put("zhangsan", "����");
		LruCache.put("lisi", "����");
		LruCache.put("wangwu", "����");
		LruCache.put("zhaoliu", "����");
		LruCache.put("tianqi", "����");
		LruCache.put("kunba", "����");
		Iterator<Object> iterator = LruCache.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());

		}
	}

}
