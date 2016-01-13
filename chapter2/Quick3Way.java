package chapter2;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdRandom;

public class Quick3Way {
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi){
		if(lo >= hi) return;
		int lt=lo, i=lo, ht=hi;
		while(i <= ht){
			int cmp=a[i].compareTo(a[lt]);
			if(cmp>0){
				swap(a, i, ht);
				ht--;
			}else if(cmp == 0){
				i++;
			}else{
				swap(a, i, lt);
				lt++;
				i++;
			}
		}
		sort(a, lo, lt-1);
		sort(a, i, hi);
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
