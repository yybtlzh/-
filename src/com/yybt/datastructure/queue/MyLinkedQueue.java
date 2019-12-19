/**
 * @Title: MyLinkedQueue.java
 * @Package com.yybt.datastructure.queue
 */
package com.yybt.datastructure.queue;

/**
  * @ClassName: MyLinkedQueue
  * @Description: 链式存储实现
  * @author liuzehong
 **/
public class MyLinkedQueue<T> {
	
	//有效数据的大小
    private int elements;
	//队头
	private Node front;
	//队尾
	private Node end;
	
	/**
	 * 默认构造方法
	 */
	public MyLinkedQueue() {
		front=null;
		end=null;
		elements=0;
	}
	
	/**
	 * 添加数据,从队尾插入
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
	 * 删除数据，从队头删除
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
	 * 查看数据，从队头查看
	 */
	public T peek() {
		return front==null?null:front.data;
	}
	
	/**
	 * 判断是否为空
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
		  * 创建一个新的实例 Node. 
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
