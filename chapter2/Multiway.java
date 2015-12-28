package chapter2;

import edu.princeton.cs.algs4.In;

public class Multiway {
	
	public static void merge(In[] ins){
		int N= ins.length;
		IndexMinPQ<String> pq;
		pq= new IndexMinPQ<String>(N);
		for(int i=0;i<N;i++){
			if(!ins[i].isEmpty()){
				pq.insert(i, ins[i].readString());
			}
		}
		while(!pq.isEmpty()){
			System.out.print(pq.min()+",");
			int minindex= pq.delMin();
			if(!ins[minindex].isEmpty()){
				pq.insert(minindex, ins[minindex].readString());
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=3;
		String[] arg={"E:\\jwork\\algs4-data\\m1.txt", 
				"E:\\jwork\\algs4-data\\m2.txt", 
				"E:\\jwork\\algs4-data\\m3.txt"};
		In[] streams= new In[N];
		for(int i=0;i<N;i++){
			streams[i]= new In(arg[i]);
		}
		merge(streams);
	}

}
