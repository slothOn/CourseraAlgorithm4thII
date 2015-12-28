package work3;


import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
	private int[] w, l, r;
	private int[][] g;
	private String teams[];
	private boolean[] marked;
	private int num;
	private SET<String>[] subset;
	public BaseballElimination(String filename){
		// create a baseball division from given filename in format specified below
		In in= new In(filename);
		num= in.readInt();
		if(num<=1) return;
		w= new int[num]; l= new int[num]; r= new int[num]; g= new int[num][num];
		teams= new String[num]; marked= new boolean[num]; subset= (SET<String>[])new SET[num];
		
		for(int i=0;i<num;i++){
			teams[i]=in.readString();
			w[i]=in.readInt();
			l[i]=in.readInt();
			r[i]=in.readInt();
			for(int j=0;j<num;j++){
				g[i][j]=in.readInt();
			}
		}
		for(int i=0;i<num;i++){
			processEachTeam(i);
		}
	}
	
	private void processEachTeam(int k){
		//trivial elimination
		int pk=r[k]+w[k];
		for(int i=0;i<num;i++){
			if(w[i]>pk){
				marked[k]=true;
				subset[k]=new SET<String>();
				subset[k].add(teams[i]);
				return;
			}
		}
		//non trivial elimination
		if(num==2) return;
		int V= num-1+(num-1)*(num-2)/2+2;
		FlowNetwork G= new FlowNetwork(V);
		
		for(int i=0;i<k;i++){
			G.addEdge(new FlowEdge(i, V-1, pk-w[i]));
		}
		for(int i=k+1;i<num;i++){
			G.addEdge(new FlowEdge(i-1, V-1, pk-w[i]));
		}
		int count=0;
		for(int p=0;p<num;p++){
			if(p==k) continue;
			for(int q=p+1;q<num;q++){
				if(q==k) continue;
				count++;
				G.addEdge(new FlowEdge(V-2, count+num-2, g[p][q]));
				G.addEdge(new FlowEdge(count+num-2, p, Double.POSITIVE_INFINITY));
				G.addEdge(new FlowEdge(count+num-2, q, Double.POSITIVE_INFINITY));
			}
		}
		FordFulkerson maxflow= new FordFulkerson(G, V-2, V-1);
		count= 0;
		for(int p=0;p<num;p++){
			if(p==k) continue;
			for(int q=p+1;q<num;q++){
				if(q==k) continue;
				count++;
				if(maxflow.inCut(count+num-2)){
					if(!marked[k]){
						marked[k]=true;
						subset[k]= new SET<String>();
					}
					if(!subset[k].contains(teams[p]))
						subset[k].add(teams[p]);
					if(!subset[k].contains(teams[q]))
						subset[k].add(teams[q]);
				}
			}
		}
	}
	
	public int numberOfTeams(){
		// number of teams
		return num;
	}
	public Iterable<String> teams()   {
		// all teams
		Queue<String> queue= new Queue<String>();
		for(int i=0;i<num;i++)
			queue.enqueue(teams[i]);
		return queue;
	}
	
	public  int wins(String team) {
		// number of wins for given team
		for(int i=0;i<num;i++){
			if(team.equals(teams[i])){
				return w[i];
			}
		}
		throw new IllegalArgumentException();
	}
	public  int losses(String team)  {
		// number of losses for given team
		for(int i=0;i<num;i++){
			if(team.equals(teams[i])){
				return l[i];
			}
		}
		throw new IllegalArgumentException();
	}
	public  int remaining(String team) {
		// number of remaining games for given team
		for(int i=0;i<num;i++){
			if(team.equals(teams[i])){
				return r[i];
			}
		}
		throw new IllegalArgumentException();
	}
	public  int against(String team1, String team2)  {
		// number of remaining games between team1 and team2
		int a=-1, b=-1;
		for(int i=0;i<num;i++){
			if(team1.equals(teams[i])){
				a= i;
				break;
			}
		}
		for(int i=0;i<num;i++){
			if(team1.equals(teams[i])){
				b= i;
				break;
			}
		}
		return g[a][b];
	}
	public  boolean isEliminated(String team)  {
		// is given team eliminated?
		for(int i=0;i<num;i++){
			if(team.equals(teams[i])){
				return marked[i];
			}
		}
		throw new IllegalArgumentException();
	}
	public Iterable<String> certificateOfElimination(String team) {
		// subset R of teams that eliminates given team; null if not eliminated
		for(int i=0;i<num;i++){
			if(team.equals(teams[i])){
				return subset[i];
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseballElimination division = new BaseballElimination("E:\\jwork\\algorithm\\Part2\\baseball\\teams5.txt");
		   for (String team : division.teams()) {
		        if (division.isEliminated(team)) {
		            StdOut.print(team + " is eliminated by the subset R = { ");
		            for (String t : division.certificateOfElimination(team)) {
		                StdOut.print(t + " ");
		            }
		            StdOut.println("}");
		        }
		        else {
		            StdOut.println(team + " is not eliminated");
		        }
		    }
	}

}
