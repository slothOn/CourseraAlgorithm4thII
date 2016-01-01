package chapter4;

public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;
	public DirectedEdge(int v, int w, double weight) {
		// TODO Auto-generated constructor stub
		this.v=v;this.w=w;this.weight=weight;
	}
	public int from(){
		return v;
	}
	
	public int to(){
		return w;
	}
	public double weight(){
		return weight;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return v+"-"+w+"("+weight+")";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
