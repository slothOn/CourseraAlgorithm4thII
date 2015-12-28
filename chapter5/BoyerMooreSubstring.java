package chapter5;

public class BoyerMooreSubstring {
	private int right[], R;
	public int search(String pat, String txt){
		int i, j, M=pat.length(), N=txt.length(), skip;
		R=256; right= new int[R];
		for(int r=0;r<R;r++){
			right[r]=-1;
		}
		for(int m=0;m<M;m++){
			right[pat.charAt(m)]=m;
		}
		for(i=M-1; i<N; i+=skip){
			skip=0;
			for(j=M-1; j>=0; j--, i--){
				if(pat.charAt(j)!=txt.charAt(i)){
					//不匹配
					skip=M-right[txt.charAt(j)]-1;
					if(skip<1) skip=1;
					break;
				}
			}
			if(j<0) return i+1;
		}
		return N;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="FINDINAHAYSTACKNEEDLEINA";
		String s2="NEEDLE";
		BoyerMooreSubstring bm = new BoyerMooreSubstring();
		System.out.println(bm.search(s2, s1));
	}

}
