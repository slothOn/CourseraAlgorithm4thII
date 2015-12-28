package chapter5;

import edu.princeton.cs.algs4.In;

public class KeyIndexCounting {
	private String[] people;
	private int[] groups;
	private int N, R;
	public KeyIndexCounting(int N, int R) {
		// TODO Auto-generated constructor stub
		this.N = N;
		this.R = R;
		people = new String[N];
		groups = new int[N];
		groups[0] = 0;
	}
	
	public String[] sort(){
		int[] counts = new int[R+1];
		String[] aux = new String[N];
		for(int i=0;i<N;i++){
			counts[groups[i]]++;
		}
		for(int i=1;i<R+1;i++){
			counts[i] += counts[i-1];
		}
		for(int i=0;i<N;i++){
			aux[counts[groups[i]-1]] = people[i];
			counts[groups[i]-1]++;
		}
		return aux;
	}
	
	public void add(String person, int group, int index){
		people[index]=person;
		groups[index]=group;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in =new In("E:\\jwork\\algs4-data\\keyindexcounting.txt");
		int groupr=4;
		String[] lines= in.readAllLines();
		KeyIndexCounting countingsort= new KeyIndexCounting(lines.length, groupr);
		for(int i=0;i<lines.length;i++){
			String[] strs= lines[i].split(" ");
			countingsort.add(strs[0], Integer.valueOf(strs[1]), i);
		}
		String[] rs= countingsort.sort();
		for(int i=0;i<rs.length;i++){
			System.out.println(rs[i]);
		}
	}

}
