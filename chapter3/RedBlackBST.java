package chapter3;
//perfect balance所有null到root的距离相等
import chapter1.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
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
	public RedBlackBST(){
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
		int cmp=key.compareTo(n.key);
		if(cmp == 0) n.val=val;
		else if(cmp > 0) n.right=put(n.right, key, val);
		else n.left=put(n.left, key, val);
		/*
		if(!isRed(n.left) && isRed(n.right)){n=rotateLeft(n);}
		if(isRed(n.left) && isRed(n.left.left)){n=rotateRight(n);}
		if(isRed(n.left) && isRed(n.right)){flipColors(n);}
		n.count=size(n.left)+size(n.right)+1;
		return n;
		*/
		return balance(n);
	}
	//左转右
	private Node rotateRight(Node n){
		Node m=n.left;
		n.left=m.right;
		m.right=n;
		m.color=n.color;
		n.color=RED;
		return m;
	}
	//右转左
	private Node rotateLeft(Node n){
		Node m=n.right;
		n.right=m.left;
		m.left=n;
		m.color=n.color;
		n.color=RED;
		return m;
	}
	private void flipColors(Node n){
		if(n.left != null) n.left.color=!n.left.color;
		if(n.right != null) n.right.color=!n.right.color;
		n.color=!n.color;
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
		if(!contains(key)) return;
		if(isEmpty()) return;
		if(!isRed(root.left) && !isRed(root.right)){
			root.color=RED;
		}
		root=delete(root, key);
		if(root != null) root.color=BLACK;
	}
	private Node delete(Node n, Key key){
		int cmp=key.compareTo(n.key);
		if(cmp<0){
			if(!isRed(n.left) && !isRed(n.left.left))
				n=moveRedLeft(n);
			n.left=delete(n.left, key);
		}else{
			if(isRed(n.left)) n=rotateRight(n);
			if(key.compareTo(n.key) == 0 && n.right == null){
				return null;
			}
			if(!isRed(n.right) && !isRed(n.right.left))
				n=moveRedRight(n);
			if(key.compareTo(n.key) == 0){
				n.key=min(n.right).key;
				n.val=min(n.right).val;
				n.right=deleteMin(n.right);
			}else{
				n.right=delete(n.right, key);
			}
		}
		return balance(n);
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
	//注意维持树的平衡,树底从3结点或4结点中删除,左子结点变为3结点或4结点,root处情况需特别考虑,
	//目标是路径上经过的所有left结点全部不为2结点
	public void deleteMin() {
		//delete smallest key
		if(isEmpty()) return;
		if(!isRed(root.left) && !isRed(root.right)) root.color=RED;
		root=deleteMin(root);
		if(root != null) root.color=BLACK;
	}
	private Node deleteMin(Node n){
		if(n.left == null) return null;
		if(!isRed(n.left) && !isRed(n.left.left))
			n=moveRedLeft(n);
		n.left=deleteMin(n.left);
		//配平
		return balance(n);
	}
	private Node moveRedLeft(Node n){
		flipColors(n);
		//z左往右移
		if(isRed(n.right.left)){
			n.right=rotateRight(n.right);
			n=rotateLeft(n);
			flipColors(n);//原书中没有这一行，但自己画图后感觉补上的做法更清晰
		}
		return n;
	}
	private Node balance(Node n){
		if(!isRed(n.left) && isRed(n.right)){n=rotateLeft(n);}//原书写法：if(isRed(n.right)){n=rotateLeft(n);}感觉是对应上面缺少flipcolors的写法
		if(isRed(n.left) && isRed(n.left.left)){n=rotateRight(n);}
		if(isRed(n.left) && isRed(n.right)){flipColors(n);}
		n.count=size(n.left)+size(n.right)+1;
		return n;
	}
	public void deleteMax() {
		//delete largest key
		if(isEmpty()) return;
		if(!isRed(root.left) && !isRed(root.right)) 
			root.color=RED;
		root=deleteMax(root);
		if(root != null) root.color=BLACK;
	}
	//思路是先把所有右路径上的2-3链接转成right leaning，之后同deleteMin
	private Node deleteMax(Node n){
		if(isRed(n.left)) n=rotateRight(n);
		if(n.right == null) return null;
		if(!isRed(n.right) && !isRed(n.right.left)){
			n=moveRedRight(n);
		}
		n.right=deleteMax(n.right);
		//配平
		return balance(n);
	}
	private Node moveRedRight(Node n){
		flipColors(n);
		if(isRed(n.left.left)){//和原书不同
			n=rotateRight(n);
			flipColors(n); //和原书不同
		}
		return n;
	}
	
	public int size(Key lo, Key hi) {
		//number of keys in [lo..hi]
		if(contains(hi)) return rank(hi)-rank(lo)+1;
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
	
	public Iterable<Key> keys(){
		//all keys in the table, in sorted order
		return keys(min(), max());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackBST<String,Integer> st;
		st=new RedBlackBST<String,Integer>();
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
