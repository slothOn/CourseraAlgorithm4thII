package chapter5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class GREP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regexp="(.*"+"(A*B|AC)D"+".*)";//加前和后.*
		NFA nfa=new NFA(regexp);
		/*
		while(StdIn.hasNextLine()){
			String txt=StdIn.readLine();
			if(nfa.recognizes(txt)){
				StdOut.println(txt);
			}
		}
		*/
		String txt="ABCCBD";
		if(nfa.recognizes(txt)){
			System.out.println(txt);
		}
	}

}
