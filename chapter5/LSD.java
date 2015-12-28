package chapter5;

import edu.princeton.cs.algs4.In;

public class LSD {
	
	public void sort(String[] lines, int W){
		int R = 256;//8位Ascii码 
		for(int i=W-1;i>=0;i--){
			int[] count = new int[R+1];
			for(int j=0;j<lines.length;j++){
				count[lines[j].charAt(i)+1]++;
			}
			for(int j=1;j<count.length;j++){
				count[j] += count[j-1];
			}
			String[] aux = new String[lines.length];
			for(int j=0;j<lines.length;j++){
				aux[count[lines[j].charAt(i)]] = lines[j];
				count[lines[j].charAt(i)]++;
			}
			System.arraycopy(lines, 0, aux, 0, aux.length);
		}
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("E:\\jwork\\algs4-data\\lsdtest.txt");
		String[] lines = in.readAllLines();
		LSD lsd = new LSD();
		lsd.sort(lines, 3);
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
		}
	}

}
