package chapter4;
//呀~这套坑还没填好
import edu.princeton.cs.algs4.In;

public class Arbitrage {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In input= new In("E:\\jwork\\algs4-data\\rates.txt");
		int N= input.readInt();
		String[] names= new String[N];
		EdgeWeightedDigraph G= new EdgeWeightedDigraph(N);
		for(int i=0;i<N;i++){
			names[i]= input.readString();
			for(int j=0;j<N;j++){
				DirectedEdge de= new DirectedEdge(i, j, -Math.log(input.readDouble()));
				G.addEdge(de);
			}
		}
		BellmanFordSP sp= new BellmanFordSP(G, 0);
		if(!sp.hasNegativeCycle()){
			System.out.println("No arbitrage chance");
		}else{
			double cap= 1000;
			for(DirectedEdge dedge:sp.negativeCycle()){
				double rate= Math.exp(-dedge.weight());
				System.out.printf("%10.5f "+names[dedge.from()]+" = "+"%10.5f "+names[dedge.to()], cap, cap*rate);
				cap= cap*rate;
			}
		}
	}

}
