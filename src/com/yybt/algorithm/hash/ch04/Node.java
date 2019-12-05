package com.yybt.algorithm.hash.ch04;

public class Node<T> {
	
	/**
	 * Êı¾İÓò
	 */
	private T data;
	
	private int step;
	
	public Node(T v) {
		this.data=v;
		this.step=0;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	

}
