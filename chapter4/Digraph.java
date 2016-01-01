package chapter4;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;


public class Digraph {
	private final int v;
	private int e;
	public Bag<Integer>[] adj;
	public Digraph(int v){
		this.v=v;
		e=0;
		adj=(Bag<Integer>[])new Bag[v];
		for(int i=0;i<v;i++){
			adj[i]=new Bag<Integer>();
		}
	}
	
	public Digraph(In in){
		this(in.readInt());
		int e=in.readInt(),v,w;
		for(int i=0;i<e;i++){
			v=in.readInt();
			w=in.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		e++;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public int V(){
		return v;
	}
	
	public int E(){
		return e;
	}
	
	public Digraph reverse(){
		Digraph dg= new Digraph(v);
		int s;
		for(int i=0;i<v;i++){
			s=i;
			for(int w:adj(i)){
				dg.addEdge(w, s);
			}
		}
		return dg;
	}
	
	public String toString(){
		String str=v+" vertex "+e+" edges\n";
		for(int i=0;i<v;i++){
			str+=i+":";
			for(int j:adj(i)){
				str+=j+",";
			}
			str+="\n";
		}
		return str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
