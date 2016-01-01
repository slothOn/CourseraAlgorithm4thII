package chapter6;

import chapter1.Queue;
import edu.princeton.cs.algs4.In;

public class FordFulkerson {
	private boolean[] marked;//起点出发的非增广路径可达处
	private FlowEdge[] edgeTo;//起点到终点的非增广路径
	private double value;
	public FordFulkerson(FlowNetwork G, int s, int t) {
		// TODO Auto-generated constructor stub
		marked= new boolean[G.V()];
		edgeTo= new FlowEdge[G.V()];
		//initialize value
		value=0;
		while(hasAugumentPath(G, s, t)){
			
			double bottleneck =Double.POSITIVE_INFINITY;
			
			for(int i=t;i!=s;i=edgeTo[i].other(i)){
				if(edgeTo[i].residualCapacityTo(i)<bottleneck){
					bottleneck=edgeTo[i].residualCapacityTo(i);
				}
			}
			
			for(int i=t;i!=s;i=edgeTo[i].other(i)){
				edgeTo[i].addResidualFlowTo(i, bottleneck);
			}
			value+=bottleneck;
		}
	}
	
	public double value(){
		return value;
	}
	
	private boolean hasAugumentPath(FlowNetwork G, int s, int t){
		Queue<Integer> queue= new Queue<Integer>();
		marked= new boolean[G.V()];
		marked[s]=true;
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int next= queue.dequeue();
			for(FlowEdge edge:G.adj(next)){
				if(edge.residualCapacityTo(edge.other(next))>0){
					if(!marked[edge.other(next)]){
						marked[edge.other(next)]=true;
						edgeTo[edge.other(next)]=edge;
						queue.enqueue(edge.other(next));
					}
				}
			}
		}
		return marked[t];
	}
	
	public boolean inCut(int v){
		return marked[v];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlowNetwork G= new FlowNetwork(new In("E:\\jwork\\algs4-data\\tinyFN.txt"));
		int s=0, t=G.V()-1;
		FordFulkerson maxflow= new FordFulkerson(G, s, t);
		System.out.println("Max flow from "+s+" to "+t);
		for(int v=0;v<G.V();v++){
			for(FlowEdge edge:G.adj(v)){
				if(v==edge.from()&&edge.flow()>0){
					System.out.println(edge);
				}
			}
		}
		System.out.println("Max flow value is "+maxflow.value());
	}

}
