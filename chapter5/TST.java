package chapter5;

import edu.princeton.cs.algs4.In;

//三向单词查找树, 只实现了get和put方法
public class TST<Value> {
	private static class Node{
		char c;
		Object val;
		Node left, mid, right;
	}
	private Node root;
	
	public void put(String key, Value val){
		root = put(root, key, val ,0);
	}
	private Node put(Node n, String key, Value val, int d){
		if(n==null) {
			n= new Node();
			n.c= key.charAt(d);
		}
        if(n.c==key.charAt(d)){
        	if(key.length()-1==d){
        		n.val = val;
        	}else
        		n.mid = put(n.mid, key, val, d+1);
        }else if(key.charAt(d)<n.c){
        	n.left = put(n.left, key, val, d);
        }else{
        	n.right = put(n.right, key, val, d);
        }
        return n;
	}																																																																																																																																								
	
	public Value get(String key){
		Node n= get(root, key, 0);
		if(n==null) return null;
		return (Value)n.val;
	}
	private Node get(Node n, String key, int d){
		if(n==null) return null;
		//和当前n比较
		char ct= key.charAt(d);
		if(ct==n.c){ 
			if(d==key.length()-1) return n;
			else	return get(n.mid, key, d+1);
		}else if(ct<n.c){ 
			return get(n.left, key, d);
		}else return get(n.right, key, d);
	}
	//S的前缀中最长的键, LZW用到
	public String longestPrefixOf(String s){
		int len= search(root, s, 0);
		return s.substring(0, len);
	}
	private int search(Node n, String s, int d){
		if(n==null||d==s.length()) return d;
		char c=s.charAt(d);
		if(c==n.c)	return search(n.mid, s, d+1);
		if(c<n.c) return search(n.left, s, d);
		return search(n.right, s, d);
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path2="/media/zxc/Personal Affairs/jwork/algs4-data/msdtest.txt";
		In in = new In(path2);
		String[] lines = in.readAllLines();
		
		TST<Integer> st= new TST<Integer>();
		for(int i=0;i<lines.length;i++){
			st.put(lines[i], i);
		}
		System.out.println(st.longestPrefixOf("seashkkkkkkkkkkkkkk"));
	}

}
