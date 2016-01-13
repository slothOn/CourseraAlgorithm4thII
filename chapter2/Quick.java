package chapter2;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdRandom;

public class Quick {
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi){
		if(lo>=hi) return;
		int p=partition(a, lo, hi);
		sort(a, lo, p-1);
		sort(a, p+1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi){
		int i=lo+1,j=hi;
		while(true){
			while(i<=hi && less(a[i], a[lo])){i++;}
			if(i>hi) break; 
			while(j>lo && less(a[lo], a[j])) {j--;}
			if(j == lo) break;
			if(i>j) break;
			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}
	
	private static boolean less(Comparable a, Comparable b){
		int cmp=a.compareTo(b);
		if(cmp<0) return true;
		return false;
	}
	private static void swap(Comparable[] a, int i, int j){
		Comparable swap=a[i];
		a[i]=a[j];
		a[j]=swap;
	}
	public static boolean isSorted(Comparable[] a){
		for(int i=0;i<a.length-1;i++){
			if(a[i].compareTo(a[i+1])>0){
				return false;
			}
		}
		return true;
	}
	
	public static void show(Comparable[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+", ");
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] a= In.readStrings("/media/zxc/Personal Affairs/jwork/algs4-data/words3.txt");
		show(a);
		StdRandom.shuffle(a);
		show(a);
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}

}
