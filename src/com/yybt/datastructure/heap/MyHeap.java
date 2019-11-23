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
 * 
 * �ϸ� shift_up�� �³� shift_down ���� push ���� pop ȡ�� top ������ heap_sort
 */

/**
 * @ClassName: MyHeap
 * @Description:��
 **/
public class MyHeap {
	
	private int[] arr;
	
	private static int default_size = 20;
	// ��Ч���ݵĴ�С
	private int elements;

	public MyHeap() {
		/*this.arr = new int[default_size];
		this.elements = 0;
		*/
		this(default_size);
	}

	/**
	 * �������Ĺ��췽��������Ϊ����Ĵ�С
	 */
	public MyHeap(int maxsize) {
		this.arr = new int[maxsize];
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
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * �ϸ�
	 * 
	 * @param index
	 */
	public void shift_up(int index) {
		// ���ڵ㲻�����ϸ�����
		if (index > 0) {
			int parent = parent(index);
			// ������׽ڵ��index����ֵС���ͽ������ߵ���ֵ
			if (arr[parent] < arr[index]) {
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
	public void shift_down(int index) {
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
			if (arr[n + 1] < arr[n + 2]) {
				++child;
			}
		}
		// ������
		else if ((n + 2) == elements) {
			child = n + 1;
		}
		// �Ƚ�
		if (arr[index] < arr[child]) {
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
	public int[] insert(int value) {
		// ����һ���ڵ�
		arr[elements++] = value;
		shift_up(elements - 1);
		return arr;
	}

	/*
	 * ���Ԫ����Ҫ�����������裺 1.������Ч������1�� 2.��Ҫ�����ֵ��ֵ���ѵ� 3.Ϊ�˼���ά�ֶ����ԣ���Ҫ��������ϸ������� ɾ����
	 */
	/**
	 * ɾ������
	 * 
	 * @return
	 */
	public int[] delete(int index) {
		if (index + 1 > elements)
			return arr;
		exChange(index, elements - 1);
		shift_down(index);
		arr[elements - 1] = 0;
		return arr;
	}

	/**
	 * �Զѽ�������
	 * 
	 * @return
	 */
	public int[] sort() {
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
	private void heapify(int i, int size) {
		// �������Һ��ӽڵ�
		int leftChild = left(i);
		int rightChild = right(i);
		// ��ʱ������������¼��Ҫ������Ԫ�ص������±�
		int temp = i;
		// ������С,��Ҫ������Ԫ�ص������±�Ϊ�����±�
		if (leftChild < size && arr[i] < arr[leftChild]) {
			temp = leftChild;
		}
		// ���Һ���С,��Ҫ������Ԫ�ص������±�Ϊ�Һ����±�
		// ���ǵ��ϱ߿����Ѿ���Ҫ����һ�� ������Աȵ�Ӧ����heap[temp]���Һ��ӣ�����Ӧ���� heap[i]
		if (rightChild < size && arr[temp] < arr[rightChild]) {
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
}