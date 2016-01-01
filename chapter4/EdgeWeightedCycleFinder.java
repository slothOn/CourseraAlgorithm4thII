package chapter4;

import chapter1.Stack;
import edu.princeton.cs.algs4.BellmanFordSP;
//寻找赋权环
public class EdgeWeightedCycleFinder {
	private boolean[] onStack;
	private Stack<DirectedEdge> cycle;
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	public EdgeWeightedCycleFinder(EdgeWeightedDigraph G){
		// TODO Auto-generated constructor stub
		onStack = new boolean[G.V()];
		marked = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for(int i=0;i<G.V();i++){
			if(!marked[i]){
				dfs(G, i);
			}
		}
	}
	
	private void dfs(EdgeWeightedDigraph G, int s){
		marked[s]= true;
		onStack[s] = true;
		for(DirectedEdge edge:G.adj(s)){
			if(hasCycle()) return;
			int next= edge.to();
			if(!marked[next]){
				edgeTo[next]=edge;
				dfs(G, next);
			}else if(onStack[next]){
				cycle = new Stack<DirectedEdge>();
				DirectedEdge e;
				for(e=edge;e.from()!=next;e=edgeTo[e.from()]){
					cycle.push(e);
				}
				cycle.push(e);
			}
		}
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
	
	public boolean hasCycle(){
		return cycle != null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
