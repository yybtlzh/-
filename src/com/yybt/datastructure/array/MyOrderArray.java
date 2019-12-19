package com.yybt.datastructure.array;


/**
 * 有序数组
 * @author liuzehong
 *
 */
public class MyOrderArray<T extends Comparable<T>> {
	private Object[] arr;
	//表示有效数据的长度
	private int elements;
	
	public MyOrderArray() {
		this(50);
	}
	
	public MyOrderArray(int maxsize) {
		arr = new Object[maxsize];
	}
	
	/**
	 * 添加数据
	 */
	@SuppressWarnings("unchecked")
	public void insert(T value) {
		int i;
		for(i = 0; i < elements; i++) {
			if(value.compareTo((T)arr[i])<0) {
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
	 * 二分法查找数据
	 */
	@SuppressWarnings("unchecked")
	public int binarySearch(T value) {
		int middle = 0;
		int low = 0;
		int pow = elements;
		
		while(true) {
			//防溢出
			middle=(low+(pow-low)/2);
			if(value.compareTo((T)arr[middle])==0) {
				return middle;
			} else if(low > pow) {
				return -1;
			} else {
				if(value.compareTo((T)arr[middle])<0) {
					pow = middle - 1;
				} else {
					low = middle + 1;
				}
			}
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
			return (T) arr[index];
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
	public void change(int index, int newvalue) {
		if(index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			arr[index] = newvalue;
		}
	}
	
	public static void main(String[] args) {
		MyOrderArray<Integer> array=new MyOrderArray<>();
		array.insert(1);
		array.insert(12);
		array.insert(3);
		array.insert(33);
		array.insert(45);
		array.insert(29);
		array.display();
		System.out.println(array.binarySearch(33));
	}
}
