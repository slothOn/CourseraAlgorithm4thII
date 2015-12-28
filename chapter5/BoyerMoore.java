package chapter5;

public class BoyerMoore {
	private int right[], R;
	public int search(String pat, String txt){
		int i, j, M=pat.length(), N=txt.length();
		R=256; right= new int[R];
		for(int r=0;r<R;r++){
			right[r]=-1;
		}
		for(int m=0;m<M;m++){
			right[pat.charAt(m)]=m;
		}
		int skip;
		for(i=0; i<N-M; i+=skip){
			skip=0;
			for(j=M-1;j>=0;j--){
				//不匹配
				if(txt.charAt(i+j)!=pat.charAt(j)){
					skip=Math.max(1, M-right[txt.charAt(i+j)-1]);
				}
			}
			if(skip==0) return i;
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
		BoyerMoore bm = new BoyerMoore();
		System.out.println(bm.search(s2, s1));
	}

}
