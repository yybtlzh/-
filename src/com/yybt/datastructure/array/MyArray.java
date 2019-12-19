package com.yybt.datastructure.array;

/**
 * 数组
 * @author liuzehong
 * 改用泛型，这里仍懒得考虑数组扩容问题，太low
 *
 */
public class MyArray<T extends Comparable<T>>  {
	
	private Object[] arr;
	//表示有效数据的长度
	private int elements;
	
	public MyArray() {
		this(50);
	}
	
	public MyArray(int maxsize) {
		arr = new Object[maxsize];
	}
	
	/**
	 * 添加数据
	 */
	public void insert(T value) {
		arr[elements] = value;
		elements++;
	}
	
	/**
	 * 显示数据
	 */
	public void display() {
		System.out.print("[");
		for(int i = 0; i < elements; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * 查找数据
	 */
	@SuppressWarnings("unchecked")
	public int search(T value) {
		int i;
		for(i = 0; i < elements; i++) {
			if(value.compareTo((T)arr[i])==0) {
				break;
			}
		}
		
		if(i == elements) {
			return -1;
		} else {
			return i;
		}
		
	}
	
	/**
	 * 查找数据，根据索引来查
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return (T)arr[index];
		}
	}
	
	/**
	 * 删除数据
	 */
	public void delete(int index) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			for(int i = index; i < elements; i++) {
				arr[index] = arr[index + 1];
			}
			elements--;
		}
	}
	
	/**
	 * 更新数据
	 */
	public void change(int index, T newvalue) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			arr[index] = newvalue;
		}
	}
}
