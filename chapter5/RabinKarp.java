package chapter5;
//挖个坑
import java.math.BigInteger;
import java.util.Random;

//拉斯维加斯算法回退字符串保证正确, 蒙特卡洛算法相信概率论
public class RabinKarp {
	private long patHash; //模式字符串的哈希值
	private int M;
	private long Q=997;
	private int R=256;
	private long RM;
	
	private long longRandomPrime(){
		BigInteger prime=BigInteger.probablePrime(31, new Random());
	    return prime.longValue();
		//return 997;
	}
	
	/*
	 * Horner
	 */
	private long hash(String key, int M){
		long hash=0;
		for(int i=0;i<M;i++){
			hash=(hash*R+key.charAt(i))%Q;
		}
		return hash;
	}
	
	public RabinKarp(String pat) {
		// TODO Auto-generated constructor stub
		Q=longRandomPrime();
		M=pat.length();
		RM=(long) Math.pow(R, M-1);
		patHash=hash(pat, M);
	}
	
	public int search(String txt){
		int N=txt.length();
		long hashx=hash(txt, M);
		if(hashx == patHash) return 0;
		for(int i=1;i<N-M;i++){
			hashx=hashx%Q-txt.charAt(i-1)*RM%Q+Q;
			hashx=(hashx*R+txt.charAt(i-1+M))%Q;
			if(hashx == patHash){
				if(check(i)) return i;
			}
		}
		return N;
	}
	
	public boolean check(int i){
		//蒙特卡洛算法
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="3141592653589793";
		String s2="26535";
		RabinKarp rk = new RabinKarp(s2);
		System.out.println(rk.search(s1));
	}

}
