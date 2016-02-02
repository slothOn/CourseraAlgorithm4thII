package work04;

import edu.princeton.cs.algs4.Queue;

public class Board {
	
	private int N;
	private int[][] tiles;
	private int zeroi, zeroj;
	public Board(int[][] blocks){
		// construct a board from an N-by-N array of blocks
		// (where blocks[i][j] = block in row i, column j)
		N=blocks.length;
		tiles=new int[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				tiles[i][j]=blocks[i][j];
				if(tiles[i][j] == 0){
					zeroi=i; zeroj=j;
				}
			}
	}
    
	public int dimension(){
		// board dimension N
		return N;
	}
	public int hamming(){
		// number of blocks out of place
		int count=0;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				if(i == N-1 && j == N-1) continue;
				if(tiles[i][j] != i*N+j+1){
					count++;
				}
			}
		return count;
	}
	public int manhattan(){
		// sum of Manhattan distances between blocks and goal
		int sum=0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				int val=tiles[i][j];
				if(val == 0) continue;
				int vali=(val-1)/N;
				int valj=(val-1)%N;
				sum += Math.abs(vali-i)+Math.abs(valj-j);
			}
		}
		return sum;
	}
	public boolean isGoal(){
		// is this board the goal board?
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++){
				if(i == N-1 && j == N-1) continue;
				if(tiles[i][j] != i*N+j+1) return false;
			}
		return true;
	}
	public Board twin(){
		// a board that is obtained by exchanging two adjacent blocks in the same row
		if(zeroi != 0){
			exch(tiles, 0, 0, 0, 1);
			Board b=new Board(tiles);
			exch(tiles, 0, 1, 0, 0);
			return b;
		}else{
			exch(tiles, 1, 0, 1, 1);
			Board b=new Board(tiles);
			exch(tiles, 1, 1, 1, 0);
			return b;
		}
	}
	public boolean equals(Object y){
		// does this board equal y?
		//类内部可以访问到私有对象
		if(y == this) return true;
		if(y == null) return false;
		if(y.getClass() != this.getClass()) return false;
		Board that=(Board)y;
		if(this.N != that.N) return false;
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(this.tiles[i][j] != that.tiles[i][j]) return false;
		return true;
	}
	public Iterable<Board> neighbors(){
		// all neighboring boards
		Queue<Board> queue=new Queue<Board>();
		if(zeroj-1 >= 0){
			exch(tiles, zeroi, zeroj, zeroi, zeroj-1);
			Board b=new Board(tiles);
			exch(tiles, zeroi, zeroj-1, zeroi, zeroj);
			queue.enqueue(b);
		}
		if(zeroj+1<N){
			exch(tiles, zeroi, zeroj, zeroi, zeroj+1);
			Board b=new Board(tiles);
			exch(tiles, zeroi, zeroj+1, zeroi, zeroj);
			queue.enqueue(b);
		}
		if(zeroi-1 >= 0){
			exch(tiles, zeroi, zeroj, zeroi-1, zeroj);
			Board b=new Board(tiles);
			exch(tiles, zeroi-1, zeroj, zeroi, zeroj);
			queue.enqueue(b);
		}
		if(zeroi+1<N){
			exch(tiles, zeroi, zeroj, zeroi+1, zeroj);
			Board b=new Board(tiles);
			exch(tiles, zeroi+1, zeroj, zeroi, zeroj);
			queue.enqueue(b);
		}
		return queue;
	}
	private void exch(int[][] tiles,int i1, int j1, int i2, int j2){
		int swap=tiles[i1][j1];
		tiles[i1][j1]=tiles[i2][j2];
		tiles[i2][j2]=swap;
	}
	
	public String toString() {
		// string representation of this board (in the output format specified below)
		StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", tiles[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
