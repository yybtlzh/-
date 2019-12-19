package com.yybt.datastructure.array;

/**
 * ����
 * @author liuzehong
 * ���÷��ͣ����������ÿ��������������⣬̫low
 *
 */
public class MyArray<T extends Comparable<T>>  {
	
	private Object[] arr;
	//��ʾ��Ч���ݵĳ���
	private int elements;
	
	public MyArray() {
		this(50);
	}
	
	public MyArray(int maxsize) {
		arr = new Object[maxsize];
	}
	
	/**
	 * �������
	 */
	public void insert(T value) {
		arr[elements] = value;
		elements++;
	}
	
	/**
	 * ��ʾ����
	 */
	public void display() {
		System.out.print("[");
		for(int i = 0; i < elements; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * ��������
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
	 * �������ݣ�������������
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
	 * ɾ������
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
	 * ��������
	 */
	public void change(int index, T newvalue) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			arr[index] = newvalue;
		}
	}
}
