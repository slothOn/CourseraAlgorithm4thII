package chapter3;

import chapter1.Queue;

public class IntervalST<Key extends Comparable<Key>, Value> {
	private class Node{
		Key key;
		Key key2;
		Key maxall;
		Value val;
		Node left, right;
		public Node(Key key, Key key2, Key maxall, Value val) {
			// TODO Auto-generated constructor stub
			this.key=key; this.key2=key2; this.maxall=maxall; this.val=val;
		}
	}
	Node root;
	public IntervalST(){
		//create interval search tree
		root=null;
	}
	
	public void put(Key lo, Key hi, Value val){
		//put interval-value pair into ST
		root=put(root, lo, hi, val);
	}
	private Node put(Node n, Key lo, Key hi, Value val){
		if(n == null) return new Node(lo, hi, hi, val);
		int cmp=lo.compareTo(n.key);
		if(cmp<0) n.left=put(n.left, lo, hi, val);
		else n.right=put(n.right, lo, hi, val);
		return balance(n);
	}
	
	public Value get(Key lo, Key hi){
		//value paired with given interval
		Node n=root;
		while(n != null){
			int cmp=lo.compareTo(n.key);
			if(cmp == 0) return n.val;
			else if(cmp<0) n=n.left;
			else n=n.right;
		}
		return null;
	}
	
	public void delete(Key lo, Key hi){
		//delete the given interval
		root=delete(root, lo, hi);
	}
	private Node delete(Node n, Key lo, Key hi){
		if(n == null) return null;
		int cmp=n.key.compareTo(lo);
		if(cmp == 0){
			if(n.right == null) return n.left;
			Node mid=min(n.right);
			n.right=deleteMin(n.right);
			n.key=mid.key;
			n.val=mid.val;
		}else if(cmp<0){
			n.left=delete(n.left, lo, hi);
		}else{
			n.right=delete(n.right, lo, hi);
		}
		return balance(n);
	}
	
	private Node balance(Node n){
		if(n == null) return null;
		if(n.left != null && (n.right == null || n.left.maxall.compareTo(n.right.maxall)>0)) n.maxall=n.left.maxall;
		else n.maxall=n.right.maxall;
		return n;
	}
	
	private Node deleteMin(Node n){
		if(n == null) return null;
		if(n.left == null) return n.right;
		n.left=deleteMin(n.left);
		return balance(n);
	}
	
	private Node min(Node n){
		if(n == null) return null;
		if(n.left == null) return n;
		return min(n.left);
	}
	
	public Value intersectsAny(Key lo, Key hi){
		Node n=intersects(root, lo, hi);
		return (n == null) ? null : n.val;
	}
	private Node intersects(Node n, Key lo, Key hi){
		if(n == null) return null;
		if(!(n.key.compareTo(hi)>0) && !(n.key2.compareTo(lo)<0)) return n; 
		int cmp=lo.compareTo(n.maxall);
		if(cmp>0) return null;
		if(n.left == null || lo.compareTo(n.left.maxall)>0) return intersects(n.right, lo, hi);
		else return intersects(n.left, lo, hi);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntervalST<Integer, String> ist=new IntervalST<Integer, String>();
		ist.put(4, 8, "A");
		ist.put(7, 10, "B");
		ist.put(17, 19, "C");
		ist.put(15, 18, "D");
		ist.put(21, 24, "E");
		ist.put(5, 8, "F");
		System.out.println(ist.intersectsAny(9, 16));
	}

}
