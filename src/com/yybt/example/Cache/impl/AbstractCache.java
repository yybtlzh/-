package com.yybt.example.Cache.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import com.yybt.example.Cache.Cache;

/**
 * ��ʱ�����ƴ�С�Ļ����Ĭ��ʵ��
 * @author lx
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

	protected Map<K, CacheObj<K, V>> cacheMap;
	private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
	
	private final ReadLock readLock = cacheLock.readLock();
	private final WriteLock writeLock = cacheLock.writeLock();

	/** ���ػ���������0�޴�С���� */
	protected int capacity=0;
	/** ����ʧЧʱ���� 0��ʾ�����ƣ���λ���� */
	protected long timeout=0;

	/** ÿ�������Ƿ��е�����ʧЧʱ�������ھ���������ڶ����Ƿ��б�Ҫ�� */
	protected boolean existCustomTimeout;

	/** ������ */
	protected int hitCount=0;
	/** ��ʧ�� */
	protected int missCount=0;
	
	@Override
	public void put(K key, V obj) {
		put(key, obj, timeout);
	}

	@Override
	public void put(K key, V object, long timeout) {
		writeLock.lock();
		try {
			CacheObj<K, V> cacheObj = new CacheObj<K, V>(key, object, timeout);
			if (timeout != 0) {
				this.existCustomTimeout = true;
			}
			if (isFull()) {
				pruneCache();
			}
			cacheMap.put(key, cacheObj);
		} finally {
			writeLock.unlock();
		}
	}
	
	/**
	 * �Ƿ��������key
	 */
	@Override
	public boolean isContainsKey(K key) {
		readLock.lock();
		try {
			// �����ڻ����Ƴ�
			final CacheObj<K, V> cacheObj = cacheMap.get(key);
			if (cacheObj == null) {
				return false;
			}
			if (!cacheObj.isExpired()) {
				return true;
			}
		} finally {
			readLock.unlock();
		}
		// ����
		remove(key, true);
		return false;
	}

	/**
	 * @return ������
	 */
	public int getHitCount() {
		this.readLock.lock();
		try {
			return hitCount;
		} finally {
			this.readLock.unlock();
		}
	}

	/**
	 * @return ��ʧ��
	 */
	public int getMissCount() {
		this.readLock.lock();
		try {
			return missCount;
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public V get(K key) {
		return get(key, true);
	}

	@Override
	public V get(K key, boolean isUpdateLastAccess) {
		readLock.lock();
		try {
			// �����ڻ����Ƴ�
			final CacheObj<K, V> cacheObj = cacheMap.get(key);
			if (cacheObj == null) {
				missCount++;
				return null;
			}
			if (!cacheObj.isExpired()) {
				hitCount++;
				return cacheObj.get(isUpdateLastAccess);
			}
		} finally {
			readLock.unlock();
		}
		// ����
		remove(key, true);
		return null;
	}

	

	/**
	 * ����ʵ��
	 * 
	 * @return ������
	 */
	protected abstract int pruneCache();

	@Override
	public final int refresh() {
		writeLock.lock();
		try {
			return pruneCache();
		} finally {
			writeLock.unlock();
		}
	}
	
	@Override
	public int capacity() {
		return capacity;
	}

	/**
	 * @return Ĭ�ϻ���ʧЧʱ����<br>
	 * ÿ��������Ե�������ʧЧʱ��
	 */
	@Override
	public long timeout() {
		return timeout;
	}
	/**
	 * �����Ƿ�����
	 */
	@Override
	public boolean isFull() {
		this.readLock.lock();
		try {
			return capacity > 0 && cacheMap.size() >= capacity;
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public void remove(K key) {
		remove(key, false);
	}

	@Override
	public void clear() {
		writeLock.lock();
		try {
			cacheMap.clear();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public int size() {
		this.readLock.lock();
		try {
			return cacheMap.size();
		} finally {
			this.readLock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		this.readLock.lock();
		try {
			return cacheMap.isEmpty();
		} finally {
			this.readLock.unlock();
		}
	}
	
	/**
	 * �Ƴ�key��Ӧ�Ķ���
	 * @param key ��
	 * @param withMissCount �Ƿ������ʧ��
	 */
	private void remove(K key, boolean withMissCount) {
		writeLock.lock();
		try {
			cacheMap.remove(key);
			if (withMissCount) {
				this.missCount--;
			}
		} finally {
			writeLock.unlock();
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<V> iterator() {
		return (Iterator<V>) cacheMap.entrySet().iterator();
	}
}
