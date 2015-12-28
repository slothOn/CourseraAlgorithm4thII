package work5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

public class MoveToFront {
	  // apply move-to-front encoding, reading from standard input and writing to standard output
	private static int R=256;
	private static BinaryIn in;
	private static BinaryOut out;
    public static void encode(){
    	int[] ci= new int[R];//第c个character的位置
    	char[] ic= new char[R];//第i个位置的character
    	for(char i=0;i<R;i++) {
    		ci[i]= i;	ic[i]= i;
    	}
    	while(!in.isEmpty()){
    		char c= in.readChar();
    		out.write(ci[c], 8);
    		int ct= ci[c];
    		for(int i=ct;i>=1;i--){
    			char tc= ic[i-1];
    			ci[tc]=i;
    			ic[i]= ic[i-1];
    		}
    		ic[0]= c; ci[c]= 0;
    	}
    	out.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
    	char[] ic= new char[R];//第i个位置的character
    	for(char i=0;i<R;i++) {
    		 ic[i]= i;
    	}
    	while(!in.isEmpty()){
    		int i= in.readChar();
    		char c= ic[i];
    		out.write(c);
    		for(int j=i;j>=1;j--){
    			ic[j]= ic[j-1];
    		}
    		ic[0]= c;
    	}
    	out.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		in = new BinaryIn("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/burrows/abra.txt"); 
		out= new BinaryOut("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abra.bin");
		encode();
		/*
		in = new BinaryIn("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abra.bin"); 
		out= new BinaryOut("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abradecode.bin");
		decode();
		*/
	}

}
