package chapter6;

public class FlowEdge {
	private int v;
	private int w;
	private final double cap;
	private double flow;
	public FlowEdge(int v, int w, double cap){
		this.v=v; this.w=w; this.cap=cap; this.flow=0;
	}
	
	public int from(){
		return v;
	}
	
	public int to(){
		return w;
	}
	
	public int other(int vertex){
		if(v!=vertex) return v;
		else return w;
	}
	
	public double capacity(){
		return cap;
	}
	
	public double flow(){
		return flow;
	}
	
	public double residualCapacityTo(int vertex){
		//到v的剩余流量
		if(vertex==w){
			return cap-flow; 
		}else if(vertex==v){
			return flow;
		}else{
			throw new IllegalArgumentException();
		}
	}

	public void addResidualFlowTo(int vertex, double delta){
		//到vertex流量增加delta
		if(vertex==w){
			flow+=delta;
		}else if(vertex==v){
			flow-=delta;
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d -> %d, %.2f, %.2f", v, w, cap, flow);
	}
}
