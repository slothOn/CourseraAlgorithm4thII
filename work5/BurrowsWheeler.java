package work5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

public class BurrowsWheeler {
	// apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
	private static int R=256;
    public static BinaryIn in;
    public static BinaryOut out;
	public static void encode(){
    	String s= in.readString();
    	CircularSuffix suffixa= new CircularSuffix(s);
    	char[] ts= new char[suffixa.length()];
    	for(int i=0;i<suffixa.length();i++){
    		int k=suffixa.index(i);
    		if(k==0) out.write(i);
    		ts[i]= charAtEnd(s, k);
    	}
    	out.write(String.valueOf(ts));
    	out.close();
    }
	private static char charAtEnd(String s, int t){
		//当前排在第t个字符串
		if(t==0) return s.charAt(s.length()-1);
		return s.charAt(t-1);
	}
	
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode(){
    	int first= in.readInt();
    	char[] t= in.readString().toCharArray();
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
    		out.write(t[next[first]]);
        	first= next[first];
        	num++;
    	}
    	
    	out.close();
    }
  
    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		in = new BinaryIn("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/burrows/abra.txt"); 
		out= new BinaryOut("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abra.bin");
		encode();
		*/
		in = new BinaryIn("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abra.bin"); 
		out= new BinaryOut("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abradecode.bin");
		decode();
	}

}
