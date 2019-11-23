package com.yybt.design.observer;

/**
 * 
  * @ClassName: Subject
  * @Description:���󱻹۲��߽ӿ�
  * @author liuzehong
 *
 */
public interface Subject {
	/** * ע��۲��� */
	public  void registerObserver(Observer observer);

	/** * �Ƴ��۲��� */
	public void removeObserver(Observer observer);

	/** * ֪ͨ�۲��� */
	public void notifyObservers();
}