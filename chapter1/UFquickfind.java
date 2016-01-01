package chapter1;

import edu.princeton.cs.algs4.In;

public class UFquickfind {
	
	private int count;
	//use this array as data index
	private int[] id;
	
	public UFquickfind(int N){
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
		return id[p];
	}
	
	public void union(int p, int q){
		int pvalue=id[p],qvalue=id[q];
		if(pvalue==qvalue) return;
		for(int i=0;i<id.length;i++){
			if(id[i]==pvalue){
				id[i]=qvalue;
			}
		}
		count--;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in= new In("E:\\jwork\\algs4-data\\tinyUF.txt");
		int nodenum= Integer.valueOf(in.readLine());
		UFquickfind uf= new UFquickfind(nodenum);
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
