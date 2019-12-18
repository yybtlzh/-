package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: MyLinkedList
  * @Description: 
  * @author liuzehong
  *  比如 1-》2-》3—》4  访问3，链表将变为1-》2-》4—》3 访问1 ，链表将变为2-》4—》3-》1
 **/
public class MyLinkedList <T extends Comparable<T>>{

	//头结点
	protected Node<T> first;
	//尾结点
	private Node<T> last;
	
	public MyLinkedList() {
		first = null;
		last = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			last = node;
		} else {
			first.previous = node;
		}
		node.next = first;
		first = node;
	}
	
	/**
	 * 插入一个结点，从尾结点进行插入
	 */
	public void insertLast(T value) {
		Node<T> node = new Node<T>(value);
		if(isEmpty()) {
			first = node;
		} else {
			last.next = node;
			node.previous = last;
		}
		last = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除，同时也是最last访问元素。
	 */
	public Node<T> deleteFirst() {
		Node<T> tmp = first;
		if(!first.hasNext()) {
			last = null;
		} else {
			first.next.previous = null;
		}
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * 删除结点，从尾部进行删除，同时也是最近访问元素。
	 */
	public Node<T>deleteLast() {
		if(!first.hasNext()) {
			first = null;
		} else {
			last.previous.next = null;
		}
		last = last.previous;
		return last;
	}
	
	/**
	 * 显示方法
	 */
	public void display() {
		Node<T> current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 查找方法
	 */
	public Node<T> find(T value) {
		Node<T> current = first;
		while(current.data .compareTo(value)!=0) {
			if(!current.hasNext()) {
				return null;
			}
			current = current.next;
		}
		if (current==last) {
		}else if(current==first){
			first=current.next;
			current.next.previous=first;
			last.next=current;
			last=current;
			last.next=null;
		}else  {
			current.previous.next=current.next;
			current.next.previous=current.previous;
			last.next=current;
			last=current;
			last.next=null;
		}
		return current;
	}
	
	/**
	 * 删除方法，根据数据域来进行删除
	 */
	public Node<T> delete(T value) {
		Node<T> current = first;
		while(current.data.compareTo(value)!=0) {
			if(current.next == null) {
				return null;
			}
			current = current.next;
		}
		
		if(current == first) {
			first = first.next;
		} else {
			current.previous.next = current.next;
		}
		return current;
		
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return (first == null);
	}

	
	public static void main(String[] args) {
		MyLinkedList<String> linkList = new MyLinkedList<>();
		linkList.insertFirst("张三");
		linkList.insertFirst("李四");
		linkList.insertFirst("王五");
		linkList.insertFirst("赵六");
		linkList.insertFirst("田七");
		linkList.display();
		System.out.println("last：    "+linkList.last.data);
		linkList.find("田七");
		//linkList.display();
		System.out.println("last：    "+linkList.last.data);
		linkList.find("李四");
		System.out.println("last：    "+linkList.last.data);
		//linkList.display();
		linkList.find("王五");
		System.out.println("last：    "+linkList.last.data);
		//linkList.display();
	}
	
	
}
