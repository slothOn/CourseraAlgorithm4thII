package chapter4;

import java.util.Scanner;

import chapter1.Stack;

import edu.princeton.cs.introcs.In;

public class DfsPaths {
	private boolean marked[];
	private int paths[];
	private final int s;
	public DfsPaths(Graph G, int s){
		this.s=s;
		marked= new boolean[G.V()];
		paths= new int[G.V()];
		for (int i=0;i<G.V();i++){
			marked[i]=false;
			paths[i]=-1;
		}
		dfs(G, s);
	}
	private void dfs(Graph G, int root){
		marked[root]=true;
		for(int i:G.adj(root)){
			if(!marked[i]){
				paths[i]=root;
				dfs(G,i);
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> stack= new Stack<Integer>();
		for(int i=v;i!=s;i=paths[i]){
			stack.push(i);
		}
		stack.push(s);
		return stack;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph G=new Graph(new In("E:\\jwork\\algs4-data\\tinyCG.txt"));
		Scanner scan=new Scanner(System.in);
		int s=scan.nextInt();
		DfsPaths dfs=new DfsPaths(G,s);
		for(int v=0;v<G.V();v++){
			System.out.println(s+" to "+v+":");
			if(dfs.hasPathTo(v)){
				for(int x:dfs.pathTo(v)){
					if(x==s) System.out.print(x);
					else System.out.print("-"+x);
				}
				System.out.println();
			}
			
		}
	}

}
