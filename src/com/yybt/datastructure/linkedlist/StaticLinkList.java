/**
 * @Title: StaticLinkList.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: StaticLinkList
  * @Description: 静态列表<这里不考虑扩容问题，毕竟静态链表很少被使用，有兴趣的童鞋可以尝试着扩展，本人比较懒>
  * @author liuzehong
 **/
public class StaticLinkList<T> {
	
	//arr[0]指向第一个有数据的结点
	//arr[length-1]指向第一个空闲结点
	@SuppressWarnings("rawtypes")
	private Element[] arr;
	/**
	 * 默认链表大小
	 */
	private static int DEFAULT_SIZE =20;
	/**
	 * 自定义列表大小
	 */
	private int size;
	
	//表示有效数据的长度
    private int elements;
	
	
	public StaticLinkList() {
		this(DEFAULT_SIZE);
	}
	
    @SuppressWarnings("unchecked")
	public StaticLinkList(int size) {
		this.arr=new Element[size];
		int length=arr.length;
		for (int i = 0; i <length ; i++) {
			Element<T> e=new Element<>(null,i+1);
			arr[i]=e;
		}
		//头部元素指向数组的第一个空闲结点，初始化1
		arr[0].cur=1;
		//尾部元素指向数组的第一个有效结点,初始化-1
		arr[length-1].cur=-1;
		this.size=size;
	}
    /**
	 * 插入一个结点，在头结点后进行插入
	 */
    @SuppressWarnings("unchecked")
	public void insert(T value) {
    	//获取第一个空闲节点下标
    	int index=arr[0].cur;
    	arr[index].data=value;
    	//arr[0]指向下一个空闲节点
    	arr[0].cur=arr[index].cur;
    	  //刚插入一个节点，需要维护arr[size-1]
    	if (elements==0) {
			arr[size-1].cur=index;
			arr[index].cur=0;
		}else {
			int i=arr[size-1].cur;
			while (arr[i].cur!=0) {
				i=arr[i].cur;
			}
			arr[i].cur=index;
			arr[index].cur=0;
			
		}
    	elements++;
    }
    
    /**
      * 指定位置插入
      * insert()
      * @Title: insert
      * @param @param index
      * @param @param value    设定文件
      * @return void    返回类型
      * @throws
     */
    @SuppressWarnings("unchecked")
	public void insert(int index,T value) {
    	//获取第一个空闲节点下标
    	int i=arr[0].cur;
    	arr[i].data=value;
    	//arr[0]指向下一个空闲节点
    	arr[0].cur=arr[i].cur;
    	//找到前驱和后继节点的下标
    	if (index==0) {//只有后继
    		if (elements==0||index==elements) {//后继就是0
    			arr[i].cur=0;
			}else {
				int next=arr[size-1].cur;
				int m=1;  
				while(m++<=index) {
					next=arr[next].cur;
				}
				arr[i].cur=next;
			}
    		arr[size-1].cur=i;
			
		}else {//有前驱有后继
			if (elements<index) {
				System.out.println("超了");
				return;
			}
			if (elements==index) {//后继就是0
				arr[i].cur=0;
			}else {
				int next=arr[size-1].cur;
				int m=1;  
				while(m++<=index) {
					next=arr[next].cur;
				}
				arr[i].cur=next;
			}
			int previous=arr[size-1].cur;
			int m=0;  
			while(m<index-1) {
				previous=arr[previous].cur;
				m++;
			}
			arr[previous].cur=i;
			
		}
    	elements++;
    }
    
    
    /**
	 * 删除一个结点
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index>=size) {
			System.out.println("超了");
			return null;
		}
		T t=(T)arr[++index].data;
		//获取到啊index的前一个索引下标
		int i=arr[size-1].cur;
		while(arr[i].cur!=index) {
			i=arr[i].cur;
		}
		 arr[i].cur=arr[index].cur;
         arr[index].cur = arr[0].cur;
         arr[0].cur = index;
         elements--;
         if (arr[size-1].cur==index) {
        	 arr[size-1].cur=arr[index].cur;
		  }
		return t;
		
	}
    /**
     * 真实位置的元素
      * get()
      * @Title: get
      * @param @param index
      * @param @return    设定文件
      * @return T    返回类型
      * @throws
     */
    @SuppressWarnings("unchecked")
	public T get(int index) {
    	return (T)arr[++index].data;
    }
    /**
     * 链表位置的元素
      * getLinked()
      * @Title: getLinked
      * @param @param index
      * @param @return    设定文件
      * @return T    返回类型
      * @throws
     */
    @SuppressWarnings("unchecked")
	public T getLinked(int index) {
    	int i=0;
    	int m=arr[size-1].cur;  
		while(i<index) {
			m=arr[m].cur;
			i++;
		}
    	return (T)arr[m].data;
    }
    
    
	/**
	 * 显示方法
	 */
	public void display() {
	   int index=arr[size-1].cur;
		String str="[ ";
		int i=index;
		while (arr[i].data!=null) {
			str+=arr[i].data+" ";
			i=arr[i].cur;
		}
		str+="]";
		System.out.println(str);
	}
	
	
	
	
	public static void main(String[] args) {
		StaticLinkList<Integer> list=new StaticLinkList<>();
		for (int i = 0; i < 3; i++) {
			list.insert(5+i);
		}
		list.display();
		list.insert(1,22222);
		list.display();
		list.insert(4,22222);
		list.display();
		//这里获取的是真实位置《物理》的数据《一般不用物理位置的，没必要》
		System.out.println(list.get(3));
		//这里获取的是《虚拟》链表位置的数据
		System.out.println(list.getLinked(3));
		list.insert(99);
		list.display();
		list.insert(12);
		list.display();
		
		
	}

}
