import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	// apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
	private static int R=256;
	public static void encode(){
    	String s= BinaryStdIn.readString();
    	CircularSuffixArray suffixa= new CircularSuffixArray(s);
    	char[] ts= new char[suffixa.length()];
    	for(int i=0;i<suffixa.length();i++){
    		int k=suffixa.index(i);
    		if(k==0) BinaryStdOut.write(i);
    		ts[i]= charAtEnd(s, k);
    	}
    	BinaryStdOut.write(String.valueOf(ts));
    	BinaryStdOut.close();
    }
	private static char charAtEnd(String s, int t){
		//当前排在第t个字符串
		if(t==0) return s.charAt(s.length()-1);
		return s.charAt(t-1);
	}
	
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode(){
    	int first= BinaryStdIn.readInt();
    	char[] t= BinaryStdIn.readString().toCharArray();
    	//原始顺序中该字符串后面那个
    	int[] next= new int[t.length];
    	int[] count= new int[R+1];
    	for(int i=0;i<t.length;i++){
    		count[t[i]+1]++;
    	}
    	for(int i=0; i<count.length-1;i++){
    		count[i+1] = count[i]+count[i+1];
    	}
   
    	for(int i=0;i<t.length;i++){
    		char c=t[i];
    		next[count[c]]= i;
    		count[c]++;
    	}
    	int num=0;
    	while(num<t.length){
    		BinaryStdOut.write(t[next[first]]);
        	first= next[first];
        	num++;
    	}
    	BinaryStdOut.close();
    }
  
    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args[0].equals("-")) encode();
		if(args[0].equals("+")) decode();
	}

}
