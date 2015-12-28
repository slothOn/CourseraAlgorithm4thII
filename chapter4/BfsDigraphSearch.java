package chapter4;

import java.util.Scanner;

import chapter1.Queue;
import chapter1.Stack;
import edu.princeton.cs.algs4.In;


public class BfsDigraphSearch {
	private final int root;
	private boolean marked[];
	private int paths[];
	public BfsDigraphSearch(Digraph G, int s) {
		// TODO Auto-generated constructor stub
		root=s;
		paths=new int[G.V()];
		marked=new boolean[G.V()];
		
		Queue<Integer> queue=new Queue<Integer>();
		marked[root]=true;
		queue.enqueue(root);
		while(!queue.isEmpty()){
			int next=queue.dequeue();
			for(int i:G.adj(next)){
				if(!marked[i]){
					marked[i]=true;
					paths[i]=next;
					queue.enqueue(i);
				}
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
		BfsDigraphSearch dfs=new BfsDigraphSearch(G,s);
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
