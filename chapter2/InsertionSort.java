package chapter2;

import edu.princeton.cs.introcs.In;



public class InsertionSort {
	public static void sort(Comparable[] a){
		for(int i=1;i<a.length;i++){
			for(int j=i;j>0;j--){
				if(!less(a[j], a[j -1])) break;
				exch ( a, j, j-1  );
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		if(v.compareTo(w)<0){
			return true;
		}
		return false;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static void show(Comparable[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a){
		for(int i=0;i<a.length-1;i++){
			if(less(a[i+1], a[i])) return false;
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] a= In.readStrings("/media/zxc/Personal Affairs/jwork/algs4-data/words3.txt");
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}

}
