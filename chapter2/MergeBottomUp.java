package chapter2;

import edu.princeton.cs.introcs.In;

public class MergeBottomUp {

	public static void sort(Comparable[] a){
		int N=a.length;
		Comparable[] aux=new Comparable[N];
		for(int t=1;t<N;t += t){
			for(int i=0;i<N-t;i += t*2){
				merge(a, aux, i, Math.min(i+2*t-1, a.length-1), i+t-1);
			}
		}
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid){
		for(int i=lo;i<=hi;i++){
			aux[i]=a[i];
		}
		for(int k=lo, i=lo, j=mid+1;k<=hi;k++){
			if(i>mid){a[k]=aux[j++];}
			else if(j>hi){a[k]=aux[i++];}
			else if(!less(aux[j], aux[i])){a[k]=aux[i++];}
			else a[k]=aux[j++];
		}
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
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}
}
