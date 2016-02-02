package work04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private Node resultnode;
	private boolean solvable=false;
	public Solver(Board initial){
		// find a solution to the initial board (using the A* algorithm)
		int moves=0;
		resultnode=null;
		MinPQ<Node> pq=new MinPQ<Node>();
		Node first=new Node(initial, null, moves);
		pq.insert(first);
		
		MinPQ<Node> pqtwin=new MinPQ<Node>();
		Node firsttwin=new Node(initial.twin(),null,moves);
		pqtwin.insert(firsttwin);
		Node twinnode=null;
		while(true){
			resultnode=pq.delMin();
			if(resultnode.board.isGoal()){
				solvable=true;
				break;
			}
			for(Board b:resultnode.board.neighbors()){
				if(resultnode.previous != null && b.equals(resultnode.previous.board)) continue;
				Node next=new Node(b, resultnode, moves+1);
				pq.insert(next);
			}
			
			twinnode=pqtwin.delMin();
			if(twinnode.board.isGoal()){
				solvable=false;
				break;
			}
			for(Board b:twinnode.board.neighbors()){
				if(twinnode.previous != null && b.equals(twinnode.previous.board)) continue;
				Node nexttwin=new Node(b, twinnode, moves+1);
				pqtwin.insert(nexttwin);
			}
			moves++;
		}
	}
    public boolean isSolvable(){
    	// is the initial board solvable?
    	return solvable;
    }
    public int moves(){
    	// min number of moves to solve initial board; -1 if unsolvable
    	if(solvable) return resultnode.moves;
    	return -1;
    }
    public Iterable<Board> solution(){
    	// sequence of boards in a shortest solution; null if unsolvable
    	if(!solvable) return null;
    	Stack<Board> stack=new Stack<Board>();
    	for(Node i=resultnode;i != null;i=i.previous){
    		stack.push(i.board);
    	}
    	return stack;
    }
	
    class Node implements Comparable<Node>{
		Board board;
		Node previous;
		int moves;
		int priority;
		public Node(Board board, Node previous, int moves) {
			// TODO Auto-generated constructor stub
			this.board=board; this.previous=previous; this.moves=moves; 
			priority=board.manhattan()+moves;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(this.priority<o.priority) return -1;
			if(this.priority == o.priority) return 0;
			return 1;
		}
	}
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create initial board from file
	    In in = new In("E:\\Further study in north America\\CS\\algorithmI\\8puzzle-testing\\8puzzle\\puzzle3x3-unsolvable.txt");
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}
