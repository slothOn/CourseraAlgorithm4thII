package chapter1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.UF;

//optimal algorithm for union find
public class UnionFind {
	
	private int count;
	private int[] id;
	private int[] dp;
	public UnionFind(int N) {
		// TODO Auto-generated constructor stub
		id= new int[N];
		dp= new int[N];
		count=N;
		for(int i=0;i<N;i++){
			id[i]=i;
			dp[i]=1;
		}
		
	}
	
	public boolean connected(int p, int q){
		return find(p)==find(q);
	}
	
	public int find(int p){
		if(p==id[p]) return p;
		int i= find(id[p]);
		id[p]= i;
		return i;
	}
	
	public void union(int p, int q){
		int pid= find(p), qid= find(q);
		if(pid==qid) return;
		if(dp[pid]==dp[qid]){
			id[pid]= qid;
			dp[qid]++;
		}else if(dp[pid]<dp[qid]){
			id[pid]= qid;
		}else{
			id[qid]= pid;
		}
		count--;
	}
	
	public int count(){
		return count;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in =new In("E:\\jwork\\algs4-data\\largeUF.txt");
		int N=Integer.valueOf(in.readLine());
		UnionFind uf= new UnionFind(N);
		//UF uf= new UF(N);
		String str;
		int a,b;
		while((str=in.readLine())!=null){
			a= Integer.valueOf(str.split(" ")[0]);
			b= Integer.valueOf(str.split(" ")[1]);
			if(!uf.connected(a, b)){
				uf.union(a, b);
				System.out.println(a+"-"+b);
			}
		}
		System.out.println(uf.count()+" components");
	}

}
