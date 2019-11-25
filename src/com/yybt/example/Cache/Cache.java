package com.yybt.example.Cache;

import java.util.Iterator;
/**
 * 缓存接口
 * @author lx
 * @param <K>
 * @param <V>
 */
public interface Cache<K, V> extends Iterable<V> {

	/**
	 * @return 返回缓存容量
	 */
	int capacity();

	/**
	 * @return 缓存失效时长，单位毫秒
	 */
	long timeout();

	/**
	 * 添加使用默认失效时长
	 * @param key 键
	 * @param object 缓存的对象
	 */
	void put(K key, V object);

	/**
	 * 将对象加入到缓存，使用指定失效时长
	 * @param key 键
	 * @param object 缓存的对象
	 * @param timeout 失效时长，单位毫秒
	 */
	void put(K key, V object, long timeout);

	/**
	 * 从缓存中获得对象
	 * 调用此方法时，会检查上次调用时间，如果与当前时间差值大于超时时间返回
	 * 每次调用此方法会刷新最后访问时间，也就是说会重新计算超时时间。
	 * 
	 * @param key 键
	 * @return 键对应的对象
	 * @see #get(Object, boolean)
	 */
	V get(K key);

	/**
	 * 从缓存中获得对象，当对象不在缓存中或已经过期返回null
	 * 调用此方法时，会检查上次调用时间，如果与当前时间差值大于超时时间返回null否则返回值。
	 * @param key 键
	 * @param isUpdateLastAccess 是否更新最后访问时间，即重新计算超时时间。
	 * @return 键对应的对象
	 */
	V get(K key, boolean isUpdateLastAccess);

	/**
	 * 返回缓存迭代器
	 * 
	 * @return 缓存迭代器
	 */
	@Override
	Iterator<V> iterator();

	/**
	 * 从缓存中清理过期对象，清理策略取决于具体实现
	 * @return 清理的缓存对象个数
	 */
	int refresh();

	/**
	 * @return 缓存是否已满，仅用于有空间限制的缓存对象
	 */
	boolean isFull();

	/**
	 * 从缓存中移除对象
	 * @param key 键
	 */
	void remove(K key);

	/**
	 * 清空缓存
	 */
	void clear();

	/**
	 * 缓存的对象数量
	 * @return 缓存的对象数量
	 */
	int size();

	/**
	 * @return 缓存是否为空
	 */
	boolean isEmpty();

	/**
	 * 是否包含key
	 * @param key KEY
	 * @return 是否包含key
	 */
	boolean isContainsKey(K key);
}
