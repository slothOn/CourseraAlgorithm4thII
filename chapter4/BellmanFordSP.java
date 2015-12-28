package chapter4;
//Queue based,坑待填,寻找的似乎仅是环而非负权环
import chapter1.Queue;
import chapter1.Stack;

//负权且有环图（负权重环无解）
public class BellmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private Queue<Integer> queue;
	private Stack<DirectedEdge> cycle;
	private boolean[] onQ;//队列中不重复入队
	private int cost=0;//relax次数
	public BellmanFordSP(EdgeWeightedDigraph G, int s){
		distTo= new double[G.V()];
		edgeTo= new DirectedEdge[G.V()];
		queue= new Queue<Integer>();
		for(int i=0;i<distTo.length;i++){
			distTo[i]=Double.MAX_VALUE;
		}
		distTo[s]= 0; edgeTo[s]= null;
		queue.enqueue(s);
		while(!queue.isEmpty()&&!hasNegativeCycle()){
			int next = queue.dequeue();
			onQ[next] = false;
			relax(G, next);
		}
	}
	
	private void relax(EdgeWeightedDigraph G, int v){
		for(DirectedEdge edge:G.adj(v)){
			int w= edge.to();
			if(distTo[w]>distTo[v]+edge.weight()){
				distTo[w]=distTo[v]+edge.weight();
				edgeTo[w]=edge;
				if(!onQ[w])	{
					queue.enqueue(w);
					onQ[w] = true;
				}
			}
		}
		cost++;
		if(cost%G.V() == 0){
			findNegativeCycle();
			if(hasNegativeCycle())	return;
		}
	}
	
	public double distTo(int v){
		return distTo[v];
	}
	
	public boolean hasPathTo(int v){
		if(distTo[v]==Double.MAX_VALUE){
			return false;
		}
		return true;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		Stack<DirectedEdge> stack = new Stack<DirectedEdge>();
		for(DirectedEdge e=edgeTo[v];e!=null;v=e.from()){
			stack.push(e);
		}
		return stack;
	}	
	//负权重环检测
	public void findNegativeCycle(){
		int V= edgeTo.length;
		EdgeWeightedDigraph EWD= new EdgeWeightedDigraph(V);
		for(int i=0;i<V;i++){
			if(edgeTo[i]!=null){
				EWD.addEdge(edgeTo[i]);
			}
		}
		EdgeWeightedCycleFinder cf= new EdgeWeightedCycleFinder(EWD);
		cycle= cf.cycle();
	}
	
	public boolean hasNegativeCycle(){
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> negativeCycle(){
		return cycle;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
