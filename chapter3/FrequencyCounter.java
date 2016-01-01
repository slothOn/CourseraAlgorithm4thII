package chapter3;

import edu.princeton.cs.algs4.In;

public class FrequencyCounter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int minlen = 1;	
		In in = new In("E:\\jwork\\algs4-data\\tinytinyTale.txt");
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		while(!in.isEmpty()){
			String word = in.readString();
			if(word.length()<minlen) continue;
			if(!st.contains(word)){
				st.put(word, 1);
			}else{
				st.put(word, st.get(word)+1);
			}
		}
		
		String max="";
		st.put(max, 0);
		for(String key:st.keys()){
			if(st.get(key)>st.get(max)){
				max=key;
			}
		}
		System.out.println(max+","+st.get(max));
	}
}
