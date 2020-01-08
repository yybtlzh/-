/**
 * @Title: KMP.java
 * @Package com.yybt.algorithm.KMP
 */
package com.yybt.algorithm.KMP;

import java.util.Arrays;

/**
  * @ClassName: KMP
  * @Description: TODO
  * @author liuzehong
 **/
public class KMP {

    /**
     * ���һ���ַ������next����
     * @param t �ַ�����
     * @return next����
     * a b a a a b a
     *-1 0 0 1 1 1 2
     *-1 0 0 1 
     * 
     */
    private  int[] getNext(char[] t) {
		int[] next = new int[t.length];
		next[0]=-1;
		int i=-1;
		int j=0;
		int length=t.length-1;
		while(j<length) {
			if (i==-1||t[i]==t[j]) {
				next[++j]=++i;
				
			}else {
				//����
				i=next[i];
			}
		
		}
		return next;
		
	}

   /**
    * ������s��ģʽ��t����KMPģʽƥ��
     * match()
     * @Title: match
     * @param @param str  ����
     * @param @param t    ģʽ��
     * @param @return    �趨�ļ�
     * @return int    ��������
     * @throws
    */
    public  int match(String str, String t){
        char[] A = str.toCharArray();
        char[] B = t.toCharArray();
        int[] next = getNext(B);
        System.out.println(Arrays.toString(next));
        int i = 0, j = 0;
        while (i<A.length && j<B.length){
            if(j == -1 || A[i]==B[j]){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        return j == B.length?i-j:-1;
    }

    public static void main(String[] args) {
        System.out.println(new KMP().match("qabcaadbcshsqhannaaansnsb", "naaa"));
    }

}