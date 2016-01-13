package chapter2;

import edu.princeton.cs.introcs.In;
//每次switch a和aux,节省数组访问时间f
public class MergeSwitch {
	public static void sort(Comparable[] a){
		Comparable[] aux=new Comparable[a.length];
		for(int i=0;i<a.length;i++) aux[i]=a[i];
		sort(a, aux, 0, a.length-1);
	}
	private static void sort(Comparable[] a, Comparable[] aux, int lo ,int hi){
		if(lo>=hi) return;
		//if(lo+k>=hi) 换用插入排序,在小数组时换用插入排序是一种常见优化
		int mid=(lo+hi)/2;
		sort(aux, a, lo, mid);
		sort(aux, a, mid+1, hi);
		merge(aux, a, lo, hi, mid);
	}
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi, int mid){
		/*
		for(int i=lo;i<=hi;i++){
			aux[i]=a[i];
		}
		*/
		for(int k=lo, i=lo, j=mid+1;k<=hi;k++){
			if(i>mid){aux[k]=a[j++];}
			else if(j>hi){aux[k]=a[i++];}
			else if(!less(a[j], a[i])){aux[k]=a[i++];}
			else aux[k]=a[j++];
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
		//String[] a= In.readStrings("/media/zxc/Personal Affairs/jwork/algs4-data/words3.txt");
		String[] a={"bed", "bug", "dad", "yes", "zoo", "now", "for", "tip"};
		show(a);
		sort(a);
		if(isSorted(a)){
			show(a);
		}
	}

}
