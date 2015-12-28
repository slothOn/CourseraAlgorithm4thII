package chapter4;

public class GraphProcessing {

	/**
	 * @param args
	 */
	public static int degree(Graph G, int v) {
		// TODO Auto-generated method stub
		int degree=0;
		for(int i:G.adj(v)){
			degree++;
		}
		return degree;
	}
	
	public static int maxDegree(Graph G){
		int max=0;
		for(int i=0;i<G.V();i++){
			if(degree(G,i)>max)
				max=degree(G,i);
		}
		return max;
	}

	public static double avergDegree(Graph G){
		return 2.0*G.E()/G.V();
	}
	
	public static int numberOfSelLoops(Graph G){
		int loop=0;
		for(int i=0;i<G.V();i++){
			for(int j:G.adj(i)){
				if(i==j){
					loop++;
				}
			}
		}
		return loop;
	}
}
