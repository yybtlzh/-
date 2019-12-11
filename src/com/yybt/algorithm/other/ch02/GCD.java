package com.yybt.algorithm.other.ch02;

import java.math.BigInteger;

/**
 * 欧几里得算法(辗转相除法)求两个数的最大公约数(Greatest Common Divisor)
 * 
 * @author LIUZEHONG
 *
 */
public class GCD {
	
	/**
	 * 静态类前面最好都加个这个，想想这是为啥
	 */
	private GCD(){
		
	}

	/**
	 * 欧几里得算法其中a>b
	 * 在确定RSA密钥的第五个步骤中，是：d ≡ e-1mod ϕ(n)
     * 将其做简单的变形为：ed ≡ 1 mod ϕ(n)。也就是ed mod ϕ(n) = 1。
     * 欧几里德算法中说：对于整数a，b都可以写成a = kb+r，
     * 那么可以将ed mod ϕ(n) = 1继续写成ed - ϕ(n)k = 1。
     * 这样将d看成是x,k看作是y，所以求解d的过程也就是求解扩展欧几里德算法中的x的过程。
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigInteger gcd(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return a;
		} else {
			return gcd(b, a.mod(b));
		}
	}
	/**
	 * 欧几里得算法扩展
	 * a>b>=0
	 * @param a
	 * @param b
	 * @return
	 * 第一个值是最大公约数，第二个值表示x，第三个值表示y。
	 */
	public static BigInteger[] extend_gcd(BigInteger a,BigInteger b){
		BigInteger[] result=new BigInteger[3];
		if (b.equals(BigInteger.ZERO)) {
			result[0]=a;
			result[1]=BigInteger.ONE;
			result[2]=BigInteger.ZERO;
		}  else {
			BigInteger [] temp=extend_gcd(b,a.mod(b));
		    result[0]=temp[0];
		    result[1]=temp[2];
	        result[2]=temp[1].subtract((a.divide(b)).multiply(temp[2]));
		}
		return result;
	}
	
	
	/**
	 * 便于理解上面的，看得也清楚，只是把BigInteger换成long类型
	 * @param a
	 * @param b
	 * @return
	 */
	public static long[] extend_gcd2(long a,long b){
	    long[] result=new long[3];
	    if(b==0)
	    {
	    	result[0]=a;
	    	result[1]=1;
	    	result[2]=0;
	        return result;
	    }
	    long [] temp=extend_gcd2(b,a%b);
	    result[0]=temp[0];
	    result[1]=temp[2];
    	result[2]=temp[1]-(a/b)*temp[2];
	    return result;
	}
	
	// 最小公倍数
    public static BigInteger get_lcm(BigInteger n1, BigInteger n2) {
        return n1 .multiply(n2) .divide( gcd(n1, n2)) ;
    }


	public static void main(String[] args) {
		BigInteger[] extGcd = GCD.extend_gcd(new BigInteger("18"), new BigInteger("30"));
		System.out.println(extGcd[0] + " " + extGcd[1] + " " + extGcd[2]);
		long[] extend_gcd = GCD.extend_gcd2(18, 30);
		System.out.println(extend_gcd[0]+" "+extend_gcd[1]+" "+extend_gcd[2]);
		System.out.println(get_lcm(new BigInteger("18"), new BigInteger("30")));
	}

}
