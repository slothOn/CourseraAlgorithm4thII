package chapter4;

import java.util.Scanner;

import chapter1.Stack;
import edu.princeton.cs.algs4.In;


public class DepthFirstOrder {
	Stack<Integer> stack= new Stack<Integer>();
	private boolean marked[];
	public DepthFirstOrder(Digraph G){
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
	
	private void dfs(Digraph G, int s){
		marked[s]=true;
		for(int i:G.adj(s)){
			if(!marked[i]){
				dfs(G,i);
			}
		}
		stack.push(s);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Digraph G=new Digraph(new In("E:\\jwork\\algs4-data\\tinyCG.txt"));
		DepthFirstOrder order= new DepthFirstOrder(G);
		for(int i:order.reversePost()){
			System.out.print(i+",");
		}
	}

}
