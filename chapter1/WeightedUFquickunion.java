package chapter1;

import edu.princeton.cs.algs4.In;

public class WeightedUFquickunion {
	
	private int count;
	//use this array as data index
	private int[] id;
	
	public WeightedUFquickunion(int N){
		id= new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
		}
		count=N;
	}
	
	public int count(){
		return count;
	}
	
	public boolean connected(int p, int q){
		return find(p)==find(q);
	}
	
	public int find(int p){
		int i;
		for(i=p;id[i]!=i;i=id[i]);
		return i;
	}
	
	public void union(int p, int q){
		int pid= find(p), qid= find(q);
		if(pid==qid) return;
		id[pid]= qid;
		count--;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\tinyUF.txt");
		int nodenum= Integer.valueOf(in.readLine());
		WeightedUFquickunion uf= new WeightedUFquickunion(nodenum);
		String str;
		int a,b;
		while((str= in.readLine())!= null){
			a=Integer.valueOf(str.split(" ")[0]);
			b=Integer.valueOf(str.split(" ")[1]);
			if(!uf.connected(a, b)){
				uf.union(a, b);
				System.out.println(a+"-"+b);
			}
		}
		System.out.println(uf.count+"components");
	}

}
