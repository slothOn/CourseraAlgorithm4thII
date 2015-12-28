package chapter5;

import chapter1.Queue;
import edu.princeton.cs.algs4.In;

//单词查找树, 和MSD的栈对应
public class StringST<Value> {
	private static int R;
	private Node root;
	private static class Node{
		private Object val;//java不支持泛型数组
		private Node[] next = new Node[R];
	}
	public StringST(){
		R=256;//extended Ascii
		root = new Node();
	}
	
	public void put(String key, Value val){
		root = put(root, key, val, 0);
	}
	private Node put(Node n, String key, Value val, int d){
		if(n==null) n=new Node();
		if(d==key.length()){
			n.val=val; return n;
		}
		n.next[key.charAt(d)]=put(n.next[key.charAt(d)], key, val, d+1);
		return n;
	}
	
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x==null) return null;
		return (Value)x.val;
	}
	private Node get(Node n, String key, int d){
		if(n==null) return null;
		if(d==key.length()) return n;
		return get(n.next[key.charAt(d)], key, d+1);
	}
	
	public void delete(String key){
		root= delete(root, key, 0);
	}
	private Node delete(Node n, String key, int d){
		if(n==null) return null;
		if(d==key.length()) {
			n.val=null;
		}else{
			n.next[key.charAt(d)]= delete(n.next[key.charAt(d)], key, d+1);
		}
		if(n.val!=null) return n;
		for(int i=0;i<R;i++){
			if(n.next[i]!=null) return n;
		}
		//无值且子全为空，删该结点
		return null;
	}
	
	public boolean contains(String key){
		return get(key)!=null;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	//s的前缀中最长的键
	public String longestPrefixOf(String s){
		int len= search(root, s, 0, 0);
		return s.substring(0, len);
	}
	private int search(Node n, String s, int d, int len){
		if(n==null) return len;
		if(n.val!=null) len =d;
		if(s.length()==d) return len;
		return search(n.next[s.charAt(d)], s, d+1, len);
	}
	
	//所有以s为前缀的键
	public Iterable<String> keysWithPrefix(String pre){
		Node n=get(root, pre, 0);
		Queue<String> q =new Queue<String>();
		collect(n, pre, q);
		return q;
	}
	private void collect(Node n, String ts, Queue<String> q){
		if(n==null) return;
		if(n.val!=null) q.enqueue(ts);
		for(char i=0;i<R;i++){
			collect(n, ts+i, q);
		}
	}
	
	//所有和s匹配的键, 包含通配符
	public Iterable<String> keysThatMatch(String pat){
		 Queue<String> q= new Queue<String>();
		collect(root, "", pat, q); 
		 return q;
	}
	private void collect(Node n, String ts, String pat, Queue<String > q){
		if(n==null) return;
		if(ts.length()==pat.length()&&n.val!= null) q.enqueue(ts);
		if(ts.length()==pat.length())  return;
		char c= pat.charAt(ts.length());
		if(c=='.'){
			for(char i=0;i<R;i++){
				collect(n.next[i], ts+i, pat, q);
			}
		}else{
			collect(n.next[c], ts+c, pat, q);
		}
	}
	
	public int size(){
		return size(root);
	}
	private int size(Node n){
		if(n==null) return 0;
		int count=0;
		if(n.val!= null) count++;
		for(int i=0;i<R;i++){
			count+=size(n.next[i]);
		}
		return count;
	}
	
	public Iterable<String> keys(){
		return keysWithPrefix("");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path2="/media/zxc/Personal Affairs/jwork/algs4-data/msdtest.txt";
		In in = new In(path2);
		String[] lines = in.readAllLines();
		
		StringST<Integer> st= new StringST<Integer>();
		for(int i=0;i<lines.length;i++){
			st.put(lines[i], i);
		}
		System.out.println(st.get("sea"));
		
	}

}
