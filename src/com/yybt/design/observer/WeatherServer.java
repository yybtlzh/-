/**
 * @Title: WeatherServer.java
 * @Package com.lzh.test14
 */
package com.yybt.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName: WeatherServer
  * @Description: 被观察者
 **/
public class WeatherServer implements Subject {
    //观察者集合
	private List<Observer> observers;
	
	private String message;
	
	
	public void setMessage(String message) {
		this.message = message;
		//信息改变，通知观察者
		notifyObservers();
	}

	public WeatherServer() {
		this.observers = new ArrayList<Observer>();
    }

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer observer:observers) {
			observer.update(message);
		}
	}

}
