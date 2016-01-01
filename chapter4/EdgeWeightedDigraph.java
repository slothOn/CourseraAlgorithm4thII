package chapter4;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private Bag<DirectedEdge>[] adjs;

	public EdgeWeightedDigraph(int V){
		this.V=V;
		this.E=0;
		adjs= (Bag<DirectedEdge>[])new Bag[V];
		for(int i=0;i<V;i++){
			adjs[i]= new Bag<DirectedEdge>();
		}
	}
	
	public EdgeWeightedDigraph(In in) {
		// TODO Auto-generated constructor stub
		this(in.readInt());
		int e= in.readInt(),a,b;
		double weight;
		DirectedEdge edge;
		for(int i=0;i<e;i++){
			a=in.readInt();
			b=in.readInt();
			weight=in.readDouble();
			edge= new DirectedEdge(a, b, weight);
			addEdge(edge);
		}
	}
	
	

	public void addEdge(DirectedEdge e){
		adjs[e.from()].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> b= new Bag<DirectedEdge>();
		for(int i=0;i<V;i++){
			for(DirectedEdge edge:adjs[i]){
				b.add(edge);
			}
		}
		return b;
	}
	
	public Iterable<DirectedEdge> adj(int v){
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
