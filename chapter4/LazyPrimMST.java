package chapter4;

import chapter1.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class LazyPrimMST {
	private boolean marked[];
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	public LazyPrimMST(EdgeWeightedGraph G){
		marked= new boolean[G.V()];
		pq= new MinPQ<Edge>();
		mst= new Queue<Edge>();
		visit(G, 0);
		while(!pq.isEmpty()&&mst.size()<G.V()-1){
			Edge next= pq.delMin();
			int v=next.either();
			int w=next.other(v);
			if(marked[v]&&marked[w])
				continue;
			mst.enqueue(next);
			if(!marked[w]) visit(G, w);
			if(!marked[v]) visit(G, v);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v){
		marked[v]= true;
		for(Edge eedge:G.adj(v)){
			if(!marked[eedge.other(v)])
				pq.insert(eedge);
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		double weight=0.0;
		for(Edge edge:mst){
			weight+=edge.weight();
		}
		return weight;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\mediumEWG.txt");
		EdgeWeightedGraph G= new EdgeWeightedGraph(in);
		LazyPrimMST mst= new LazyPrimMST(G);
		for(Edge edge:mst.edges()){
			System.out.println(edge);
		}
		System.out.printf("weight:%.2f\n",mst.weight());
	}

}
