package chapter2;

public class IndexMinPriorityQueue<Item extends Comparable<Item>>{
	private Item[] pq;//item数组
	private int[] qp;//关联的索引数组,堆排列
	private int[] a;//
	int N;
	public IndexMinPriorityQueue(int maxN){
		N=0;
		pq=(Item[])new Comparable[maxN+1];
		qp=new int[maxN+1];
		a=new int[maxN+1];
	}
	
	private void swim(int i){
		while(i/2>0){
			if(less(pq[i], pq[i/2])){
				exch(pq, i, i/2);
				{int swap=qp[i]; qp[i]=qp[i/2]; qp[i/2]=swap;}
				a[qp[i]]=i; a[qp[i/2]]=i/2;
				i=i/2;
			}
			else break;
		}
	}
	private void sink(int i){
		int k=i*2;
		while(k<=N){
			if(k+1 <= N && less(pq[k+1], pq[k])) k++;
			if(less(pq[k], pq[i])){
				exch(pq, k, i);
				{int swap=qp[i]; qp[i]=qp[k]; qp[k]=swap;}
				a[qp[i]]=i; a[qp[k]]=k;
				i=k;
				k=i*2;	
			}
			else break;
		}
	}
	
	public void insert(int k, Item item){
		N++;
		pq[N]=item;
		qp[N]=k;
		a[k]=N;
		swim(N);
	}
	//坑待填
	public void change(int k, Item item){
		int i=a[k];
		pq[i]=item;
		sink(i);
		swim(i);
	}
	public boolean contains(int k){
		return a[k] != 0;
	}
	//坑待填
	public void delete(int k){
		//和N交换
		int i=a[k];
		exch(pq, i, N);
		{int swap=qp[i]; qp[i]=qp[N]; qp[N]=swap;}
		a[qp[N]]=0;
		a[qp[i]]=i;
		N--;
		sink(i);
		swim(i);
	}
	public Item min(){
		return pq[1];
	}
	public int minIndex(){
		return qp[1];
	}
	public int delMin(){
		int result=qp[1];
		exch(pq, 1, N);
		{int swap=qp[1]; qp[1]=qp[N]; qp[N]=swap;}
		a[qp[1]]=1;
		N--;
		a[result]=0;
		sink(1);
		return result;
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public int size(){
		return N;
	}
	
	private boolean less(Comparable a, Comparable b){
		int cmp=a.compareTo(b);
		if(cmp<0) return true;
		return false;
	}
	
	private void exch(Comparable[] a, int i, int j){
		Comparable swap=a[i]; a[i]=a[j]; a[j]=swap;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
