package chapter5;

import edu.princeton.cs.algs4.In;

public class PersonalMSD {
	private int R = 256;
	public void sort(String[] lines){
		
		sort(lines, 0, lines.length-1, 0);
	}
	
	private void sort(String[] lines, int lo, int hi, int w){
		if(lo >= hi) return;
		if(lines[lo].charAt(w)=='\0'){
			lo++;
		}
		int count[] = new int[R+1];
		for(int i=lo;i<=hi;i++){
			count[lines[i].charAt(w)+1]++;
		}
		for(int i=1;i<count.length;i++){
			count[i] += count[i-1];
		}
		String[] aux = new String[lines.length];
		for(int i=lo;i<=hi;i++){
			aux[count[lines[i].charAt(w)]] = lines[i];
			count[lines[i].charAt(w)]++;
		}
		for(int i=lo;i<=hi;i++){
			lines[i] = aux[i-lo];
		}
		
		for(int i=1;i<count.length;i++){
			sort(lines, lo+count[i-1], lo+count[i]-1, w+1);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path1="E:\\jwork\\algs4-data\\msdtest.txt";
		String path2="/media/zxc/Personal Affairs/jwork/algs4-data/msdtest.txt";
		In in = new In(path2);
		String[] lines = in.readAllLines();
		for(int i=0;i<lines.length;i++){
			lines[i] += "\0";
		}
		
		PersonalMSD msd = new PersonalMSD();
		msd.sort(lines);
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
		}
	}

}
