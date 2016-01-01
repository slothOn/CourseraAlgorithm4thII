package chapter4;

import java.util.Scanner;

import chapter1.Stack;
import edu.princeton.cs.algs4.In;


public class DfsDigraphSearch {
	private final int root;
	private boolean marked[];
	private int paths[];
	public DfsDigraphSearch(Digraph G, int s) {
		// TODO Auto-generated constructor stub
		root=s;
		paths=new int[G.V()];
		marked=new boolean[G.V()];
		dfs(G,root);
	}
	
	private void dfs(Digraph G, int s){
		marked[s]=true;
		for(int i:G.adj(s)){
			if(!marked[i]){
				paths[i]=s;
				dfs(G, i);
			}
		}
	}
	
	public boolean visited(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!visited(v)) return null;
		Stack<Integer> stack=new Stack<Integer>();
		for(int i=v;i!=root;i=paths[i])
			stack.push(i);
		stack.push(root);
		return stack;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Digraph G=new Digraph(new In("E:\\jwork\\algs4-data\\tinyCG.txt"));
		Scanner scan=new Scanner(System.in);
		int s=scan.nextInt();
		DfsDigraphSearch dfs=new DfsDigraphSearch(G,s);
		for(int v=0;v<G.V();v++){
			System.out.println(s+" to "+v+":");
			if(dfs.visited(v)){
				for(int x:dfs.pathTo(v)){
					if(x==s) System.out.print(x);
					else System.out.print("-"+x);
				}
				System.out.println();
			}
			
		}
	}

}
