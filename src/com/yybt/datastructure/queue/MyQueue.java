package com.yybt.datastructure.queue;


/**
 *队列
 * @author liuzehong
 *
 */
public class MyQueue<T> {
	//底层使用数组
	//private long[] arr;
	//改用泛型
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
	public MyQueue() {
		this(10);
	}
	
	/**
	 * 带参数的构造方法，参数为数组的大小
	 */
	public MyQueue(int maxsize) {
		arr = new Object[maxsize];
		elements = 0;
		front = 0;
		end = -1;
	}
	
	/**
	 * 添加数据,从队尾插入
	 */
	public void insert(T value) {
		arr[++end] = value;
		elements++;
		
	}
	
	/**
	 * 删除数据，从队头删除
	 */
	@SuppressWarnings("unchecked")
	public T remove() {
		elements--;
		return (T) arr[front++];
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
	

	public static void main(String[] args) {
		MyQueue<Integer> queue=new MyQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.insert(i);
		}
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println("有一次");
		queue.insert(3);
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
	}
	
	
}


