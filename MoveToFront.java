import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	 // apply move-to-front encoding, reading from standard input and writing to standard output
		private static int R=256;
	    public static void encode(){
	    	int[] ci= new int[R];//第c个character的位置
	    	char[] ic= new char[R];//第i个位置的character
	    	for(char i=0;i<R;i++) {
	    		ci[i]= i;	ic[i]= i;
	    	}
	    	while(!BinaryStdIn.isEmpty()){
	    		char c= BinaryStdIn.readChar();
	    		BinaryStdOut.write(ci[c], 8);
	    		int ct= ci[c];
	    		for(int i=ct;i>=1;i--){
	    			char tc= ic[i-1];
	    			ci[tc]=i;
	    			ic[i]= ic[i-1];
	    		}
	    		ic[0]= c; ci[c]= 0;
	    	}
	    	BinaryStdOut.close();
	    }

	    // apply move-to-front decoding, reading from standard input and writing to standard output
	    public static void decode(){
	    	char[] ic= new char[R];//第i个位置的character
	    	for(char i=0;i<R;i++) {
	    		 ic[i]= i;
	    	}
	    	while(!BinaryStdIn.isEmpty()){
	    		int i= BinaryStdIn.readChar();
	    		char c= ic[i];
	    		BinaryStdOut.write(c);
	    		for(int j=i;j>=1;j--){
	    			ic[j]= ic[j-1];
	    		}
	    		ic[0]= c;
	    	}
	    	BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args[0].equals("-")) encode();
		if(args[0].equals("+")) decode();
	}

}
