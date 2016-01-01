package chapter4;

import chapter1.Bag;
import edu.princeton.cs.introcs.In;

public class Graph {
	private final int v;
	private int e;
	public Bag<Integer>[] adj;
	public Graph(int v){
		this.v= v;
		e=0;
		adj= (Bag<Integer>[]) new Bag[v];
		for(int i=0;i<adj.length;i++){
			adj[i]=new Bag<Integer>();
		}
	}
	
	public Graph(In in){
		this(in.readInt());
		int e=in.readInt();
		for(int i=0;i<e;i++){
			int v= in.readInt();
			int w= in.readInt();
			addEdge(v, w);
		}
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
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
	
	public String toString(){
		String str=v+" Vertix "+e+" Edges\n";
		for(int i=0; i<v; i++){
			str+=e+": ";
			for(int j: adj(v)){
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
		Graph G=new Graph(new In("E:\\jwork\\algs4-data\\tinyCG.txt"));
		System.out.println(G.toString());
	}

}
