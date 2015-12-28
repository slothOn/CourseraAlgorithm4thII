package chapter5;

import edu.princeton.cs.algs4.In;

public class Quick3string {
	
	private int charAt(String line, int w){
		if(w>line.length()-1) return -1;
		else return line.charAt(w);
	}
	private void exch(Comparable[] a, int i, int j){
		Comparable temp= a[i];
		a[i]= a[j];
		a[j]= temp;
	}
	private void sort(String[] a, int lo, int hi, int w){
		//三向切分
		if(lo>=hi) return;
		int i, lt, gt;
		int std=charAt(a[lo], w);
		lt=i=lo; gt=hi;
		while(i<=gt){
			if(std==charAt(a[i],w)){
				i++;
			}else if(charAt(a[i],w)<std){
				exch(a, i, lt);
				i++;
				lt++;
			}else{
				exch(a, i, gt);
				gt--;
			}
		}
		sort(a, lo, lt-1, w);
		sort(a, i, hi, w);
		if(std>0) sort(a, lt, i-1, w+1);
	}
	
	public void sort(String[] a){
		sort(a, 0, a.length-1, 0);
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
		
		Quick3string q3s= new Quick3string();
	    q3s.sort(lines);
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
		}
	}

}
