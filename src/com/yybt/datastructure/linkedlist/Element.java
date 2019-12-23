/**
 * @Title: Element.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: Element
  * @Description: TODO
  * @author liuzehong
  * @date 2019��12��20�� ����4:17:55
 **/
public class Element<T>{
	/**
	 * ������
	 */
    Object data;
    /**
     * ָ��
     */
    int cur;
	
    Element(T data, int cur) {
		this.data = data;
		this.cur = cur;
	}
	@Override
	public String toString() {
		return "[data=" + data + ", cur=" + cur + "]";
	}
}