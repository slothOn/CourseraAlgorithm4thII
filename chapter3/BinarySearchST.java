package chapter3;

import chapter1.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;
	public BinarySearchST(int cap){
		keys=(Key[])new Object[cap];
		vals=(Value[])new Object[cap];
		N=0;
	}
	
	public void put(Key key, Value val){
		//put key-value pair into the table (remove key from table if value is null)
		int rank=rank(key);
		if(keys[rank].compareTo(key) == 0) {
			vals[rank]=val;
			return;
		}
		for(int i=N;i>rank;i--){
			keys[i]=keys[i-1];
			vals[i]=vals[i-1];
		}
		N++;
		keys[rank]=key;
		vals[rank]=val;
	}
	
	public Value get(Key key){
		//value paired with key (null if key is absent)
		if(isEmpty()) return null;
		int rank=rank(key);
		if(keys[rank] != null && key.equals(keys[rank])){
			return vals[rank];
		}
		return null;
	}
	
	public void delete(Key key){
		//remove key (and its value) from table
		if(!contains(key)) return;
		int rank=rank(key);
		N--;
		for(int i=rank;i<N;i++){
			keys[i]=keys[i+1];
			vals[i]=vals[i+1];
		}
	}
	
	public boolean contains(Key key){
		//is there a value paired with key?
		return get(key) != null;
	}
	
	public boolean isEmpty(){
		//is the table empty?
		return N == 0;
	}
	
	public int size(){
		//number of key-value pairs in the table
		return N;
	}
	
	public Key min(){
		//smallest key
		return keys[0];
	}
	
	public Key max(){
		//largest key
		return keys[N-1];
	}
	
	public Key floor(Key key){
		//largest key less than or equal to key
		int rank=rank(key);
		if(rank == 0)	return null;
		return keys[rank-1];
	}
	
	public Key ceiling(Key key){
		//smallest key greater than or equal to key
		int rank=rank(key);
		return keys[rank];
	}
	
	public int rank(Key key){
		//number of keys less than key
		int lo=0, hi=N-1, mid=-1;
		while(lo<=hi){
			mid=(lo+hi)/2;
			int cmp=key.compareTo(keys[mid]);
			if(cmp<0){hi=mid-1;}
			else if(cmp>0){lo=mid+1;}
			else{return mid;}
		}
		return mid;
	}
	
	public Key select(int k){
		//key of rank k
		return keys[k];
	}
	
	public void deleteMin(){
		//delete smallest key
		N--;
		for(int i=0;i<N;i++){
			keys[i]=keys[i+1];
			vals[i]=vals[i+1];
		}
	}
	
	public void deleteMax(){
		//delete largest key
		N--;
	}
	
	public int size(Key lo, Key hi){
		//number of keys in [lo..hi]
		return rank(hi)-rank(lo);
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		//keys in [lo..hi], in sorted order
		int loi=rank(lo);
		int hii=rank(hi);
		Queue<Key> queue=new Queue<Key>();
		for(int i=loi;i<hii;i++){
			queue.enqueue(keys[i]);
		}
		if(hi.equals(keys[hii])) queue.enqueue(keys[hii]);
		return queue;
	}
	
	public Iterable<Key> keys(){
		//all the keys in the table
		Queue<Key> queue=new Queue<Key>();
		for(Key key:keys){
			queue.enqueue(key);
		}
		return queue;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<String,Integer> st;
		st=new BinarySearchTree<String,Integer>();
		String[] str={"S","E","A","R","C","H","E","X","A","M","P","L","E"};
		for(int i=0;i<str.length;i++){
			st.put(str[i],i);
		}
		for(String s:st.keys()){
			System.out.print(s+" "+st.get(s)+",");
		}
		System.out.println();
		System.out.println(st.rank("N"));
		System.out.println(st.ceiling("K"));
		System.out.println(st.floor("F"));
		st.deleteMin();
		System.out.println(st.floor("A"));
		st.delete("E");
		for(String s:st.keys("A","R")){
			System.out.print(s+" "+st.get(s)+",");
		}
		System.out.println();
		for(String s:st.keys()){
			System.out.print(s+" "+st.get(s)+",");
		}
	}

}
