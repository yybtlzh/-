package com.yybt.algorithm.hash.ch04;

public class JumpHashTable<T> {
	/**
	 * 负载因子：0.4
	 */
	private static final float MAX_LOAD = 0.4f;
	
	/**
	 * 最大探测深度
	 */
	private  int MAX_DIST=4;
	
	/**
	 * 定义默认表的大小
	 */
	private  final static int DEFAULT_TABLE_SIZE = 101;
	
	/**
	 * 定义当前表
	 */
	private Node<T>[] array;
	/**
	 * 有效元素个数
	 */
	private int eleSize;
	
	
	public JumpHashTable() {
		this(DEFAULT_TABLE_SIZE);
	} 
	
	
	
	@SuppressWarnings("unchecked")
	public JumpHashTable(int size) {
		array = new Node[size];
		//初始化array
		for(int i = 0; i < array.length; i++){
			array[i] = new Node<T>(null);
		}
		eleSize = 0;
	}
	
	/**
	 * 返回有效数据大小
	 * @return
	 */
	public int size() {
		return this.eleSize;
	}
	/**
	 * 添加
	 * @param value
	 */
	public void add(T value) {
		insert(value);
	}
	
    /**
     * 删除
     * @param t
     */
	public void remove(T t) {
		delete(t);
	}
	

	/**
	 * 清空散列
	 */
	public void clear() {
		doClear();
	}
	
	/**
	 * 显示数据
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
	 * 查询是否包含元素t
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return getIndex(t) != -1;
	}
	
	
	private void insert(T value){
		//1.如果元素数目达到装载极限
		if(eleSize >= array.length * MAX_LOAD){
			//扩容
			expand();
		}
		//2.不断循环直至插入成功
		insertHelper(value);
	}
	
	/**
	 * 扩容
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
		 * 赋值到新的array
		 */
		for(Node<T> node : oldArr){
			if(node.getData()!= null){
				insert(node.getData());
			}
		}
	}
	
	/**
	 * 下一个素数
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
	 * 是不是素数
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
		// 记录循环的最大次数
		while(true){
			//获取散列位置
			int pos = hash(value);
			//保存最初散列值
			int temp = pos;
			//循环以得到空位
			while(array[pos].getData()!= null){
				pos++;
			}
			//如果空位在距离内，直接插入并修改距离标志
			if(pos <= temp + MAX_DIST - 1){
				array[pos].setData(value);
				array[temp].setStep(array[temp].getStep() 
						+ (1 << (MAX_DIST+temp - 1 - pos)));//修改距离标志
                eleSize++;
				return;
			}
			//如果不在距离内，调整位置直至符合距离要求
			while(true) {
                //设置标志判断是否调整位置成功，便于二次循环的跳转
				boolean isNotDist = false;
				//散列位置从最远处开始
				for(int i = MAX_DIST - 1; i > 0; i--){
					//距离标志从最高位开始
					for(int j = MAX_DIST - 1; j > MAX_DIST - 1 - i; j--){
						//如果距离标志位为1，则可以调整位置
						if((array[pos - i].getStep() >> j) % 2 == 1){
							Node<T> node = array[pos - i + MAX_DIST - 1 - j];//获得需要被调整的散列位置
                            array[pos].setData(node.getData());
							node.setStep(node.getStep() - (1 << j) + 1);//修改被调整值的距离标志
                            pos = pos - i + MAX_DIST - 1 - j;
							//如果在距离内，直接插入并修改距离标志
							if(pos <= temp + MAX_DIST-1){
								array[pos].setData(value);
								array[temp].setStep(array[temp].getStep() + (1 << (MAX_DIST - 1 - pos + temp)));//修改距离标志
                                eleSize++;
								return;
							}
							//如果不在距离标志内
							else{
								isNotDist = true;
								break;
							}
						}
					}
					if(isNotDist)
						break;
				}
				//如果无法调整位置
				if(!isNotDist)
					break;
			}
			//扩容，重新插入
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
		//初始化array
		for(int i = 0; i < array.length; i++){
			array[i] = new Node<T>(null);
		}
		eleSize = 0;
	}
	
	/**
	 * 查询元素的位置，若找到元素，则返回其当前位置，否则返回-1
	 * @param t
	 * @return
	 */
	private int getIndex(T t) {
		// 遍历散列函数集合，因为不确定元素所用的散列函数为哪个
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
	 * 自定义哈希
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
