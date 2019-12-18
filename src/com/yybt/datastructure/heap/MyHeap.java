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
 * //��Ԫ�صĸ��׽ڵ������±��Ǳ����1/2(ֻȡ��������) ����ڵ������ֵΪindex����ô�� �ڵ�����ӽڵ��� 2*index+1�� �ڵ�����ӽڵ���
 * 2*index+2�� �ڵ�ĸ��ڵ��� ��index-1��/2��
 * ����һ����ȫ����������������У����и��ڵ㶼������ڵ������ӽڵ�Ķѽд���ѣ����и��ڵ㶼����С�ڵ������ӽڵ�Ķѽ�С���ѡ�
 * ����Ȼ��һ����������ͨ�������һ�������У����ڵ�ͺ��ӽڵ�ĸ��ӹ�ϵͨ�������±���ȷ��������ͼ��С���Ѽ��洢�������飺
 * 
 * //�ѷ�Ϊ�������ͣ�����ѡ�С���� //����˼�壬���Ǳ�֤���ڵ����������������/С�����Ҿ�����С�Ľڵ����Ϸ�
 * �ϸ� shift_up�� �³� shift_down ���� push ���� pop ȡ�� top ������ heap_sort
 */

/**
 * @ClassName: MyHeap
 * @Description:��
 * 2019/12/12�ģ����÷������飬���ٵ�һΪint����
 **/
public class MyHeap<T extends Comparable<T>> {
	
	private Object[] arr;
	
	private final static int DEFAULT_SIZE = 20;
	// ��Ч���ݵĴ�С
	private int elements;

	public MyHeap() {
		this(DEFAULT_SIZE);
	}

	/**
	 * �������Ĺ��췽��������Ϊ����Ĵ�С
	 */
	public MyHeap(int maxsize) {
		this.arr = new Object[maxsize];   // ������������
		this.elements = 0;
	}

	/**
	 * �Ѵ�С
	 */
	public int size() {
		return elements;
	}

	/**
	 * ������ڵ��±�
	 * 
	 * @param i
	 * @return
	 */
	private int left(int i) {
		return ((i + 1) << 1) - 1;
	}

	/**
	 * �����ҽڵ��±�
	 * 
	 * @param i
	 * @return
	 */
	private int right(int i) {
		return (i + 1) << 1;
	}

	/**
	 * ���ظ��ڵ��±�
	 * 
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return (i - 1) >> 1;
	}

	/**
	 * ���� �����±�Ϊi ,j����Ԫ��
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
	 * �ϸ�
	 * 
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void shift_up(int index) {
		T t1 ,t2;
		// ���ڵ㲻�����ϸ�����
		if (index > 0) {
			int parent = parent(index);
			t1=(T) arr[parent];
			t2=(T) arr[index];
			// ������׽ڵ��index����ֵС���ͽ������ߵ���ֵ
			if (t1.compareTo(t2) <0 ) {
				// �������ӽڵ�
				exChange(parent, index);
				// �ݹ����
				shift_up(parent);
			}
		}
	}

	/**
	 * �³�
	 * 
	 * @param index
	 */
	@SuppressWarnings("unchecked")
	public void shift_down(int index) {
		T t1;
		T t2;
		// �³����ȱ�����ҵ�����ӽڵ�
		int n = index << 1;
		// û�к��ӽڵ�
		if (n + 3 > elements)
			return;
		// ����child�����洢����ӽڵ��±�
		int child = -1;
		// ����������
		if (n + 2 < elements) {
			child = n + 1;
		   t1=	(T) arr[n + 1];
		   t2=	(T) arr[n + 2];
			if( t1 .compareTo(t2)< 0) {
				++child;
			}
		}
		// ������
		else if ((n + 2) == elements) {
			child = n + 1;
		}
		// �Ƚ�
		t1=(T) arr[index];
		t2=(T) arr[child];
		if (t1.compareTo(t2) < 0) {
			exChange(index, child);
			// �ݹ����
			shift_down(child);
		}
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public Object[] insert(T value) {
		if (elements<arr.length) {
			//����һ���ڵ�
			arr[elements++] = value;
			shift_up(elements - 1);
		}
		
		return arr;
	}

	/*
	 * ���Ԫ����Ҫ�����������裺 1.������Ч������1�� 
	 * 2.��Ҫ�����ֵ��ֵ���ѵ� 
	 * 3.Ϊ�˼���ά�ֶ����ԣ���Ҫ��������ϸ�����
	 * ɾ������
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
	 * �Զѽ�������
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
	 *            ���±�
	 */
	@SuppressWarnings("unchecked")
	private void heapify(int i, int size) {
		T t1 ,t2;
		// �������Һ��ӽڵ�
		int leftChild = left(i);
		int rightChild = right(i);
		// ��ʱ������������¼��Ҫ������Ԫ�ص������±�
		int temp = i;
		// ������С,��Ҫ������Ԫ�ص������±�Ϊ�����±�
		t1=(T) arr[i];
		t2=(T) arr[leftChild];
		if (leftChild < size &&t1.compareTo(t2) <0 ) {
			temp = leftChild;
		}
		// ���Һ���С,��Ҫ������Ԫ�ص������±�Ϊ�Һ����±�
		// ���ǵ��ϱ߿����Ѿ���Ҫ����һ�� ������Աȵ�Ӧ����heap[temp]���Һ��ӣ�����Ӧ���� heap[i]
		t1=(T) arr[temp];
		t2=(T) arr[rightChild];
		if (rightChild < size && t1.compareTo(t2) <0 ) {
			temp = rightChild;
		}
		if (temp == i) {
			return;
		}
		// �������ӽڵ�
		exChange(i, temp);
		// �ݹ����
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