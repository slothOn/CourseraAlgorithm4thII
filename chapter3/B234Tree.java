package chapter3;
//2-3-4树，重在学习插入算法以帮助理解红黑树的删除操作,delete相关操作未实现
import chapter1.Queue;
import chapter3.RedBlackBST.Node;

public class B234Tree<Key extends Comparable<Key>, Value> {
	public static boolean RED=true;
	public static boolean BLACK=false;
	private Node root;
	class Node{
		Key key;
		Value val;
		Node left, right;
		int count;
		boolean color;
		public Node(Key key, Value val, int count, boolean color) {
			// TODO Auto-generated constructor stub
			this.key=key; this.val=val; this.count=count; this.color=color;
		}
	}
	public B234Tree(){
		//create an ordered symbol table
		root=null;
	}
	
	public void put(Key key, Value val){
		//put key-value pair into the table(remove key from table if value is null)
		root=put(root, key, val);
		root.color=BLACK;
	}
	//考虑到出现4结点的三种情况以及插入情况
	private Node put(Node n, Key key, Value val){
		if(n == null) return new Node(key, val, 1, RED);
		//4结点分解
		if(isRed(n.left) && isRed(n.right)){flipColors(n);}
		int cmp=key.compareTo(n.key);
		if(cmp == 0) n.val=val;
		else if(cmp > 0) n.right=put(n.right, key, val);
		else n.left=put(n.left, key, val);
		//临时4结点配平成标准4结点,也包含了3结点的配平
		if(!isRed(n.left) && isRed(n.right)){n=rotateLeft(n);}
		if(isRed(n.left) && isRed(n.left.left)){n=rotateRight(n);}
		
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	//左转右
	private Node rotateRight(Node n){
		Node m=n.left;
		n.left=m.right;
		m.right=n;
		return m;
	}
	//右转左
	private Node rotateLeft(Node n){
		Node m=n.right;
		n.right=m.left;
		m.left=n;
		return m;
	}
	private void flipColors(Node n){
		if(n.left != null) n.left.color=BLACK;
		if(n.right != null) n.right.color=BLACK;
		n.color=RED;
	}
	private boolean isRed(Node n){
		if(n != null && n.color == RED) return true;
		return false;
	}
	
	//同BST
	public Value get(Key key){
		//value paired with key(null if key is absent)
		Node n=root;
		while(n != null){
			int cmp=key.compareTo(n.key);
			if(cmp == 0) return n.val;
			if(cmp>0) n=n.right;
			else n=n.left;
		}
		return null;
	}
	
	public void delete(Key key){
		//remove key (and its value) from table
	}
	
	public boolean contains(Key key){
		//is there a value paired with key?
		return get(key) != null;
	}
	
	public boolean isEmpty(){
		//is the table empty?
		return size() == 0;
	}
	
	public int size(){
		//number of key-value pairs
		return size(root);
	}
	private int size(Node n){
		if(n == null) return 0;
		return size(n.left)+size(n.right)+1;
	}
	
	public Key min(){
		//smallest key
		Node n=min(root);
		if(n == null) return null;
		return n.key;
	}
	private Node min(Node n){
		if(n == null) return null;
		if(n.left == null) return n;
		return min(n.left);
	}
	
	public Key max(){
		//largest key
		Node n=max(root);
		if(n == null) return null;
		return n.key;
	}
	private Node max(Node n){
		if(n == null) return null;
		if(n.right == null) return n;
		return max(n.right);
	}
	
	public Key floor(Key key){
		//largest key less than or equal to key
		Node n=floor(root, key);
		if(n == null) return null;
		return n.key;
	}
	private Node floor(Node n, Key key){
		if(n == null) return null;
		int cmp=key.compareTo(n.key);
		if(cmp == 0) return n;
		if(cmp<0) return floor(n.left, key);
		Node fright=floor(n.right, key);
		if(fright != null) return fright;
		return n;
	}
	
	public Key ceiling(Key key){
		//smallest key greater than or equal to key
		Node n=ceiling(root, key);
		if(n == null) return null;
		return n.key;
	}
	private Node ceiling(Node n, Key key){
		if(n == null) return null;
		int cmp=key.compareTo(n.key);
		if(cmp == 0) return n;
		if(cmp>0) return ceiling(n.right, key);
		Node cleft=ceiling(n.left, key);
		if(cleft != null) return cleft;
		return n;
	}
	
	public int rank(Key key){
		//number of keys less than key
		return rank(root, key);
	}
	private int rank(Node n, Key key){
		if(n == null) return 0;
		int cmp=key.compareTo(n.key);
		if(cmp == 0) return size(n.left);
		if(cmp>0) return rank(n.right, key)+1+size(n.left);
		return rank(n.left, key);
	}
	
	public Key select(int k){
		//key of rank k
		Node n=select(root, k);
		if(n == null) return null;
		return n.key;
	}
	private Node select(Node n, int k){
		if(n == null) return null;
		int size=size(n.left);
		if(size == k) return n;
		if(k<size) return select(n.left, k);
		return select(n.right, k-size-1);
	}
	//注意维持树的平衡,树底从3结点或4结点中删除
	public void deleteMin() {
		//delete smallest key
		root=deleteMin(root);
	}
	private Node deleteMin(Node n){
		if(n == null) return null;
		if(n.left == null) {
			if(n.right == null) return null;
			n.right.color=n.color;
			return n.right;
		}
		deleteMin(n.left);
		
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	
	public void deleteMax() {
		//delete largest key
	}
	
	public int size(Key lo, Key hi) {
		//number of keys in [lo..hi]
		return rank(hi)-rank(lo);
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		//keys in [lo..hi], in sorted order
		Queue<Key> queue=new Queue<Key>();
		keys(root, lo, hi, queue);
		return queue;
	}
	private void keys(Node n, Key lo, Key hi, Queue<Key> queue){
		if(n == null) return;
		int cmp1=lo.compareTo(n.key);
		if(cmp1>0) keys(n.right, lo, hi, queue);
		int cmp2=hi.compareTo(n.key);
		if(cmp2<0) keys(n.left, lo, hi, queue);
		keys(n.left, lo, n.key, queue);
		queue.enqueue(n.key);
		keys(n.right, n.key, hi, queue);
	}
	
	public Iterable<Key> keys(){
		//all keys in the table, in sorted order
		return keys(min(), max());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
