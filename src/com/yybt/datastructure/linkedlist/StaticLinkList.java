/**
 * @Title: StaticLinkList.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: StaticLinkList
  * @Description: ��̬�б�<���ﲻ�����������⣬�Ͼ���̬������ٱ�ʹ�ã�����Ȥ��ͯЬ���Գ�������չ�����˱Ƚ���>
  * @author liuzehong
 **/
public class StaticLinkList<T> {
	
	//arr[0]ָ���һ�������ݵĽ��
	//arr[length-1]ָ���һ�����н��
	@SuppressWarnings("rawtypes")
	private Element[] arr;
	/**
	 * Ĭ�������С
	 */
	private static int DEFAULT_SIZE =20;
	/**
	 * �Զ����б��С
	 */
	private int size;
	
	//��ʾ��Ч���ݵĳ���
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
		//ͷ��Ԫ��ָ������ĵ�һ�����н�㣬��ʼ��1
		arr[0].cur=1;
		//β��Ԫ��ָ������ĵ�һ����Ч���,��ʼ��-1
		arr[length-1].cur=-1;
		this.size=size;
	}
    /**
	 * ����һ����㣬��ͷ������в���
	 */
    @SuppressWarnings("unchecked")
	public void insert(T value) {
    	//��ȡ��һ�����нڵ��±�
    	int index=arr[0].cur;
    	arr[index].data=value;
    	//arr[0]ָ����һ�����нڵ�
    	arr[0].cur=arr[index].cur;
    	  //�ղ���һ���ڵ㣬��Ҫά��arr[size-1]
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
      * ָ��λ�ò���
      * insert()
      * @Title: insert
      * @param @param index
      * @param @param value    �趨�ļ�
      * @return void    ��������
      * @throws
     */
    @SuppressWarnings("unchecked")
	public void insert(int index,T value) {
    	//��ȡ��һ�����нڵ��±�
    	int i=arr[0].cur;
    	arr[i].data=value;
    	//arr[0]ָ����һ�����нڵ�
    	arr[0].cur=arr[i].cur;
    	//�ҵ�ǰ���ͺ�̽ڵ���±�
    	if (index==0) {//ֻ�к��
    		if (elements==0||index==elements) {//��̾���0
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
			
		}else {//��ǰ���к��
			if (elements<index) {
				System.out.println("����");
				return;
			}
			if (elements==index) {//��̾���0
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
	 * ɾ��һ�����
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if (index>=size) {
			System.out.println("����");
			return null;
		}
		T t=(T)arr[++index].data;
		//��ȡ����index��ǰһ�������±�
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
     * ��ʵλ�õ�Ԫ��
      * get()
      * @Title: get
      * @param @param index
      * @param @return    �趨�ļ�
      * @return T    ��������
      * @throws
     */
    @SuppressWarnings("unchecked")
	public T get(int index) {
    	return (T)arr[++index].data;
    }
    /**
     * ����λ�õ�Ԫ��
      * getLinked()
      * @Title: getLinked
      * @param @param index
      * @param @return    �趨�ļ�
      * @return T    ��������
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
	 * ��ʾ����
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
		//�����ȡ������ʵλ�á����������ݡ�һ�㲻������λ�õģ�û��Ҫ��
		System.out.println(list.get(3));
		//�����ȡ���ǡ����⡷����λ�õ�����
		System.out.println(list.getLinked(3));
		list.insert(99);
		list.display();
		list.insert(12);
		list.display();
		
		
	}

}
