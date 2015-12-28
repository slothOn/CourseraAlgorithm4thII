package chapter4;

import chapter1.Queue;
import chapter2.IndexMinPQ;
import edu.princeton.cs.algs4.In;

public class EagerPrimMST {
	private IndexMinPQ<Double> pq;
	//点是否已加入树中
	private boolean[] marked;
	//连接点与树的最短边
	private Edge[] edgeTo;
	private double[] distTo;
	
	public EagerPrimMST(EdgeWeightedGraph G){
		edgeTo= new Edge[G.V()];
		distTo= new double[G.V()];
		marked= new boolean[G.V()];
		pq= new IndexMinPQ<Double>(G.V());
		for(int i=0;i<G.V();i++){
			distTo[i]=Double.MAX_VALUE;
		}
		
		distTo[0]=0.0;
		pq.insert(0, 0.0);
		while(!pq.isEmpty()){
			visit(G,pq.delMin());
		}
	}
	private void visit(EdgeWeightedGraph G, int v){
		marked[v]=true;
		for(Edge edge:G.adj(v)){
			int w= edge.other(v);
			if(marked[w]) continue;
			if(edge.weight()<distTo[w]){
				edgeTo[w]=edge;
				distTo[w]=edge.weight();
				if(pq.contains(w)){
					pq.change(w, distTo[w]);
				}else{
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	
	public Iterable<Edge> edges(){
		Queue<Edge> queue= new Queue<Edge>();
		for(int i=1;i<edgeTo.length;i++){
			queue.enqueue(edgeTo[i]);
		}
		return queue;
	}
	
	public double weight(){
		double rweight=0;
		for(int i=0;i<distTo.length;i++){
			rweight+=distTo[i];
		}
		return rweight;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\tinyEWG.txt");
		EdgeWeightedGraph G= new EdgeWeightedGraph(in);
		EagerPrimMST mst= new EagerPrimMST(G);
		for(Edge edge:mst.edges()){
			System.out.println(edge);
		}
		System.out.printf("weight:%.2f\n",mst.weight());

	}

}
