package chapter2;

import edu.princeton.cs.algs4.In;

public class Heap {
	private static void sort(Comparable[] a){
		//a[0]是空置的
		int N=a.length-1;
		for(int i=N/2;i>=1;i--){
			sink(a, i, N);
		}
		//堆构造好可以排序
		for(int i=N;i>=2;i--){
			exch(a, 1, i);
			sink(a, 1, i-1);
		}
	}
	
	private static void sink(Comparable[] a, int k, int N){
		int j=k*2;
		while(j<=N){
			if(j+1<=N && less(a[j], a[j+1])) j++;
			if(!less(a[k], a[j])) break;
			exch(a, k, j);
			k=j;
			j=k*2;
		}
	}
	
	private static boolean less(Comparable a, Comparable b){
		int cmp=a.compareTo(b);
		if(cmp<0) return true;
		return false;
	}
	private static void exch(Comparable[] a, int i, int j){
		Comparable swap=a[i];
		a[i]=a[j];
		a[j]=swap;
	}
	public static boolean isSorted(Comparable[] a){
		for(int i=1;i<a.length-1;i++){
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
		String[] s= In.readStrings("E:\\jwork\\algs4-data\\words3.txt");
		String[] a=new String[s.length+1];
		for(int i=1;i<a.length;i++){
			a[i]=s[i-1];
		}
		show(a);
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}

}
