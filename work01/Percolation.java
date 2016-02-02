package work01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF uf;
	private boolean[] tiles;
	private int N;
	public Percolation(int N){
		// create N-by-N grid, with all sites blocked
		this.N=N;
		tiles=new boolean[N*N+2];
		uf=new WeightedQuickUnionUF(N*N+2);
		tiles[0]=true; tiles[N*N+1]=true;
		for(int i=1;i <= N;i++){
			uf.union(0, i);
		}
		for(int i=1;i <= N;i++){
			uf.union(N*N+1-i, N*N+1);
		}
	}
	
	private int convert2dTo1d(int i, int j){
		if(i<1 || i>N || j<1 || j>N){
			return -1;
		}
		return (i-1)*N+j;
	}
	public void open(int i, int j){
		// open site (row i, column j) if it is not open already
		if(isOpen(i, j)) return;
		int k=convert2dTo1d(i, j);
		tiles[k]=true;
		int k1=convert2dTo1d(i-1, j);
		int k2=convert2dTo1d(i+1, j);
		int k3=convert2dTo1d(i, j-1);
		int k4=convert2dTo1d(i, j+1);
		if(k1 != -1 && tiles[k1]) {
			uf.union(k, k1);
		}
		if(k2 != -1 && tiles[k2]){
			uf.union(k, k2);
		}
		if(k3 != -1 && tiles[k3]){
			uf.union(k, k3);
		}
		if(k4 != -1 && tiles[k4]){
			uf.union(k, k4);
		}
	}
	public boolean isOpen(int i, int j){
		// is site (row i, column j) open?
		int k=convert2dTo1d(i, j);
		return tiles[k];
	}
	public boolean isFull(int i, int j){
		// is site (row i, column j) full?
		int k=convert2dTo1d(i, j);
		return uf.connected(0, k);
	}
	public boolean percolates(){
		// does the system percolate?
		return uf.connected(0, N*N+1);
	}

	public static void main(String[] args){
	// test client (optional)
	
	}

}
