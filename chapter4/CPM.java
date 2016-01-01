package chapter4;

import edu.princeton.cs.algs4.In;
//Parallel tasks scheduling
public class CPM {
	//model to an acycllic graph
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\jobsPC.txt");
		int num=Integer.valueOf(in.readLine());
		EdgeWeightedDigraph G= new EdgeWeightedDigraph(2*num+2);
		DirectedEdge edge;
		for(int i=0;i<num;i++){
			String[] str= in.readLine().split("\\s+");
			double weight= Double.valueOf(str[0]);
			G.addEdge(new DirectedEdge(i, i+num, weight));
			G.addEdge(new DirectedEdge(2*num, i, 0));
			G.addEdge(new DirectedEdge(i+num, 2*num+1, 0));
			for(int j=1;j<str.length;j++){
				int w= Integer.valueOf(str[j]);
				G.addEdge(new DirectedEdge(i+num, w, 0));
			}
		}
		AcyclicLP lp= new AcyclicLP(G, 2*num);
		for(int i=0;i<num;i++){
			System.out.printf("%4d:%5.1f\n",i,lp.distTo(i));
		}
		System.out.printf("%5.1f",lp.distTo(2*num+1));
	}

}
