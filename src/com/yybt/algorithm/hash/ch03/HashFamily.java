package com.yybt.algorithm.hash.ch03;

public interface HashFamily<E> {
	
	/**
	 * 返回集合中散列函数的个数
	 * @return
	 */
	
    int getNumOfFunctions();
    
    /**
     * 获取到新的散列函数
     */
    void generateNewFunctions();
    
    /**
     * 根据which来选择散列函数，并返回hash值返回一个hash
     * @param e
     * @param which
     * @return
     */
    int hash(E e, int which);
}
