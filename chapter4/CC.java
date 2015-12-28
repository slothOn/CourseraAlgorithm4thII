package chapter4;


import chapter1.Bag;
import edu.princeton.cs.introcs.In;

public class CC {
	private int[] id;
	private int count;
	public CC(Graph G){
		count=0;
		id=new int[G.V()];
		for(int i=0;i<G.V();i++){
			id[i]=-1;
		}
		for(int i=0;i<G.V();i++){
			if(id[i]==-1){
				id[i]=count;
				dfs(G,i);
				count++;
			}
		}
	}
	
	private void dfs(Graph G, int s){
		for(int i:G.adj(s)){
			if(id[i]==-1){
				id[i]=id[s];
				dfs(G,i);
			}
		}
	}
	
	public boolean connected(int v, int w){
		if(id[v]==id[w]){
			return true;
		}
		return false;
	}
	
	public int count(){
		return count;
	}
	
	public int id(int v){
		return id[v];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph G=new Graph(new In("E:\\jwork\\algs4-data\\tinyG.txt"));
		CC cc=new CC(G);
		int M=cc.count();
		System.out.println(M+" components");
		Bag<Integer>[] components=(Bag<Integer>[]) new Bag[M];
		for(int i=0;i<components.length;i++){
			components[i]=new Bag<Integer>();
		}
		for(int i=0;i<G.V();i++){
			components[cc.id(i)].add(i);
		}
		for(int i=0;i<M;i++){
			for(int v:components[i]){
				System.out.print(v+" ");
			}
			System.out.println();
		}
	}

}
