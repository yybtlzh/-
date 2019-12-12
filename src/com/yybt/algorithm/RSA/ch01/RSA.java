package com.yybt.algorithm.RSA.ch01;

import java.math.BigInteger;

import com.yybt.algorithm.other.ch01.Montgomery;
import com.yybt.algorithm.other.ch02.GCD;

/**
 * RSA���ܡ�����   ԭ�����ģ�����DmodN���ģ� ����DmodN
 * @author liuzehong
 *
 */
public class RSA {
	
	private  final long maxWait=10000;
	/**
	 * ��N
	 * @param a
	 * @param b
	 * @return
	 */
	private BigInteger  getN(BigInteger p,BigInteger q ){
		return p.multiply(q);
	}
	
	/**
	 * ��L :L �� p��1 �� q��1����С������
	 * @param a
	 * @param b
	 * @return
	 */
	private  BigInteger  getL(BigInteger p,BigInteger q ){
		BigInteger m=p.subtract(BigInteger.ONE);
		BigInteger n=q.subtract(BigInteger.ONE);
		return GCD.get_lcm(m, n);
	}
	//1 < E < L
			
	/**
	 * ��E :1 < E < L&&gcd��E��L��=1
	 * @param a
	 * @param b
	 * @return
	 */
	public  BigInteger  getE(BigInteger p,BigInteger q ,BigInteger L){
		//nextProbablePrime
		//����һ���������ڸ�BigInteger��������ͨ���˷������ص������Ǹ��ϵĸ��ʲ�����2-100.
		BigInteger E=null;
		int i=4;
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis()-start<maxWait&&E==null) {
			  E=L.divide(BigInteger.valueOf(i)).nextProbablePrime();
			  if (E.compareTo(L)>0) {
				E=null;
				i++;
			}
		}
		return E;
	}
	
	/**
	 * ������Կ����Կ[E,N] ˽Կ[D,N]
	 * @param p
	 * @param q
	 * @return  [n,e,d]
	 */
	public BigInteger[] genKey(BigInteger p,BigInteger q){
		 BigInteger[] result=new  BigInteger[3];
		 BigInteger N = getN(p, q);
		 /**
		  * ��ȡE��Ƚ���
		  */
		 BigInteger L = getL(p, q);
		 BigInteger E= getE(p, q,L);
		 //BigInteger E = new BigInteger("3889") ;
		 BigInteger OL = p.subtract(BigInteger.ONE)
				         .multiply(q.subtract(BigInteger.ONE)) ;
		 //1 < D < L
		 //E��D mod L �� 1 ,���������õ���ŷ�������չ
		 BigInteger[]  rxy= GCD.extend_gcd(OL, E);
		 BigInteger D = rxy[2];
		 result[0]=N;
		 result[1]=E;
		 result[2]=D;
		return result;
	}
	
	/**
	 * ����
	 * @param M ����
	 * @param pubkey �����������ˣ���ֱ�����Ǹ����ص���Կ��������
	 * @return
	 * ���� (��e,n)
	 * ���ģ�0<M<n
	 * ���ģ�C��M^e (mod n)
	 */
	public BigInteger encrypt(BigInteger M, BigInteger[] pubkey){
		BigInteger N = pubkey[0] ;
		BigInteger E = pubkey[1] ;
		return Montgomery.montgomery(M, E, N) ;
	}
	
	/**
	 * ����
	 * @param C  ����
	 * @param pubkey �����������ˣ���ֱ�����Ǹ����ص���Կ��������
	 * @return
	 * ���� (��d,n)
	 * ���ģ�C
	 * ���ģ�M �� C^d (mod n)
	 */
	public BigInteger decrypt(BigInteger C, BigInteger[] prikey){
		BigInteger N = prikey[0] ;
		BigInteger D = prikey[2] ;
		return Montgomery.montgomery(C,D, N) ;
		
	}
	
	public static void main(String[] args) {
		
		BigInteger p = new BigInteger("106697219132480173106064317148705638676529121742557567770857687729397446898790451577487723991083173010242416863238099716044775658681981821407922722052778958942891831033512463262741053961681512908218003840408526915629689432111480588966800949428079015682624591636010678691927285321708935076221951173426894836169") ;
		BigInteger q = new BigInteger("144819424465842307806353672547344125290716753535239658417883828941232509622838692761917211806963011168822281666033695157426515864265527046213326145174398018859056439431422867957079149967592078894410082695714160599647180947207504108618794637872261572262805565517756922288320779308895819726074229154002310375209") ;
		
		RSA rsa = new RSA() ;
		// ������Կ
		BigInteger[] genKey = rsa.genKey(p, q);
		System.out.println("N:"+genKey[0]+" \t\nE:"+ genKey[1]+" \t\nD:"+genKey[2]);
		BigInteger m = new BigInteger("12345") ;
		System.out.println("���ģ�"+m);
		 // ��Ϣ����'''
		BigInteger c = rsa.encrypt(m, genKey) ;
		    
		System.out.println("���ģ�" + c);
		// ��Ϣ����'''
		BigInteger d = rsa.decrypt(c, genKey) ;
		System.out.println("���ģ�"+d);
		 
		
	}
}

