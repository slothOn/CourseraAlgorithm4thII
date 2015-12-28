package chapter4;

import chapter1.Stack;
import edu.princeton.cs.algs4.In;
//无环图中寻找最长路径，允许权值为负
public class AcyclicLP {
	private final static double INFINITY= Double.MIN_VALUE;
	private int s;
	private double distTo[];
	private DirectedEdge edgeTo[];
	
	
	public AcyclicLP(EdgeWeightedDigraph G,int s){
		this.s=s;
		distTo= new double[G.V()];
		for(int i=0;i<distTo.length;i++){
			distTo[i]=INFINITY;
		}
		edgeTo= new DirectedEdge[G.V()];
		EdgeWeightedTopologicalOrder order= new EdgeWeightedTopologicalOrder(G);
		int count=0;
		int kb=Integer.MAX_VALUE;
		edgeTo[s]=null; distTo[s]=0;/* initialize*/
		for(int i:order.reversePost()){
			if(i==s){
				kb=count;
			}
			if(count<kb){
				count++; continue;
			}
			relax(G, i);
		}
		
	}
	
	private void relax(EdgeWeightedDigraph G,int v){
		for(DirectedEdge edge:G.adj(v)){
			int w=edge.to();
			if(distTo[w]<distTo[v]+edge.weight()){
				distTo[w]= distTo[v]+edge.weight();
				edgeTo[w]=edge;
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
		In in= new In("E:\\jwork\\algs4-data\\tinyEWDAG.txt");
		EdgeWeightedDigraph G=new EdgeWeightedDigraph(in); 
		int s=5;
		AcyclicLP sp= new AcyclicLP(G, s);
		for(int i=0;i<G.V();i++){
			System.out.printf(s+"-"+i+":%.2f\n", sp.distTo(i));
			for(DirectedEdge edge:sp.pathTo(i)){
				System.out.print(edge+" ");
			}
			System.out.println();
		}
	}

}
