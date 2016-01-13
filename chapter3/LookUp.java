package chapter3;

import edu.princeton.cs.algs4.In;

public class LookUp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In input=new In("");
		int keyi, vali;
		keyi=0; vali=1;
		RedBlackBST<String, String> rbst=new RedBlackBST<String, String>();
		while(input.hasNextLine()){
			String line=input.readLine();
			rbst.put(line.split(",")[keyi], line.split(",")[vali]);
		}
		
	}

}
