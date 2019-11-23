/**
 * @Title: WeatherServer.java
 * @Package com.lzh.test14
 */
package com.yybt.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName: WeatherServer
  * @Description: ���۲���
 **/
public class WeatherServer implements Subject {
    //�۲��߼���
	private List<Observer> observers;
	
	private String message;
	
	
	public void setMessage(String message) {
		this.message = message;
		//��Ϣ�ı䣬֪ͨ�۲���
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
