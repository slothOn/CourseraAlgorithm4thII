package chapter2;

import java.util.Random;

//保证random的uniform
public class RandomShuffling {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r=new Random();
		int[] a={8, 6 ,9 ,7 ,2 ,4, 10, 5, 3};
		for(int i=0;i<a.length;i++){
			int j=r.nextInt(i+1);
			swap(a, i, j);
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+", ");
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
}
