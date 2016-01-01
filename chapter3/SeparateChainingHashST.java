package chapter3;

import chapter1.Queue;

public class SeparateChainingHashST<Key, Value> {
	private int M, N;
	private SequentialSearchST<Key, Value>[] st;
	public SeparateChainingHashST() {
		// TODO Auto-generated constructor stub
		M=997;N=0;
		st=(SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for(int i=0;i<M;i++)
			st[i]=new SequentialSearchST<Key, Value>();
	}
	
	private int hash(Key key){
		//取低31位做模运算
		return (key.hashCode() & 0x7fffffff)%M;
	}
	
	public void put(Key key, Value val){
		int index=hash(key);
		st[index].put(key, val);
		N++;
	}
	
	public Value get(Key key){
		int index=hash(key);
		return st[index].get(key);
	}
	
	public void delete(Key key){
		int index=hash(key);
		st[index].delete(key);	
		N--;
	}
	
	public boolean contains(Key key){
		int index=hash(key);
		return st[index].contains(key);
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue=new Queue<Key>();
		for(int i=0;i<M;i++){
			for(Key key:st[i].keys()){
				queue.enqueue(key);
			}
		}
		return queue;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
