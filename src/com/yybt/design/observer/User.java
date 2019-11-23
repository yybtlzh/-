/**
 * @Title: User.java
 * @Package com.lzh.test14
 */
package com.yybt.design.observer;

/**
 * @ClassName: User
 * @Description: ����۲���
 **/
public class User implements Observer {

	private String name;
	
	private String message;

	public String getMessage() {
		System.out.println(name + " �յ�������Ϣ�� " + message);
		return message;
	}

	public User(String name) {
		this.name = name;
	}

	@Override
	public void update(String message) {
		this.message = message;
		getMessage();
	}

}
