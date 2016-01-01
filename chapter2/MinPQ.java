package chapter2;
//基于完全二叉树实现
public class MinPQ<Key extends Comparable<Key>>{
	private Key[] pq;
	private int N=0;
	public MinPQ(){
		this(10);
	}
	
	public MinPQ(int max){
		pq= (Key[])new Comparable[max+1];
	}
	
	public MinPQ(Key[] a){
		this(a.length);
		for(int i=0;i<a.length;i++){
			insert(a[i]);
		}
	}
	
	private boolean less(int i, int j){
		if(pq[i].compareTo(pq[j])<0) return true;
		return false;
	}
	private void exch(int i, int j){
		Key swap=pq[i];pq[i]=pq[j];pq[j]=swap;
	}
	private void swim(int i){
		//上浮操作
		int j;
		while(i/2>=1){
			j=i/2;
			if(less(i,j)){
				exch(i,j);
			}
			i=j;
		}
	}
	private void sink(int i){
		//下沉操作
		int j;
		while(2*i<=N){
			j=2*i;
			if((j+1<=N)&&less(j+1,j)) j++;
			if(less(i,j)) break;
			exch(i,j);
			i=j;
		}
	}
	
	public void insert(Key v){
		N++;
		if(N>=pq.length/2) resize(pq.length*2+1);
		pq[N]=v;
		swim(N);
	}
	
	public Key min(){
		return pq[1];
	}
	
	public Key delMin(){
		Key k=pq[1];
		pq[1]=pq[N];
		N--;
		sink(1);
		if(N<pq.length/4) resize(pq.length/2+1);
		return k;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return N;
	}
	
	private void resize(int n){
		Key[] pq2= (Key[])new Comparable[n];
		for(int i=1;i<=N;i++){
			pq2[i]=pq[i];
		}
		pq=pq2;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
