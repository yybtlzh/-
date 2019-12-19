package com.yybt.datastructure.queue;

/**
 *队列
 * @author liuzehong
 *
 */
public class MyCycleQueue<T> {
	//底层使用数组
	private Object[] arr;
	//有效数据的大小
	private int elements;
	//队头
	private int front;
	//队尾
	private int end;
	
	/**
	 * 默认构造方法
	 */
	public MyCycleQueue() {
		this(10);
	}
	
	/**
	 * 带参数的构造方法，参数为数组的大小
	 */
	public MyCycleQueue(int maxsize) {
		arr = new Object[maxsize];
		elements = 0;
		front = 0;
		end = -1;
	}
	
	/**
	 * 添加数据,从队尾插入
	 */
	public void insert(T value) {
		if(end == arr.length - 1) {
			end = -1;
		}
		arr[++end] = value;
		elements++;
	}
	
	/**
	 * 删除数据，从队头删除
	 */
	public T remove() {
		@SuppressWarnings("unchecked")
		T value = (T) arr[front++];
		if(front == arr.length) {
			front = 0;
		}
		elements--;
		return value;
	}
	
	/**
	 * 查看数据，从队头查看
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T) arr[front];
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	/**
	 * 判断是否满了
	 */
	public boolean isFull() {
		return elements == arr.length;
	}
	
	public int size() {
		return elements;
	}
}
