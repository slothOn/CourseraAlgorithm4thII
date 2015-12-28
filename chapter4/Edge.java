package chapter4;
//Non-directed weighted edge
public class Edge implements Comparable<Edge>{
	
	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight){
		this.v=v;this.w=w;this.weight=weight;
	}
	
	public int either(){
		return v;
	}
	
	public int other(int v){
		if(this.v==v) return this.w;
		if(this.w==v) return this.v;
		else throw new RuntimeException("Inconsistent edge");
	}
	
	public double weight(){
		return weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		if(o.weight==this.weight) return 0;
		if(this.weight>o.weight) return 1;
		return -1;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d-%d,%.2f", v,w,weight);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
