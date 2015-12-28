package chapter5;

import edu.princeton.cs.algs4.In;

public class MSD {
	private int R = 256;
	private String[] aux;//可重复利用
	private int charAt(String line, int index) {
		if(index>=line.length()){
			return -1;
		}else{
			return line.charAt(index);
		}
	}
	
	public void sort(String[] lines){
		aux = new String[lines.length];
		sort(lines, 0, lines.length-1, 0);
	}
	
	private void sort(String[] lines, int lo, int hi, int w){
		if(lo>=hi) return;
		int[] count= new int[R+2];//留给-1
		for(int i=lo;i<=hi;i++){
			count[charAt(lines[i], w)+2]++;
		}
		for(int i=1;i<count.length;i++){
			count[i] += count[i-1];
		}
		for(int i=lo;i<=hi;i++){
			aux[count[charAt(lines[i], w)+1]]=lines[i];
			count[charAt(lines[i],w)+1]++;
		}
		for(int i=lo;i<=hi;i++){
			lines[i]=aux[i-lo];
		}
		//count修正过了
		for(int i=0;i<count.length-2;i++){
			sort(lines, lo+count[i], lo+count[i+1]-1, w+1);
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
		
		MSD msd = new MSD();
		msd.sort(lines);
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
		}
	}

}
