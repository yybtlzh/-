package com.yybt.datastructure.heap;

/*
 * //**
 *  
 *       O 
 *       1 
 *      / \ 
 *     0   O 
 *     2    3 
 *    / \  / \ 
 *   O   O O  O 
 *   4   5 6   7 
 *  / \ 
 *  O O 
 *  8 9
 */

/*
 * //，元素的父亲节点数组下标是本身的1/2(只取整数部分) 假设节点的索引值为index，那么： 节点的左子节点是 2*index+1， 节点的右子节点是
 * 2*index+2， 节点的父节点是 （index-1）/2。
 * 堆是一颗完全二叉树，在这棵树中，所有父节点都满足大于等于其子节点的堆叫大根堆，所有父节点都满足小于等于其子节点的堆叫小根堆。
 * 堆虽然是一颗树，但是通常存放在一个数组中，父节点和孩子节点的父子关系通过数组下标来确定。如下图的小根堆及存储它的数组：
 * 
 * //堆分为两种类型：大根堆、小根堆 //顾名思义，就是保证根节点是所有数据中最大/小，并且尽力让小的节点在上方
 * 上浮 shift_up； 下沉 shift_down 插入 push 弹出 pop 取顶 top 堆排序 heap_sort
 */

/**
 * @ClassName: MyHeap
 * @Description:堆
 * 2019/12/12改：改用泛型数组，不再单一为int类型
 **/
public class MyHeap<T extends Comparable<T>> {
	
	private Object[] arr;
	
	private final static int DEFAULT_SIZE = 20;
	// 有效数据的大小
	private int elements;

	public MyHeap() {
		this(DEFAULT_SIZE);
	}

	/**
	 * 带参数的构造方法，参数为数组的大小
	 */
	public MyHeap(int maxsize) {
		this.arr = new Object[maxsize];   // 创建泛型数组
		this.elements = 0;
	}

	/**
	 * 堆大小
	 */
	public int size() {
		return elements;
	}

	/**
	 * 返回左节点下标
	 * 
	 * @param i
	 * @return
	 */
	private int left(int i) {
		return ((i + 1) << 1) - 1;
	}

	/**
	 * 返回右节点下标
	 * 
	 * @param i
	 * @return
	 */
	private int right(int i) {
		return (i + 1) << 1;
	}

	/**
	 * 返回父节点下标
	 * 
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return (i - 1) >> 1;
	}

	/**
	 * 交换 数组下标为i ,j的俩元素
	 * 
	 * @param i
	 * @param j
	 */
	private void exChange(int i, int j) {
		@SuppressWarnings("unchecked")
		T tmp = (T) arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 上浮
	 * 
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void shift_up(int index) {
		T t1 ,t2;
		// 根节点不存在上浮操作
		if (index > 0) {
			int parent = parent(index);
			t1=(T) arr[parent];
			t2=(T) arr[index];
			// 如果父亲节点比index的数值小，就交换二者的数值
			if (t1.compareTo(t2) <0 ) {
				// 交换父子节点
				exChange(parent, index);
				// 递归调用
				shift_up(parent);
			}
		}
	}

	/**
	 * 下沉
	 * 
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void shift_down(int index) {
		T t1;
		T t2;
		// 下沉首先必须得找到最大子节点
		int n = index << 1;
		// 没有孩子节点
		if (n + 3 > elements)
			return;
		// 定义child用来存储最大子节点下标
		int child = -1;
		// 有两个孩子
		if (n + 2 < elements) {
			child = n + 1;
		   t1=	(T) arr[n + 1];
		   t2=	(T) arr[n + 2];
			if( t1 .compareTo(t2)< 0) {
				++child;
			}
		}
		// 有左孩子
		else if ((n + 2) == elements) {
			child = n + 1;
		}
		// 比较
		t1=(T) arr[index];
		t2=(T) arr[child];
		if (t1.compareTo(t2) < 0) {
			exChange(index, child);
			// 递归调用
			shift_down(child);
		}
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public Object[] insert(T value) {
		if (elements<arr.length) {
			//新增一个节点
			arr[elements++] = value;
			shift_up(elements - 1);
		}
		
		return arr;
	}

	/*
	 * 添加元素需要以下三个步骤： 1.堆中有效数增加1个 
	 * 2.将要插入的值赋值给堆底 
	 * 3.为了继续维持堆属性，需要对其进行上浮操作
	 * 删除数据
	 * 
	 * @return
	 */
	public Object[] delete(int index) {
		if (index + 1 > elements)
			return arr;
		exChange(index, elements - 1);
		shift_down(index);
		arr[--elements] = null;
		return arr;
	}

	/**
	 * 对堆进行排序
	 * 
	 * @return
	 */
	public Object[] sort() {
		for (int i = elements - 1; i > 0; i--) {
			exChange(0, i);
			heapify(0, i);
		}
		return arr;
	}

	/**
	 * 
	 * @param i
	 *            堆下标
	 */
	@SuppressWarnings("unchecked")
	private void heapify(int i, int size) {
		T t1 ,t2;
		// 或者左右孩子节点
		int leftChild = left(i);
		int rightChild = right(i);
		// 临时变量，用来记录需要交换的元素的数组下标
		int temp = i;
		// 比左孩子小,需要交换的元素的数组下标为左孩子下标
		t1=(T) arr[i];
		t2=(T) arr[leftChild];
		if (leftChild < size &&t1.compareTo(t2) <0 ) {
			temp = leftChild;
		}
		// 比右孩子小,需要交换的元素的数组下标为右孩子下标
		// 考虑到上边可能已经需要交换一次 ，这里对比的应该是heap[temp]和右孩子，而不应该是 heap[i]
		t1=(T) arr[temp];
		t2=(T) arr[rightChild];
		if (rightChild < size && t1.compareTo(t2) <0 ) {
			temp = rightChild;
		}
		if (temp == i) {
			return;
		}
		// 交换父子节点
		exChange(i, temp);
		// 递归调用
		heapify(temp, size);
	}
	
	@SuppressWarnings("unchecked")
	public  T  get(int index) {
		if (index<0||index>=arr.length) {
			return null;
		}
		return (T) arr[index];
	}
	
	public  void  show() {
		for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
		}
	}
}