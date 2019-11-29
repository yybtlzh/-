package com.yybt.algorithm.hash.ch03;

public class MyHashFamily<E> implements HashFamily<E>{

	@Override
	public int getNumOfFunctions() {
		
		return 5;
	}

	@Override
	public void generateNewFunctions() {
	}

	@Override
	public int hash(E e, int which) {
        int hashVal = 0;
        java.lang.String s= e.toString();
        switch (which){
            case 0:{
                for (int i = 0; i <s.length(); i ++){
                    hashVal += s.toString().charAt(i);
                }
                break;
            }
            case 1:
                for (int i = 0; i < s.length(); i ++){
                    hashVal = 37 * hashVal + s.charAt(i);
                }
                break;
        }
        return hashVal;
    }
	
}
