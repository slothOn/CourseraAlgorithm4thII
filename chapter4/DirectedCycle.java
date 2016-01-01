package chapter4;

import chapter1.Stack;
import edu.princeton.cs.algs4.In;
//有向环的检测,只能返回首次检测到的环？
public class DirectedCycle {
	private boolean[] onstack;
	private Stack<Integer> cycle;
	private boolean[] marked;
	private int[] edgeTo;
	public DirectedCycle(Digraph G){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onstack = new boolean[G.V()];
		for(int i=0;i<G.V();i++){
			if(!marked[i]){
				dfs(G, i);
			}
		}
	}
	
	private void dfs(Digraph G, int i){
		marked[i] = true;
		onstack[i] = true;
		for(int w:G.adj(i)){
			if(hasCycle()) return;
			else if(!marked[w]){
				edgeTo[w] = i;
				dfs(G, w);
			}else if(onstack[w]){
				cycle = new Stack<Integer>();
				for(int j=i;j!=w;j=edgeTo[j]){
					cycle.push(j);
				}
				cycle.push(w);
				cycle.push(i);
			}
		}
	}
	public boolean hasCycle(){
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In input = new In("E:\\jwork\\algs4-data\\DirectedCircle.txt");
		Digraph G = new Digraph(input);
		DirectedCycle c = new DirectedCycle(G);
		if(c.hasCycle()){
			for(int i:c.cycle){
				System.out.print(i+",");
			}
		}
	}

}
