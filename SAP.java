
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class SAP {
    // constructor takes a digraph (not necessarily a DAG)
	private Digraph G;
	private int cachev, cachew, cachedist, cacheancestor;
    public SAP(Digraph G){
    	if(G==null) throw new NullPointerException("v or w is null");    	
    	this.G = new Digraph(G);
    	cachev= -1; cachew= -1; cachedist= -1; cacheancestor= -1;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
    	if(v<0||v>G.V()-1||w<0||w>G.V()-1) throw new IndexOutOfBoundsException();
    	
    	if(v == cachev && w == cachew){
    		return cachedist;
    	}
    	
    	BreadthFirstDirectedPaths bfs1= new BreadthFirstDirectedPaths(G, v);
    	BreadthFirstDirectedPaths bfs2= new BreadthFirstDirectedPaths(G, w);
    	int min=Integer.MAX_VALUE,length,acs=-1;
    	for(int i=0;i<G.V();i++){
    		if(bfs1.hasPathTo(i)&&bfs2.hasPathTo(i)){
    			length=bfs1.distTo(i)+bfs2.distTo(i);
    			if(length<min){
    				min=length;
    				acs=i;
    			}
    		}
    	}
    	//更新cache
    	cachev = v; cachew = w; cacheancestor = acs;
    	if(acs == -1){
    		cachedist = -1;
    	}else{
    		cachedist = bfs1.distTo(acs)+bfs2.distTo(acs);
    	}
    	return cachedist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
    	if(v<0||v>G.V()-1||w<0||w>G.V()-1) throw new IndexOutOfBoundsException();
    	if(v == cachev && w == cachew){
    		return cacheancestor;
    	}
    	
    	BreadthFirstDirectedPaths bfs1= new BreadthFirstDirectedPaths(G, v);
    	BreadthFirstDirectedPaths bfs2= new BreadthFirstDirectedPaths(G, w);
    	int min=Integer.MAX_VALUE,length,acs=-1;
    	for(int i=0;i<G.V();i++){
    		if(bfs1.hasPathTo(i)&&bfs2.hasPathTo(i)){
    			length=bfs1.distTo(i)+bfs2.distTo(i);
    			if(length<min){
    				min=length;
    				acs=i;
    			}
    		}
    	}
    	//更新cache
    	cachev = v; cachew = w; cacheancestor = acs; 
    	if(acs == -1){
    		cachedist = -1;
    	}else{
    		cachedist = bfs1.distTo(acs)+bfs2.distTo(acs);
    	}
    	return acs;
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
    	if(v==null||w==null) throw new NullPointerException("v or w is null");
    	BreadthFirstDirectedPaths bfs1= new BreadthFirstDirectedPaths(G, v);
    	BreadthFirstDirectedPaths bfs2= new BreadthFirstDirectedPaths(G, w);
    	int acs=ancestor(v, w);
    	if(acs==-1) return -1;
    	return bfs1.distTo(acs)+bfs2.distTo(acs);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
    	if(v==null||w==null) throw new NullPointerException("v or w is null");
    	BreadthFirstDirectedPaths bfs1= new BreadthFirstDirectedPaths(G, v);
    	BreadthFirstDirectedPaths bfs2= new BreadthFirstDirectedPaths(G, w);
    	
    	int min=Integer.MAX_VALUE,length,acs=-1;
    	for(int i=0;i<G.V();i++){
    		if(bfs1.hasPathTo(i)&&bfs2.hasPathTo(i)){
    			length=bfs1.distTo(i)+bfs2.distTo(i);
    			if(length<min){
    				min=length;
    				acs=i;
    			}
    		}
    	}
    	return acs;
    }
	
    // do unit testing of this class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In("E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\digraph1.txt");
	    Digraph G= new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}

}
