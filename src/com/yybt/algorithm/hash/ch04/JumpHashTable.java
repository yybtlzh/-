package com.yybt.algorithm.hash.ch04;

public class JumpHashTable<T> {
	/**
	 * �������ӣ�0.4
	 */
	private static final float MAX_LOAD = 0.4f;
	
	/**
	 * ���̽�����
	 */
	private  int MAX_DIST=4;
	
	/**
	 * ����Ĭ�ϱ�Ĵ�С
	 */
	private  final static int DEFAULT_TABLE_SIZE = 101;
	
	/**
	 * ���嵱ǰ��
	 */
	private Node<T>[] array;
	/**
	 * ��ЧԪ�ظ���
	 */
	private int eleSize;
	
	
	public JumpHashTable() {
		this(DEFAULT_TABLE_SIZE);
	} 
	
	
	
	@SuppressWarnings("unchecked")
	public JumpHashTable(int size) {
		array = new Node[size];
		//��ʼ��array
		for(int i = 0; i < array.length; i++){
			array[i] = new Node<T>(null);
		}
		eleSize = 0;
	}
	
	/**
	 * ������Ч���ݴ�С
	 * @return
	 */
	public int size() {
		return this.eleSize;
	}
	/**
	 * ���
	 * @param value
	 */
	public void add(T value) {
		insert(value);
	}
	
    /**
     * ɾ��
     * @param t
     */
	public void remove(T t) {
		delete(t);
	}
	

	/**
	 * ���ɢ��
	 */
	public void clear() {
		doClear();
	}
	
	/**
	 * ��ʾ����
	 */
	public void display(){
		T t = null;
		System.out.print("[");
		for(Node<T> note : array){
			t=note.getData();
			if(note.getData() != null){
				System.out.print(t+" ");
			}
		}
		System.out.println("]");
	} 
	
	/**
	 * ��ѯ�Ƿ����Ԫ��t
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return getIndex(t) != -1;
	}
	
	
	private void insert(T value){
		//1.���Ԫ����Ŀ�ﵽװ�ؼ���
		if(eleSize >= array.length * MAX_LOAD){
			//����
			expand();
		}
		//2.����ѭ��ֱ������ɹ�
		insertHelper(value);
	}
	
	/**
	 * ����
	 */
	private void expand() {
		rehash((int) (array.length / MAX_LOAD));
	}
	
	@SuppressWarnings("unchecked")
	private void rehash(int newLength) {
		Node<T>[] oldArr = this.array;
		array = new Node[nextPrime(newLength)];
		for(int i = 0; i < array.length; i++){
			array[i] = new Node<T>(null);
		}
		eleSize = 0;
		/**
		 * ��ֵ���µ�array
		 */
		for(Node<T> node : oldArr){
			if(node.getData()!= null){
				insert(node.getData());
			}
		}
	}
	
	/**
	 * ��һ������
	 * @param n
	 * @return
	 */

	private int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		while (!isPrime(n))
			n += 2;
		return n;
	}


	/**
	 * �ǲ�������
	 * @param n
	 * @return
	 */
	private boolean isPrime(int n) {

		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;

	}

	
	private void insertHelper(T value){
		// ��¼ѭ����������
		while(true){
			//��ȡɢ��λ��
			int pos = hash(value);
			//�������ɢ��ֵ
			int temp = pos;
			//ѭ���Եõ���λ
			while(array[pos].getData()!= null){
				pos++;
			}
			//�����λ�ھ����ڣ�ֱ�Ӳ��벢�޸ľ����־
			if(pos <= temp + MAX_DIST - 1){
				array[pos].setData(value);
				array[temp].setStep(array[temp].getStep() 
						+ (1 << (MAX_DIST+temp - 1 - pos)));//�޸ľ����־
                eleSize++;
				return;
			}
			//������ھ����ڣ�����λ��ֱ�����Ͼ���Ҫ��
			while(true) {
                //���ñ�־�ж��Ƿ����λ�óɹ������ڶ���ѭ������ת
				boolean isNotDist = false;
				//ɢ��λ�ô���Զ����ʼ
				for(int i = MAX_DIST - 1; i > 0; i--){
					//�����־�����λ��ʼ
					for(int j = MAX_DIST - 1; j > MAX_DIST - 1 - i; j--){
						//��������־λΪ1������Ե���λ��
						if((array[pos - i].getStep() >> j) % 2 == 1){
							Node<T> node = array[pos - i + MAX_DIST - 1 - j];//�����Ҫ��������ɢ��λ��
                            array[pos].setData(node.getData());
							node.setStep(node.getStep() - (1 << j) + 1);//�޸ı�����ֵ�ľ����־
                            pos = pos - i + MAX_DIST - 1 - j;
							//����ھ����ڣ�ֱ�Ӳ��벢�޸ľ����־
							if(pos <= temp + MAX_DIST-1){
								array[pos].setData(value);
								array[temp].setStep(array[temp].getStep() + (1 << (MAX_DIST - 1 - pos + temp)));//�޸ľ����־
                                eleSize++;
								return;
							}
							//������ھ����־��
							else{
								isNotDist = true;
								break;
							}
						}
					}
					if(isNotDist)
						break;
				}
				//����޷�����λ��
				if(!isNotDist)
					break;
			}
			//���ݣ����²���
			expand();
		}

	}
	
	private void delete(T value){
		int index = getIndex((value));
		int hash = hash(value);
		if(index != -1){
			array[index].setData(null);
			array[hash].setStep(array[hash].getStep() - 
					(1 << (MAX_DIST+hash- 1 - index)));
			eleSize--;
		}

	}

	
	@SuppressWarnings("unchecked")
	private void doClear() {
		array = new Node[DEFAULT_TABLE_SIZE];
		//��ʼ��array
		for(int i = 0; i < array.length; i++){
			array[i] = new Node<T>(null);
		}
		eleSize = 0;
	}
	
	/**
	 * ��ѯԪ�ص�λ�ã����ҵ�Ԫ�أ��򷵻��䵱ǰλ�ã����򷵻�-1
	 * @param t
	 * @return
	 */
	private int getIndex(T t) {
		// ����ɢ�к������ϣ���Ϊ��ȷ��Ԫ�����õ�ɢ�к���Ϊ�ĸ�
		int hash = hash(t);
		for(int i = 0; i < MAX_DIST; i++) {
			int step = array[hash].getStep();
			if((step >> i) % 2 == 1){
				if(array[hash + MAX_DIST - 1 - i].getData().equals(t)){
					return hash + MAX_DIST - 1 - i;
				}
			}
		}
		return -1;
	}
	/**
	 * �Զ����ϣ
	 * @param value
	 * @return
	 */
	private int hash(T t){
		int hash = t.hashCode();
		hash %= array.length;
		if(hash < 0)
			hash +=array.length;
		return hash;
	}
	
	
	
	public static void main(String[] args) {
		JumpHashTable t=new JumpHashTable();
		for (int i = 0; i < 100; i++) {
			t.add("zhangs"+i);
			t.delete("zhangs"+4);
		}
		t.display();
	}

}
