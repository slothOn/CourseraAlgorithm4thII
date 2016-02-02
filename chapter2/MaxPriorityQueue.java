package chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Transaction;

public class MaxPriorityQueue<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N;
	public MaxPriorityQueue(int capacity) {
		// TODO Auto-generated constructor stub
		pq=(Key[]) new Comparable[capacity];
		N=0;
	}
	private void sink(int k){
		int j=k*2;
		while(j<=N){
			if(j+1<=N && less(pq[j],pq[j+1])) j++;
			if(!less(pq[k],pq[j])) break;
			exch(pq, k, j);
			k=j;
			j=k*2;
		}
	}
	private void swim(int k){
		int j=k/2;
		while(j>=1){
			if(!less(pq[j],pq[k])) break;
			exch(pq, j, k);
			k=j;
			j=k/2;
		}
	}
	public void insert(Key v){
		N++;
		pq[N]=v;
		swim(N);
	}
	public Key delMax(){
		Key max=pq[1];
		exch(pq,1,N);
		N--;
		sink(1);
		pq[N+1]=null;
		return max;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public Key max(){
		return pq[1];
	}
	public int size(){
		return N;
	}
	private boolean less(Comparable v, Comparable w){
		int cmp=v.compareTo(w);
		if(cmp<0) return true;
		return false;
	}
	private void exch(Comparable[] a, int i, int j){
		Comparable swap=a[i];
		a[i]=a[j];
		a[j]=swap;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//找出最小的5个
		MaxPriorityQueue<Transaction> mpq=new MaxPriorityQueue<Transaction>(20);
		In in=new In("E:\\jwork\\algs4-data\\tinyBatch.txt");
		while(!in.isEmpty()){
			String line=in.readLine();
			Transaction t=new Transaction(line);
			mpq.insert(t);
			if(mpq.size()>5) mpq.delMax();
		}
		while(!mpq.isEmpty()){
			System.out.println(mpq.delMax());
		}
	}

}
