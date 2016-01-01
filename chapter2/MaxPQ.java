package chapter2;

public class MaxPQ<Key extends Comparable<Key>>{
	private Key[] pq;//without using index 0
	private int N=0;
	public MaxPQ(){
		this(10);
	}
	
	public MaxPQ(int max){
		pq= (Key[])new Comparable[max+1];
	}
	
	public MaxPQ(Key[] a){
		this(a.length);
		for(int i=0;i<a.length;i++){
			insert(a[i]);
		}
	}
	
	private void sink(int i){
		//下沉操作
		int j;
		while(2*i<=N){
			j=2*i;
			if((j+1<=N)&&less(j,j+1)) j++;
			if(!less(i,j)) break;
			exch(i,j);
			i=j;
		}
	}
	private void swim(int i){
		//上浮操作
		int j;
		while(i/2>=1){
			j=i/2;
			if(less(i,j)) break;
			exch(i,j);
			i=j;
		}
	}
	private boolean less(int i, int j){
		if(pq[i].compareTo(pq[j])<0) return true;
		return false;
	}
	private void exch(int i, int j){
		Key swap=pq[i];pq[i]=pq[j];pq[j]=swap;
	}
	
	public void insert(Key v){
		N++;
		if(N>=pq.length/2) resize(2*pq.length+1);
		pq[N]=v;
		swim(N);
	}
	
	public Key max(){
		return pq[1];
	}
	
	public Key delMax(){
		Key k= pq[1];
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
