package chapter2;

import edu.princeton.cs.introcs.In;

public class Quick3sort {

	private static void sort(Comparable[] a, int lo, int hi){
		if(lo>=hi) return;
		int lt, ht, i;
		lt=lo; ht=hi; i=lt;//i是尚未比较的
		Comparable std=a[lo];
		while(i<=ht){
			int cmp=a[i].compareTo(std);
			if(cmp==0){
				i++;
			}else if(cmp>0){
				exch(a, i, ht);
				ht--;
			}else{
				exch(a, i, lt);
				i++;
				lt++;
			}
		}
		sort(a, lo, lt-1);
		sort(a, i, hi);
	}
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
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
