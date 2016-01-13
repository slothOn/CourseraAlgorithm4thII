package chapter3;

import java.util.Iterator;

import chapter1.Queue;

public class SET<Key extends Comparable<Key>> implements Iterable<Key>{
	RedBlackBST<Key, Boolean> rbbst;
	public SET(){
		rbbst=new RedBlackBST<Key, Boolean>();
	}
	
	public void add(Key key){
		rbbst.put(key, true);
	}
	
	public boolean contains(Key key){
		return rbbst.contains(key);
	}
	
	public void remove(Key key){
		rbbst.delete(key);
	}
	
	public int size(){
		return rbbst.size();
	}
	
	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		Queue<Key> queue=new Queue<Key>();
		for(Key key:rbbst.keys()){
			queue.enqueue(key);
		}
		return queue.iterator();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
