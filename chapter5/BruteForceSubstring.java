package chapter5;

public class BruteForceSubstring {
	/*
	public static int search(String pat, String txt){
		//Brute Force
		int M=txt.length(), N=pat.length(), i, j;
		for(i=0;i<M-N;i++){
			for(j=0;j<N;j++){
				if(txt.charAt(i+j)!=pat.charAt(j)){
					break;
				}
			}
			if(j==N) return i;
		}
		return N;
	}
	*/
	
	/*
	public static int search(String pat, String txt){
		int M=txt.length(), N=pat.length(), i ,j;
		for(i=0, j=0;i<M&& j<N;i++){
			if(txt.charAt(i)!=pat.charAt(j)){
				j=0; i -=j;
			}else{
				j++;
			}
			
		}
		if(j==pat.length()){
			return i-N;
		}
		return M;
	}
	*/
	public static int search(String pat, String txt){
		int j, M=pat.length();
		int i, N=txt.length();
		for(i=0, j=0;i<N&&j<M;i++){
			if(txt.charAt(i)==pat.charAt(j)){
				j++;
			}else{
				i-=j; j=0;
			}
		}
		if(j==M) return i-M;
		else return N;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="ABACADABRAC";
		String s2="ABRA";
		System.out.println(search(s2, s1));
	}

}
