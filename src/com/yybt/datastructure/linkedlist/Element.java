/**
 * @Title: Element.java
 * @Package com.yybt.datastructure.linkedlist
 */
package com.yybt.datastructure.linkedlist;

/**
  * @ClassName: Element
  * @Description: TODO
  * @author liuzehong
  * @date 2019年12月20日 下午4:17:55
 **/
public class Element<T>{
	/**
	 * 数据域
	 */
    Object data;
    /**
     * 指针
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