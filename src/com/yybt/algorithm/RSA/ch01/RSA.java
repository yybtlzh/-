package com.yybt.algorithm.RSA.ch01;

import java.math.BigInteger;

import com.yybt.algorithm.other.ch01.Montgomery;
import com.yybt.algorithm.other.ch02.GCD;

/**
 * RSA加密、解密   原理：明文＝密文DmodN明文＝ 密文DmodN
 * @author liuzehong
 *
 */
public class RSA {
	
	private  final long maxWait=10000;
	/**
	 * 求N
	 * @param a
	 * @param b
	 * @return
	 */
	private BigInteger  getN(BigInteger p,BigInteger q ){
		return p.multiply(q);
	}
	
	/**
	 * 求L :L 是 p－1 和 q－1的最小公倍数
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
	 * 求E :1 < E < L&&gcd（E，L）=1
	 * @param a
	 * @param b
	 * @return
	 */
	public  BigInteger  getE(BigInteger p,BigInteger q ,BigInteger L){
		//nextProbablePrime
		//返回一个整数大于该BigInteger的素数。通过此方法返回的数字是复合的概率不超过2-100.
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
	 * 生成秘钥：公钥[E,N] 私钥[D,N]
	 * @param p
	 * @param q
	 * @return  [n,e,d]
	 */
	public BigInteger[] genKey(BigInteger p,BigInteger q){
		 BigInteger[] result=new  BigInteger[3];
		 BigInteger N = getN(p, q);
		 /**
		  * 获取E会比较慢
		  */
		 BigInteger L = getL(p, q);
		 BigInteger E= getE(p, q,L);
		 //BigInteger E = new BigInteger("3889") ;
		 BigInteger OL = p.subtract(BigInteger.ONE)
				         .multiply(q.subtract(BigInteger.ONE)) ;
		 //1 < D < L
		 //E＊D mod L ＝ 1 ,所以这里用到了欧几里得拓展
		 BigInteger[]  rxy= GCD.extend_gcd(OL, E);
		 BigInteger D = rxy[2];
		 result[0]=N;
		 result[1]=E;
		 result[2]=D;
		return result;
	}
	
	/**
	 * 加密
	 * @param M 明文
	 * @param pubkey 懒得做区分了，就直接用那个返回的秘钥当参数了
	 * @return
	 * 加密 (用e,n)
	 * 明文：0<M<n
	 * 密文：C≡M^e (mod n)
	 */
	public BigInteger encrypt(BigInteger M, BigInteger[] pubkey){
		BigInteger N = pubkey[0] ;
		BigInteger E = pubkey[1] ;
		return Montgomery.montgomery(M, E, N) ;
	}
	
	/**
	 * 解密
	 * @param C  秘文
	 * @param pubkey 懒得做区分了，就直接用那个返回的秘钥当参数了
	 * @return
	 * 解密 (用d,n)
	 * 密文：C
	 * 明文：M ≡ C^d (mod n)
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
		// 生成秘钥
		BigInteger[] genKey = rsa.genKey(p, q);
		System.out.println("N:"+genKey[0]+" \t\nE:"+ genKey[1]+" \t\nD:"+genKey[2]);
		BigInteger m = new BigInteger("12345") ;
		System.out.println("明文："+m);
		 // 信息加密'''
		BigInteger c = rsa.encrypt(m, genKey) ;
		    
		System.out.println("密文：" + c);
		// 信息解密'''
		BigInteger d = rsa.decrypt(c, genKey) ;
		System.out.println("明文："+d);
		 
		
	}
}

