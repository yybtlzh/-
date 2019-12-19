/**
 * @Title: MyLinkedQueue.java
 * @Package com.yybt.datastructure.queue
 */
package com.yybt.datastructure.queue;

/**
  * @ClassName: MyLinkedQueue
  * @Description: ��ʽ�洢ʵ��
  * @author liuzehong
 **/
public class MyLinkedQueue<T> {
	
	//��Ч���ݵĴ�С
    private int elements;
	//��ͷ
	private Node front;
	//��β
	private Node end;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MyLinkedQueue() {
		front=null;
		end=null;
		elements=0;
	}
	
	/**
	 * �������,�Ӷ�β����
	 */
	public void insert(T value) {
		Node node = new Node(value);
		if (elements==0) {
			front=end=node;
		}else {
			end.next=node;
			end=node;
		}
		elements++;
	}
	
	/**
	 * ɾ�����ݣ��Ӷ�ͷɾ��
	 */
	public T remove() {
		if (elements==0) {
			return null;
		}
		elements--;
		T temp=front.data;
		front=front.next;
		return temp;
	}
	
	/**
	 * �鿴���ݣ��Ӷ�ͷ�鿴
	 */
	public T peek() {
		return front==null?null:front.data;
	}
	
	/**
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return elements == 0;
	}
	
	public int size() {
		return elements;
		
	}
	
	
	
	
	private class Node{
		T data;
		Node next;
		/**
		  * ����һ���µ�ʵ�� Node. 
		  * <p>Title: </p>
		  * <p>Description: </p>
		  * @param value
		  */
		public Node(T value) {
			this.data=value;
		}
	}
	
	public static void main(String[] args) {
		MyLinkedQueue<Integer> queue=new MyLinkedQueue<>();
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
		System.out.println("��һ��");
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
