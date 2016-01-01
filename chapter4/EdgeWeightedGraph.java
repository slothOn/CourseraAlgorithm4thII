package chapter4;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge>[] adjs;

	public EdgeWeightedGraph(int V){
		this.V=V;
		this.E=0;
		adjs= (Bag<Edge>[])new Bag[V];
		for(int i=0;i<V;i++){
			adjs[i]= new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(In in) {
		// TODO Auto-generated constructor stub
		this(in.readInt());
		int e= in.readInt(),a,b;
		double weight;
		Edge edge;
		for(int i=0;i<e;i++){
			a=in.readInt();
			b=in.readInt();
			weight=in.readDouble();
			edge= new Edge(a, b, weight);
			addEdge(edge);
			E++;
		}
	}
	
	public void addEdge(Edge e){
		int v=e.either();
		adjs[v].add(e);
		int w=e.other(v);
		adjs[w].add(e);
	}
	
	public Iterable<Edge> edges(){
		Bag<Edge> b= new Bag<Edge>();
		for(int i=0;i<V;i++){
			for(Edge edge:adjs[i]){
				if(i>edge.other(i))
					b.add(edge);
			}
		}
		return b;
	}
	
	public Iterable<Edge> adj(int v){
		return adjs[v];
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
