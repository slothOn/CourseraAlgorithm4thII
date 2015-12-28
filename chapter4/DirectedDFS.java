package chapter4;

public class DirectedDFS {
	boolean[] marked;
	public DirectedDFS(Digraph G, int s){
		marked=new boolean[G.V()];
	    dfs(G, s);
	}
	
	public DirectedDFS(Digraph G, Iterable<Integer> sources){
		marked=new boolean[G.V()];
		for(int s:sources){
			if(!marked[s]){
				dfs(G, s);
			}
		}
	}
	
	private void dfs(Digraph G, int root){
		marked[root]=true;
		for(int i:G.adj(root)){
			if(!marked[i]){
				dfs(G, i);
			}
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
