package chapter4;

import chapter1.Queue;
import chapter1.UnionFind;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class KruscalMST {

	Queue<Edge> mst= new Queue<Edge>();
	
	public KruscalMST(EdgeWeightedGraph G){
		MinPQ<Edge> pq=new MinPQ<Edge>();
		for(Edge edge:G.edges()){
			pq.insert(edge);
		}

		UnionFind uf= new UnionFind(G.V());
		while(!pq.isEmpty()&&mst.size()<G.V()-1){
			Edge edge= pq.delMin(); 
			int v=edge.either();
			int w=edge.other(v);
			if(!uf.connected(v, w)){
				uf.union(v, w);
				mst.enqueue(edge);
			}
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
		In in= new In("E:\\jwork\\algs4-data\\tinyEWG.txt");
		EdgeWeightedGraph G= new EdgeWeightedGraph(in);
		KruscalMST mst= new KruscalMST(G);
		for(Edge edge:mst.edges()){
			System.out.println(edge);
		}
		System.out.printf("weight:%.2f\n",mst.weight());
	}

}
