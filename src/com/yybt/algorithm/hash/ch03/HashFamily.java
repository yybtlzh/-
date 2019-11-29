package com.yybt.algorithm.hash.ch03;

public interface HashFamily<E> {
	
	/**
	 * ���ؼ�����ɢ�к����ĸ���
	 * @return
	 */
	
    int getNumOfFunctions();
    
    /**
     * ��ȡ���µ�ɢ�к���
     */
    void generateNewFunctions();
    
    /**
     * ����which��ѡ��ɢ�к�����������hashֵ����һ��hash
     * @param e
     * @param which
     * @return
     */
    int hash(E e, int which);
}
