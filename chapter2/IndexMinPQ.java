package chapter2;
//关联索引优先队列
public class IndexMinPQ<Item extends Comparable<Item>> {
	private int N=0;
	private Item[] items;
	private int[] pq;//索引二叉堆
	private int[] qp;//索引二叉堆逆序, pq[qp[k]]=k
	//index ranges from 0 to maxN-1
	public IndexMinPQ(int maxN){
		pq=new int[maxN+1];
		qp=new int[maxN+1];
		items=(Item[])new Comparable[maxN+1];
		for(int i=0;i<=maxN;i++){
			pq[i]=-1;
			qp[i]=-1;
		}
	}
	
	private void exch(int i, int j){
		Item swap= items[i];items[i]= items[j];items[j]= swap;
		int swapindex=pq[i];
		pq[i]=pq[j];
		pq[j]=swapindex;
		qp[pq[i]]=i;
		qp[pq[j]]=j;
	}
	private boolean less(int i, int j){
		if(items[i].compareTo(items[j])<0){
			return true;
		}return false;
	}
	private void sink(int i){
		int j;
		while(i*2<=N){
			j=i*2;
			if((j+1<=N)&&less(j+1,j))j++;
			if(less(i,j)) break;
			exch(i,j);
			i=j;
		}
	}
	private void swim(int i){
		int j;
		while(i/2>=1){
			j=i/2;
			if(less(j,i)) break;
			exch(i,j);
			i=j;
		}
	}
	//插入元素并与k关联
	public void insert(int k, Item item){
		N++;
		if(N>=items.length/2) resize(items.length*2+1);
		pq[N]=k;
		items[N]=item;
		qp[k]=N;
		swim(N);
	}
	//将索引为k元素设为item,可理解为改变优先级
	public void change(int k, Item item){
		int index=qp[k];
		items[index]=item;
	}
	//是否存在索引为k的元素
	public boolean contains(int k){
		if(qp[k]!=-1) return true;
		return false;
	}
	//删去索引k及其相关联元素
	public void delete(int k){
		int index=qp[k];
		items[index]=items[N];
		pq[index]=pq[N];
		qp[pq[N]]=index;
		qp[k]=-1;
		N--;
		if(N<items.length/4) resize(items.length/2+1);
	}
	//返回最小元素
	public Item min(){
		return items[1];
	}
	//返回最小元素索引值
	public int minIndex(){
		return pq[1];
	}
	//删除最小元素及返回其索引
	public int delMin(){
		int rindex=pq[1];
		items[1]=items[N];
		pq[1]=pq[N];
		qp[pq[1]]=1;
		qp[rindex]=-1;
		sink(1);
		N--;
		if(N<items.length/4) resize(items.length/2+1);
		return rindex;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return N;
	}
	
	private void resize(int n){
		Item[] items2= (Item[])new Comparable[n];
		int[] pq2= new int[n];
		for(int i=0;i<=N;i++){
			items2[i]= items[i];pq2[i]= pq[i];
		}
		//qp不需要resize?需要?
		items= items2;pq= pq2;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
