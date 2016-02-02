package work01;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private int N;
	private double[] count;
	private double mean;
	private double stddev;
	public PercolationStats(int N, int T){
		// perform T independent experiments on an N-by-N grid
		count=new double[T];
		this.N=N;
		for(int i=0;i<T;i++){
			Percolation percolation=new Percolation(N);
			while(!percolation.percolates()){
				int row=StdRandom.uniform(1, N+1);
				int col=StdRandom.uniform(1, N+1);
				if(!percolation.isOpen(row, col)){
					percolation.open(row, col);
					count[i]++;
				}
			}
			count[i]=count[i]/(N*N);
		}
	}
	public double mean(){
		// sample mean of percolation threshold
		double sum=0.0;
		for(int i=0;i<count.length;i++){
			sum += count[i];
		}
		mean=sum/count.length;
		return mean;
	}
	public double stddev(){
		// sample standard deviation of percolation threshold
		double sum=0.0;
		for(int i=0;i<count.length;i++){
			sum += (count[i]-mean)*(count[i]-mean);
		}
		stddev=Math.sqrt(sum/(count.length-1));
		return stddev;
	}
	public double confidenceLo(){
		// low  endpoint of 95% confidence interval
		return mean-1.96*stddev/Math.sqrt(count.length);
	}
	public double confidenceHi(){
		// high endpoint of 95% confidence interval
		return mean+1.96*stddev/Math.sqrt(count.length);
	}

	public static void main(String[] args){
		// test client (described below)
		int N=200, T=100;
		PercolationStats percolationstats=new PercolationStats(N, T);
		System.out.println("mean = "+percolationstats.mean());
		System.out.println("stddev = "+percolationstats.stddev());
		System.out.println("95% confidence interval = "+percolationstats.confidenceLo()+", "+percolationstats.confidenceHi());
	}

}
