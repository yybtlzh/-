package com.yybt.datastructure.queue;

/**
 *����
 * @author liuzehong
 *
 */
public class MyCycleQueue<T> {
	//�ײ�ʹ������
	private Object[] arr;
	//��Ч���ݵĴ�С
	private int elements;
	//��ͷ
	private int front;
	//��β
	private int end;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MyCycleQueue() {
		this(10);
	}
	
	/**
	 * �������Ĺ��췽��������Ϊ����Ĵ�С
	 */
	public MyCycleQueue(int maxsize) {
		arr = new Object[maxsize];
		elements = 0;
		front = 0;
		end = -1;
	}
	
	/**
	 * �������,�Ӷ�β����
	 */
	public void insert(T value) {
		if(end == arr.length - 1) {
			end = -1;
		}
		arr[++end] = value;
		elements++;
	}
	
	/**
	 * ɾ�����ݣ��Ӷ�ͷɾ��
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
	 * �鿴���ݣ��Ӷ�ͷ�鿴
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T) arr[front];
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	/**
	 * �ж��Ƿ�����
	 */
	public boolean isFull() {
		return elements == arr.length;
	}
	
	public int size() {
		return elements;
	}
}
