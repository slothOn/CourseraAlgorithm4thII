package chapter3;

import chapter1.Queue;

public class SequentialSearchST<Key, Value> {
	private class Node{
		Key key;
		Value val;
		Node next;
		public Node(Key k, Value v, Node next) {
			// TODO Auto-generated constructor stub
			this.key=k;this.val=v;this.next=next;
		}
	}
	private Node first;
	private int N;
	public SequentialSearchST(){
		first=null;
		N=0;
	}
	//插入键值
	public void put(Key key, Value val){
		for(Node x=first;x!=null;x=x.next){
			if(key.equals(x.key)){
				x.val=val;return;
			}
		}
		first=new Node(key, val, first);
		N++;
	}
	
	public Value get(Key key){
		for(Node x=first;x!=null;x=x.next){
			if(key.equals(x.key)){
				return x.val;
			}
		}
		return null;
	}
	
	public void delete(Key key){
		if(isEmpty()) return;
		if(key.equals(first.key)) first=first.next;
		for(Node x=first;x.next!=null;x=x.next){
			if(key.equals(x.next.key)){
				x.next=x.next.next;
				N--;
			}
		}
	}
	
	public boolean contains(Key key){
		for(Node x=first;x!=null;x=x.next){
			if(key.equals(x.key)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public int size(){
		return N;
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue=new Queue<Key>();
		for(Node x=first;x!=null;x=x.next){
			queue.enqueue(x.key);
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
