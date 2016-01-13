package chapter2;

import edu.princeton.cs.introcs.In;

public class Selection {
	public static void sort(Comparable[] a){
		for(int i=0;i<a.length;i++){
			int min=i;
			for(int j=i+1;j<a.length;j++){
				if(less(a[j], a[min])) min=j;
			}
			exch(a, min, i);
		}
	}
	
	public static boolean less(Comparable v, Comparable w){
		if(v.compareTo(w)<0) return true;
		return false;
	}
	
	public static void exch(Comparable[] a, int i, int j){
		Comparable swap=a[i];
		a[i]=a[j];
		a[j]=swap;
	}
	
	public static void show(Comparable[] a){
		for(Comparable c:a){
			System.out.print(c+", ");
		}
	}
	
	public static boolean isSorted(Comparable[] a){
		for(int i=0;i<a.length-1;i++){
			if(a[i].compareTo(a[i+1])>0){
				return false;
			}
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
