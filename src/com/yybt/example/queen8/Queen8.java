package com.yybt.example.queen8;

/**
 * 8�ʺ����⣬������չ��N�ʺ�
 * @author lx
 *
 */
public class Queen8 {  
	
    public static int num = 0; //�ۼƷ�������  
    public static final int MAXQUEEN = 8;//�ʺ������ͬʱҲ��������������  
    public static int[] cols = new int[MAXQUEEN]; //����cols���飬��ʾ8�����Ӱڷ����  
    /**
     * ���ĺ���
     * @param n
     */
    public void  getArrangement(int n){  
     //�����������в��Ϸ����У�����rows�����¼�����Ϸ���rows[i]=true  
     boolean[] rows = new boolean[MAXQUEEN];  
     for(int i=0;i<n;i++){  
        rows[cols[i]]=true;  
        int d = n-i;  
        if(cols[i]-d >= 0) {
        	rows[cols[i]-d]=true;
        }
        if(cols[i]+d <= MAXQUEEN-1) {
        	rows[cols[i]+d]=true;   
        }
     }  
     for(int i=0;i<MAXQUEEN;i++){  
       //�жϸ����Ƿ�Ϸ�    
       if(rows[i]) {
    	   continue;  
       }
       //���õ�ǰ�кϷ�������������  
       cols[n] = i;  
       //��ǰ�в�Ϊ���һ��ʱ  
       if(n<MAXQUEEN-1){  
         getArrangement(n+1);  
       }else{  
        //�ۼƷ�������  
         num++;  
         //��ӡ������Ϣ  
         printChessBoard();  
       }
     }  
    }  
    public void printChessBoard(){  
       System.out.println();
       System.out.print("��"+num+"���߷�");  
       System.out.println();
       for(int i=0;i<MAXQUEEN;i++){  
         for(int j=0;j<MAXQUEEN;j++){  
           if(i==cols[j]){  
             System.out.print("1  ");  
           }else {
        	   System.out.print("0  ");  
           }
         }  
         System.out.println(); 
       }  
    }  
    
    public static void main(String args[]){  
      Queen8 queen = new Queen8(); 
      long m=System.currentTimeMillis();
      queen.getArrangement(0);
      System.out.println("�ܹ�"+num+"�ַ���");
      long n=System.currentTimeMillis();
      System.out.println("��ʱ��"+(long)(n-m)+"����");
    }  
     
}  