package chapter3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class Concordance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename="";
		In input=new In(filename);
		RedBlackBST<String, SET<Integer>> st=new RedBlackBST<String, SET<Integer>>();
		String[] words=input.readAllStrings();
		for(int i=0;i<words.length;i++){
			if(!st.contains(words[i])){
				st.put(words[i], new SET<Integer>());
			}
			st.get(words[i]).add(i);
		}
		String searchword=StdIn.readString();
		for(int i:st.get(searchword)){
			//i-4到i+4
		}
	}

}
