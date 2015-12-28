package chapter4;
//计算强连通分支
import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class KosarajuSharirSCC {
	private int[] id;
	private boolean[] marked;
	private int count;
	public KosarajuSharirSCC(Digraph G){
		id=new int[G.V()];
		marked=new boolean[G.V()];
		count=0;
		DepthFirstOrder topoorder=new DepthFirstOrder(G.reverse());
		for(int i:topoorder.stack){
			if(!marked[i]){
				dfs(G,i);
				count++;
			}
		}
	}
	
	private void dfs(Digraph G,int root){
		marked[root]=true;
		id[root]=count;
		for(int i:G.adj(root)){
			if(!marked[i]){
				dfs(G,i);
			}
		}
	}
	
	public boolean stronglyConnected(int v,int w){
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
		In in= new In("E:\\jwork\\algs4-data\\tinyDG.txt");
		Digraph G=new Digraph(in);
		KosarajuSharirSCC scc=new KosarajuSharirSCC(G);
		int count=scc.count(); 
		System.out.println(count+" components");
		Bag<Integer>[] bags=(Bag<Integer>[])new Bag[count];
		for(int i=0;i<bags.length;i++){
			bags[i]=new Bag<Integer>();
		}
		for(int i=0;i<G.V();i++){
			bags[scc.id(i)].add(i);
		}
		for(int i=0;i<count;i++){
			for(int j:bags[i]){
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}

}
