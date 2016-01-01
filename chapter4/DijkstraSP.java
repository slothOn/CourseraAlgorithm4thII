package chapter4;

import chapter1.Stack;
import chapter2.IndexMinPQ;
import edu.princeton.cs.algs4.In;

public class DijkstraSP {
	private final static double INFINITY= Double.MAX_VALUE;
	private int s;
	private double distTo[];
	private DirectedEdge edgeTo[];
	private IndexMinPQ<Double> pq;
	public DijkstraSP(EdgeWeightedDigraph G,int s){
		this.s=s;
		distTo= new double[G.V()];
		for(int i=0;i<distTo.length;i++){
			distTo[i]=INFINITY;
		}
		edgeTo= new DirectedEdge[G.V()];
		pq= new IndexMinPQ<Double>(G.V());
		edgeTo[s]=null;distTo[s]=0;
		pq.insert(s, (double) 0);
		while(!pq.isEmpty()){
			int next=pq.delMin();
			relax(G, next);
		}
	}
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge edge:G.adj(v)){
			int w=edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){
				distTo[w]= distTo[v]+edge.weight();
				edgeTo[w]=edge;
				if(!pq.contains(w)) pq.insert(w, distTo[w]);
				else pq.change(w, distTo[w]);
			}
		}
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		Stack<DirectedEdge> stack= new Stack<DirectedEdge>();
		for(DirectedEdge edge=edgeTo[v];edge!=null;edge=edgeTo[edge.from()]){
			stack.push(edge);
		}
		return stack;
	}
	
	public boolean hasPathTo(int v){
		if(distTo[v]==INFINITY){
			return false;
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\tinyEWD.txt");
		EdgeWeightedDigraph G=new EdgeWeightedDigraph(in); 
		int s=0;
		DijkstraSP sp= new DijkstraSP(G, s);
		for(int i=0;i<G.V();i++){
			System.out.printf(s+"-"+i+":%.2f\n", sp.distTo(i));
			for(DirectedEdge edge:sp.pathTo(i)){
				System.out.print(edge+" ");
			}
			System.out.println();
		}
	}

}
