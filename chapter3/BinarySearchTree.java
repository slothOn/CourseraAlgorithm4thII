package chapter3;

public class BinarySearchTree<Key, Value> {
	private class Node{
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int count;
		public Node(Key key, Value val, int count) {
			// TODO Auto-generated constructor stub
			this.key=key; this.val=val; this.count=count;
		}
	}
	private Node first;
	public BinarySearchTree(){
		
	}
	
	public void put(Key key, Value val){
		
	}
	
	public Value get(Key key){
		
	}
	
	public void delete(Key key){
		
	}
	
	public boolean contains(Key key){
		
	}
	
	public boolean isEmpty(){
		return size(first) == 0;
	}
	
	public int size(){
		return size(first);
	}
	private int size(Node n){
		if(n == null)  return 0;
		return size(n.left)+size(n.right)+1;
	}
	
	public Iterable<Key> keys(){
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
