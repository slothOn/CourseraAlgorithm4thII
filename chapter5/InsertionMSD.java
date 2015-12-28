package chapter5;

import edu.princeton.cs.algs4.In;

public class InsertionMSD {
	private int R =256;
	private int M =15;
	private String[] aux;
	public void sort(String[] lines){
		aux = new String[lines.length];
		sort(lines, 0, lines.length-1, 0);
	}
	
	private int charAt(String line, int index){
		if(index>=line.length()) return -1;
		return line.charAt(index);
	}
	
	private boolean less(String s1, String s2, int w){
		if(s1.substring(w).compareTo(s2.substring(w))<0) return true;
		return false;
	}
	private void exch(String[] lines, int i ,int j){
		String temp = lines[i]; lines[i] = lines[j]; lines[j] = temp;
	}
	private void insertionSort(String[] lines, int lo, int hi, int w){
		for(int i=lo+1;i<=hi;i++){
			for(int j=i;j>lo;j--){
				if(!less(lines[j], lines[j-1], w)) break;
				exch(lines, j-1, j);
			}
		}
	}
	
	private void sort(String[] lines, int lo, int hi, int w){
		if(lo+M>=hi){
			insertionSort(lines, lo, hi, w);
			return;
		}
		int count[] = new int[R+2];
		for(int i=lo;i<=hi;i++){
			count[charAt(lines[i], w)+2]++;
		}
		for(int i=1;i<count.length;i++){
			count[i] += count[i-1];
		}
		for(int i=lo;i<=hi;i++){
			aux[count[charAt(lines[i], w)+1]] = lines[i];
			count[charAt(lines[i], w)+1]++;
		}
		for(int i=lo;i<=hi;i++){
			lines[i] = aux[i-lo];
		}
		for(int i=0;i<count.length-2;i++){
			sort(lines, lo+count[i], lo+count[i+1], w+1);
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
		
		InsertionMSD msd = new InsertionMSD();
		msd.sort(lines);
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
		}
	}

}
