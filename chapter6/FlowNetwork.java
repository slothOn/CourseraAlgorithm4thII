package chapter6;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;
//增广路径算法
public class FlowNetwork {
	private int V;
	private int E;
	private Bag<FlowEdge>[] edges; 
	public FlowNetwork(int V){
		this.V= V;
		this.E=0;
		edges= (Bag<FlowEdge>[])new Bag[V];
		for(int i=0;i<edges.length;i++){
			edges[i]= new Bag<FlowEdge>();
		}
	}
	
	public FlowNetwork(In in){
		this(in.readInt());
		int edgenum= in.readInt();
		for(int i=0;i<edgenum;i++){
			int a= in.readInt(); int b= in.readInt(); double f= in.readDouble();
			addEdge((new FlowEdge(a, b, f)));
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(FlowEdge e){
		int a= e.from();
		int b= e.to();
		edges[a].add(e);
		edges[b].add(e);
		E++;
	}
	
	public Iterable<FlowEdge> adj(int v){
		return edges[v];
	}
	
	public Iterable<FlowEdge> edges(){
		Bag<FlowEdge> bag= new Bag<FlowEdge>();
		for(int i=0;i<edges.length;i++){
			for(FlowEdge edge:edges[i]){
				if(i>edge.other(i)){
					bag.add(edge);
				}
			}
		}
		return bag;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str= "";
		for(int i=0;i<edges.length;i++){
			str+= (i+":");
			for(FlowEdge edge:edges[i]){
				if(edge.from()==i)
					str+= edge+",";
			}
			str+= "\n";
		}
		return str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
