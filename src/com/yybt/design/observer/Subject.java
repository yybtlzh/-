package com.yybt.design.observer;

/**
 * 
  * @ClassName: Subject
  * @Description:抽象被观察者接口
  * @author liuzehong
 *
 */
public interface Subject {
	/** * 注册观察者 */
	public  void registerObserver(Observer observer);

	/** * 移除观察者 */
	public void removeObserver(Observer observer);

	/** * 通知观察者 */
	public void notifyObservers();
}