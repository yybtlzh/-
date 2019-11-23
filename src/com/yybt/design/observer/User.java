/**
 * @Title: User.java
 * @Package com.lzh.test14
 */
package com.yybt.design.observer;

/**
 * @ClassName: User
 * @Description: 具体观察者
 **/
public class User implements Observer {

	private String name;
	
	private String message;

	public String getMessage() {
		System.out.println(name + " 收到推送消息： " + message);
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
