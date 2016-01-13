package chapter3;

import java.io.File;

import edu.princeton.cs.algs4.In;

public class FileIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackBST<String, SET<File>> st=new RedBlackBST<String, SET<File>>();
		String[] filenames={};
		for(String filename:filenames){
			File f=new File(filename);
			In in=new In(f);
			while(!in.isEmpty()){
				String s=in.readString();
				if(!st.contains(s)){
					st.put(s, new SET<File>());
				}
				st.get(s).add(f);
			}
			String word="";
			
		}
	}

}
