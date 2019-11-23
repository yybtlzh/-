package com.yybt.datastructure.array;

/**
 * ��������
 * @author liuzehong
 *
 */
public class MyOrderArray {
	private long[] arr;
	//��ʾ��Ч���ݵĳ���
	private int elements;
	
	public MyOrderArray() {
		this(50);
	}
	
	public MyOrderArray(int maxsize) {
		arr = new long[maxsize];
	}
	
	/**
	 * �������
	 */
	public void insert(long value) {
		int i;
		for(i = 0; i < elements; i++) {
			if(arr[i] > value) {
				break;
			}
		}
		
		for(int j = elements; j > i; j--) {
			arr[j] = arr[j - 1];
		}
		arr[i] = value;
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
	 * ���ַ���������
	 */
	public int binarySearch(long value) {
		int middle = 0;
		int low = 0;
		int pow = elements;
		
		while(true) {
			middle = (pow + low) / 2;
			if(arr[middle] == value) {
				return middle;
			} else if(low > pow) {
				return -1;
			} else {
				if(arr[middle] > value) {
					pow = middle - 1;
				} else {
					low = middle + 1;
				}
			}
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
