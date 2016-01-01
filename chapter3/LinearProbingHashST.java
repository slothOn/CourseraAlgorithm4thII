package chapter3;

import chapter1.Queue;

public class LinearProbingHashST <Key, Value>{
	private int M, N, size;
	private Value[] vals;
	private Key[] keys;
	public LinearProbingHashST(){
		N=0;
		size=30001;
		M=997;
		vals=(Value[])new Object[size];
		keys=(Key[])new Object[size];
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff)%M;
	}
	
	public void put(Key key, Value val){
		int index=hash(key), i;
		for(i=index;keys[i]!=null;i=(i+1)%size){
			if(key.equals(keys[i])){
				vals[i]=val; return;
			}
		}
		vals[i]=val;keys[i]=key;
		N++;
	}
	
	public Value get(Key key){
		int index=hash(key), i;
		for(i=index;vals[i]!=null;i=(i+1)%size){
			if(key.equals(keys[i])){
				return vals[i];
			}
		}
		return null;
	}
	
	public void delete(Key key){
		int index=hash(key), i;
		for(i=index;vals[i]!=null;i=(i+1)%size){
			if(key.equals(keys[i])){
				keys[i]=null;
				vals[i]=null;
				N--;
				return;
			}
		}
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue= new Queue<Key>();
		for(int i=0;i<size;i++){
			if(keys[i] != null) queue.enqueue(keys[i]); 
		}
		return queue;
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
