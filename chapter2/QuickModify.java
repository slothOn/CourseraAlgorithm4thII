package chapter2;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdRandom;

public class QuickModify {
	//基于切分的选择
	public static Comparable select(Comparable[] a, int k){
		//return select(a, 0, a.length-1, k);method1
		int lo=0, hi=a.length-1;
		while(lo<hi){
			int p=partition(a, lo, hi);
			if(p == k) return a[p];
			if(p<k) {
				lo=p+1;
			}else{
				hi=p-1;
			}
		}
		return a[k];
	}
	private static Comparable select(Comparable[] a, int lo, int hi, int k){
		if(lo >= hi) return null;
		int i=lo+1, j=hi;
		while(true){
			while(i <= hi && less(a[i], a[lo])){i++;}
			if(i>hi) break;
			while(j > lo && less(a[lo], a[j])){j--;}
			if(j == lo) break;
			if(i>j) break;
			swap(a, i, j);
		}
		swap(a, lo, j);
		if(j == k) return a[j];
		if(j<k) return select(a, j+1, hi, k);
		return select(a, lo, j-1, k);
	}
	
	public static void sort(Comparable[ ] a){
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi){
		if(lo+5>=hi){
			//switch to insertionsort
			for(int i=lo+1;i<=hi;i++){
				int j=i;
				while(j>lo && less(a[j], a[j-1])){
					swap(a, j, j-1); j--;
				}
			}
			return;
		}
		int p=partition(a, lo, hi);
		sort(a, lo, p-1);
		sort(a, p+1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi){
		int t=medium3(a, lo, (lo+hi)/2, hi);
		swap(a, lo, t);//问题归约
		int i=lo+1, j=hi;
		while(true){
			while(i<=hi && less(a[i], a[lo])){i++;}
			if(i>hi) break;
			while(j>lo && less(a[lo], a[j])){j--;}
			if(j == lo) break;
			if(i>j) break;
			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}
	private static int medium3(Comparable[] a, int lo, int mid, int hi){
		Comparable t1=a[lo]; Comparable t2=a[mid]; Comparable t3=a[hi];
		if(less(a[lo], a[mid]) && less(a[mid], a[hi])){
			return mid;
		}
		
		if(less(a[mid], a[lo]) && less(a[lo], a[hi])){
			return lo;
		}
		return hi;
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
		System.out.println(select(a, 5));
		show(a);
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}
}
