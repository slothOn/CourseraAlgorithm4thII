package chapter4;

import java.util.Scanner;

import chapter1.Queue;
import chapter1.Stack;

import edu.princeton.cs.introcs.In;

public class BfsPathsRecur {
	private boolean marked[];
	private int paths[];
	private final int s;
	Queue<Integer> queue= new Queue<Integer>();
	public BfsPathsRecur(Graph G, int s){
		this.s=s;
		marked= new boolean[G.V()];
		paths= new int[G.V()];
		for(int i=0;i<G.V();i++){
			marked[i]=false;
			paths[i]=-1;
		}
		marked[s]=true;
		bfs(G,s);
	}
	
	private void bfs(Graph G, int s){
		for(int i:G.adj(s)){
			if(!marked[i]){
				marked[i]=true;
				paths[i]=s;
				queue.enqueue(i);
			}
		}
		if(queue.isEmpty()) return;
		else{
			int next=queue.dequeue();
			bfs(G,next);
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
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
		BfsPathsRecur dfs=new BfsPathsRecur(G,s);
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
