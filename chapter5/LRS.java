package chapter5;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
//查找最长重复字符串
public class LRS {
	
	public static String lrs(String s){
		String[] suffix = new String[s.length()];
		for(int i=0;i<suffix.length;i++){
			suffix[i] = s.substring(i);
		}
		Arrays.sort(suffix);
		int max=0, r, j;
		String rs="";
		for(int i=0;i<suffix.length-1;i++){
			int minl = Math.min(suffix[i].length(), suffix[i+1].length());
			for(j=minl;j>0;j--){
				if(suffix[i].substring(0, j).equals(suffix[i+1].substring(0, j))){
					break;
				}
			}
			if(j>max){
				max =j;
				rs = suffix[i].substring(0, j);
			}
		}
		return rs;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//In in = new In("../LRS.java");
		System.out.println(lrs("twinstwins"));
	}

}
