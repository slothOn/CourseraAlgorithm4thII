package chapter2;

import edu.princeton.cs.algs4.In;

public class MultiwayTest {
	private static void merge(In[] stream){
		IndexMinPriorityQueue<String> ipq=new IndexMinPriorityQueue<>(100);
		for(int i=0;i<stream.length;i++){
			ipq.insert(i, stream[i].readString());
		}
		while(!ipq.isEmpty()){
			System.out.print(ipq.min()+" ");
			int k=ipq.delMin();
			if(!stream[k].isEmpty())
				ipq.insert(k, stream[k].readString());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=3;
		In[] stream=new In[N];
		stream[0]=new In("E:\\jwork\\algs4-data\\m1.txt");
		stream[1]=new In("E:\\jwork\\algs4-data\\m2.txt");
		stream[2]=new In("E:\\jwork\\algs4-data\\m3.txt");
		merge(stream);
	}

}
