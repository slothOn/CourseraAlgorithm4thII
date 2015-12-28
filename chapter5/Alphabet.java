package chapter5;

import edu.princeton.cs.algs4.In;

public class Alphabet {
	//根据s中字符创建一张新的字母表
	private char[] alphas;
	private int[] alpi;
	private int N;
	public Alphabet(String s){
		//默认s没有重复数组
		N = s.length();
		alphas = new char[N]; alpi = new int[Character.MAX_VALUE];
		for(int i=0;i<N;i++){
			alphas[i] = s.charAt(i);
		}
		for(int i=0;i<N;i++){
			alpi[i] = -1;
		}
		for(int i=0;i<N;i++){
			alpi[alphas[i]] = i;
		}	
	}
	//获取字母表中索引位置的字符
	public char toChar(int index){
		return alphas[index]; 
	}
	//获取c的索引， 在0到R-1之间
	public int toIndex(char c){
		if(!contains(c)) throw new IllegalArgumentException("c doesn't exist in the alphabet");
		return alpi[c];
	}
	//c在字母表之中吗
	public boolean contains(char c){
		return alpi[c]!=-1;
	}
	//基数
	public int R(){
		return N;
	}	
	//表示一个索引所需的比特数
	public int lgR(){
		int lgr = 0, n=N;
		while(n>=1){
			n=n/2;
			lgr++;
		}
		return lgr;
	}
	//将s转换为R进制的整数
	public int[] toIndices(String s){
		int[] indices = new int[s.length()];
		for(int i=0;i<indices.length;i++){
			indices[i] = toIndex(s.charAt(i));
		}
		return indices;
	}
	//将R进制整数转换为基于该字母表的字符串
	String toChars(int[] indices){
		char[] s = new char[indices.length];
		for(int i=0;i<s.length;i++){
			s[i]= toChar(indices[i]);
		}
		return s.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alphabet alpha = new Alphabet("ABCDR");
		int R = alpha.R();
		int[] count= new int[R];
		In in = new In("/media/zxc/Personal Affairs/jwork/algs4-data/abra.txt");
		String s = in.readAll();
		int N = s.length();
		for(int i=0;i<N;i++){
			if(alpha.contains(s.charAt(i)))
				count[alpha.toIndex(s.charAt(i))]++;
		}
		for(int c=0;c<R;c++){
			System.out.println(alpha.toChar(c)+" "+count[c]);
		}
	}

}
