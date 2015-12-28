package chapter5;
//挖个坑
import java.math.BigInteger;
import java.util.Random;

//拉斯维加斯算法回退字符串保证正确, 本处相信概率论
public class RabinKarp {
	private long patHash; //模式字符串的哈希值
	private int M;
	private long Q;
	private int R=256;
	private long RM;
	private long longRandomPrime(){
		
		  BigInteger prime = BigInteger.probablePrime(31, new Random());
	       return prime.longValue();
	      
		//return 997;
	}
	
	/*
	 * 除留余数法, Horner Method计算散列值
	 * 计算前M个数
	 */
	private long hash(String key, int M){
		long h =0;
		for(int i=0;i<M;i++){
			//h=h*R+key.charAt(i)%Q;
			h=(h*R+key.charAt(i))%Q;
		}
		return h;
	}
	
	public RabinKarp(String pat) {
		// TODO Auto-generated constructor stub
		this.M= pat.length();
		Q= longRandomPrime();//一个很大的素数
		long sum=0;
		RM=1;
		for(int i=0;i<M;i++){;
			RM = R*RM;
		}
		patHash = hash(pat, M);
	}
	
	public int search(String txt){
		int N= txt.length();
		long hashx = hash(txt, M);
		if(hashx == patHash){
			return 0;
		}
		for(int i=1; i<N-M; i++){
			long qt=Q-(txt.charAt(i-1)*RM);
			hashx=(hashx+qt)* R%Q+txt.charAt(i+M-1)%Q;
			if(hashx==patHash){
				return i;
			}
		}
		return N;
	}
	
	public boolean check(int i){
		//蒙特卡洛算法是再跑一遍
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
