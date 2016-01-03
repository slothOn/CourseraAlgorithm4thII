//理解结点的更新
package chapter3;

import chapter1.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
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
	private Node root;
	public BinarySearchTree(){
		root=null;
	}
	
	public void put(Key key, Value val){
		root=put(root, key, val);
	}
	private Node put(Node n, Key key, Value val){
		if(n == null)	return new Node(key, val, 1);
		int cmp=key.compareTo(n.key);
		if(cmp == 0)  n.val=val;
		else if(cmp<0)  n.left=put(n.left, key, val); 
		else n.right=put(n.right, key, val);
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	
	public Value get(Key key){
		//可递归,此处循环实现
		Node n=root;
		while(n != null){
			int cmp=key.compareTo(n.key);
			if(cmp == 0) return n.val;
			else if(cmp<0)	n=n.left;
			else n=n.right;
		}
		return null;
	}
	/*递归实现
	private Node get(Node n, Key key){
		if(n == null)	return null;
		int cmp=key.compareTo(n.key);
		if(cmp == 0) return n;
		else if(cmp<0) return get(n.left, key);
		else return get(n.right, key);
	}
	*/
	
	
	public boolean contains(Key key){
		return get(key) == null;
	}
	
	public boolean isEmpty(){
		return size(root) == 0;
	}
	
	public int size(){
		return size(root);
	}
	private int size(Node n){
		if(n == null)  return 0;
		return n.count;
	}
	
	public Key min(){
		/*循环实现
		if(isEmpty())  return null;
		Node n;
		for(n=root;n.left != null;n=n.left);
		return n.key;
		*/
		//递归实现,后续使用
		Node n=min(root);
		if(n == null)	return null;
		return n.key;
	}
	private Node min(Node n){
		if(n == null)	return null;
		if(n.left == null)	return n;
		return min(n.left);
	}
	
	public Key max(){
		/*
		if(isEmpty())  return null;
		Node n;
		for(n=root;n.right != null;n=n.right);
		return n.key;
		*/
		Node n=max(root);
		if(n == null)		return null;
		return n.key;
	}
	private Node max(Node n){
		if(n == null)	return null;
		if(n.right == null)	return n;
		return max(n.right);
	}
	//不大于key的最大元素
	public Key floor(Key key){
		//递归实现
		return floor(root, key);
	}
	private Key floor(Node n, Key key){
		if(n == null) return null;
		int cmp=key.compareTo(n.key);
		if(cmp<0){
			return floor(n.left, key);
		}else if(cmp == 0){
			return n.key;
		}else{
			Key fright=floor(n.right, key);
			if(fright == null) return n.key;
			else return fright;
		}
		
	}
	//不小于key的最小元素
	public Key ceiling(Key key){
		return ceiling(root, key);
	}
	private Key ceiling(Node n, Key key){
		if(n == null) return null;
		int cmp=key.compareTo(n.key);
		if(cmp == 0) return n.key;
		if(cmp>0){
			return ceiling(n.right, key);
		}else{
			Key cleft=ceiling(n.left, key);
			if(cleft == null) return n.key;
			else return cleft;
		}
	}
	
	//number of keys less than key
	public int rank(Key key){
		return rank(root, key);
	}
	private int rank(Node n, Key key){
		if(n == null) return 0;
		int cmp=key.compareTo(n.key);
		if(cmp == 0){
			return size(n.left);
		}else if(cmp>0){
			return size(n.left)+1+rank(n.right, key);
		}else{
			return rank(n.left, key);
		}
	}
	
	
	//key of rank k
	public Key select(int k){
		return select(root, k);
	}
	private Key select(Node n, int k){
		if(n == null) return null;
		if(size(n.left) == k) return n.key;
		if(size(n.left)<k) return select(n.right, k+1);
		return select(n.left, k); 
	}
	
	public void deleteMin(){
		root=deleteMin(root);
	}
	//深刻理解node替换
	private Node deleteMin(Node n){
		if(n == null)	return null;
		if(n.left == null) return n.right;
		n.left=deleteMin(n.left);
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	
	public void deleteMax(){
		root=deleteMax(root);
	}
	private Node deleteMax(Node n){
		if(n == null) return null;
		if(n.right == null) return n.left;
		n.right=deleteMax(n.right);
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	
	public void delete(Key key){
		root=delete(root, key);
	}
	private Node delete(Node n, Key key){
		if(n == null) return null;
		int cmp=key.compareTo(n.key);
		if(cmp == 0)	{
			if(n.right == null)	return n.left;
			if(n.left == null)	return n.right;
			Node mid=min(n.right);
			n.right=deleteMin(n.right);
			mid.left=n.left;
			mid.right=n.right;
			mid.count=size(mid.left)+size(mid.right)+1;
			return mid;
		}else if(cmp>0){
			n.right=delete(n.right, key);
			n.count=size(n.left)+size(n.right)+1;
			return n;
		}else{
			n.left=delete(n.left, key);
			n.count=size(n.left)+size(n.right)+1;
			return n;
		}
	}
	
	public int size(Key lo, Key hi){
		return rank(hi)-rank(lo);
	}
	
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue=new Queue<Key>();
		keys(root, lo, hi, queue);
		return queue;
	}
	private void keys(Node n, Key lo, Key hi, Queue<Key> queue){
		if(n == null) return;
		if(hi.compareTo(lo)<0) return;
		int cmp1=lo.compareTo(n.key);
		if(cmp1>0) {
			keys(n.right, lo, hi, queue);
			return;
		}
		int cmp2=hi.compareTo(n.key);
		if(cmp2<0) {
			keys(n.left, lo, hi, queue);
			return;
		}
		keys(n.left, lo, n.key, queue);
		queue.enqueue(n.key);
		keys(n.right, n.key, hi, queue);
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
		System.out.println(st.ceiling("Z"));
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
