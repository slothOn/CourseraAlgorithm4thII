package chapter5;

public class KMPSubstring {
	private int[][] dfa;//有限状态自动机
	private int R=256;
	private void initDfa(String pat){
		int M=pat.length();
		dfa= new int[R][pat.length()];
		dfa[pat.charAt(0)][0]=1;
		//x记录1到j-1的后缀中包含的pat最长前缀的长度
		for(int j=1, x=0; j<M; j++){
			for(int r=0; r<R; r++){
				dfa[r][j]=dfa[r][x];																																																																									
			}
			dfa[pat.charAt(j)][j]=j+1;
			x= dfa[pat.charAt(j)][x];
		}
	}
	public int search(String pat, String txt){
		initDfa(pat);
		int M=pat.length(), N=txt.length(), i, j;
		for(i=0, j=0; i<N && j<M; i++){
			j=dfa[txt.charAt(i)][j];
		}
		if(j==M){
			return i-M;
		}else{
			return N;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="ABACADABRAC";
		String s2="ABRA";
		KMPSubstring kmp = new KMPSubstring();
		System.out.println(kmp.search(s2, s1));
	}

}
