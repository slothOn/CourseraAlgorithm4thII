package chapter4;


import chapter1.Stack;
import edu.princeton.cs.algs4.In;



public class EdgeWeightedTopologicalOrder {
	Stack<Integer> stack= new Stack<Integer>();
	
	private boolean marked[];
	
	public EdgeWeightedTopologicalOrder(EdgeWeightedDigraph G){
		marked=new boolean[G.V()];
		for(int i=0;i<G.V();i++){
			if(!marked[i]){
				dfs(G,i);
			}
		}
	}
	
	/*
	 * topological order
	 */
	public Iterable<Integer> reversePost(){
		return stack;
	}
	
	private void dfs(EdgeWeightedDigraph G,int s){
		marked[s]=true;
		for(DirectedEdge edge:G.adj(s)){
			int w=edge.to();
			if(!marked[w]){
				dfs(G, w);
			}
		}
		stack.push(s);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EdgeWeightedDigraph G=new EdgeWeightedDigraph(new In("E:\\jwork\\algs4-data\\tinyEWDAG.txt"));
		EdgeWeightedTopologicalOrder order= new EdgeWeightedTopologicalOrder(G);
		for(int i:order.reversePost()){
			System.out.print(i+",");
		}
	}

}
