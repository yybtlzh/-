package com.yybt.datastructure.array;

/**
 * ����
 * @author liuzehong
 *
 */
public class MyArray {
	
	private long[] arr;
	//��ʾ��Ч���ݵĳ���
	private int elements;
	
	public MyArray() {
		this(50);
	}
	
	public MyArray(int maxsize) {
		arr = new long[maxsize];
	}
	
	/**
	 * �������
	 */
	public void insert(long value) {
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
	public int search(long value) {
		int i;
		for(i = 0; i < elements; i++) {
			if(value == arr[i]) {
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
	public long get(int index) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return arr[index];
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
	public void change(int index, int newvalue) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			arr[index] = newvalue;
		}
	}
}
