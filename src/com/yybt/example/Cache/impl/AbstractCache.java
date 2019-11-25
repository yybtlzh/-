package com.yybt.example.Cache.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import com.yybt.example.Cache.Cache;

/**
 * 超时和限制大小的缓存的默认实现
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

	/** 返回缓存容量，0无大小限制 */
	protected int capacity=0;
	/** 缓存失效时长， 0表示无限制，单位毫秒 */
	protected long timeout=0;

	/** 每个对象是否有单独的失效时长，用于决定清理过期对象是否有必要。 */
	protected boolean existCustomTimeout;

	/** 命中数 */
	protected int hitCount=0;
	/** 丢失数 */
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
	 * 是否包含主键key
	 */
	@Override
	public boolean isContainsKey(K key) {
		readLock.lock();
		try {
			// 不存在或已移除
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
		// 过期
		remove(key, true);
		return false;
	}

	/**
	 * @return 命中数
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
	 * @return 丢失数
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
			// 不存在或已移除
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
		// 过期
		remove(key, true);
		return null;
	}

	

	/**
	 * 清理实现
	 * 
	 * @return 清理数
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
	 * @return 默认缓存失效时长。<br>
	 * 每个对象可以单独设置失效时长
	 */
	@Override
	public long timeout() {
		return timeout;
	}
	/**
	 * 缓存是否满了
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
	 * 移除key对应的对象
	 * @param key 键
	 * @param withMissCount 是否计数丢失数
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
