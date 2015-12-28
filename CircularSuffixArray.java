
public class CircularSuffixArray {
	private int N;
	private int[] index;
	public CircularSuffixArray(String s)  {
		// circular suffix array of s
		if(s==null) throw new NullPointerException();
		char[] a= s.toCharArray();
		int[] ia= new int[a.length];
		for(int i=0;i<ia.length;i++) ia[i] = a[i];
		N=a.length;
		index= new int[N];
		for(int i=0;i<N;i++){
			index[i]=i;	
		}
		//三向字符串快速排序
		sort(a, 0, N-1, 0);
	}
	private void sort(char[] s, int lo, int hi, int d){
		if(lo>=hi) return;
		int val=charAt(s, lo, d);
		int lt=lo, t=lo, ht=hi;
		while(t<=ht){
			if(charAt(s, t, d)==val) t++;
			else if(charAt(s, t, d)> val) {
				//交换index, t, ht
				{int swap = index[t]; index[t]= index[ht]; index[ht]=swap;}
				ht--;
			}else{
				//交换index, t, lt
				{int swap = index[t]; index[t]= index[lt]; index[lt]=swap;}
				t++;
				lt++;
			}
		}
		sort(s, lo, lt-1, d);
		if(val>=0) sort(s, lt, t-1, d+1);//确保结尾情况
		sort(s, t, hi, d);
	}
	private int charAt(char[] s, int t, int d){
		//当前排在第t个字符串
		int i=index[t];
		if(d>N-1) return -1;
		if(i+d<N) return s[i+d];
		else return s[i+d-N];
	}
	
    public int length() {
    	// length of s
    	return N;
    }
    public int index(int i) {
    	// returns index of ith sorted suffix
    	if(i<0||i>N-1) throw new IndexOutOfBoundsException();
    	return index[i];
    }
    
    private static String convertHex2String(String s){
    	int[] val= new int[256];
    	int count= 0;
    	for(char i='0';i<='9';i++) val[i]= count++;
    	for(char i='a';i<'g';i++) val[i]= count++;
    	char a,b;
    	int n=s.length();
    	char[] newchar= new char[n/2];
    	for(int i=0;i<n;i+=2){
    		a=s.charAt(i);
    		b=s.charAt(i+1);
    		newchar[i/2]= (char) (val[a]*16+val[b]);
    	}
    	int[] ia= new int[newchar.length];
		for(int i=0;i<ia.length;i++) ia[i] = newchar[i];
    	return String.valueOf(newchar);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="546e002500771f006562";//s7r2
		String s1="006a7300052c231c0442";//s0r3
		String s2="005b69003e7f145c307e";//s0r3
		String s4="160f5740023e79230000";//s9r8
		CircularSuffixArray suffix= new CircularSuffixArray(convertHex2String(s));
		for(int i=0;i<suffix.length();i++){
			System.out.println(suffix.index(i));
		}
	}

}
