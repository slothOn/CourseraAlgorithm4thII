package work1;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private WordNet net;
	
	public Outcast(WordNet wordnet){
		// constructor takes a WordNet object
		if(wordnet==null) throw new NullPointerException("v or w is null");
		net=wordnet;
	}
	 public String outcast(String[] nouns){
    	// given an array of WordNet nouns, return an outcast
    	if(nouns==null) throw new NullPointerException("v or w is null");
    	String outcast="";
    	int max=0,sum;
    	int[][] distmatrix= new int[nouns.length][nouns.length];
    	for(int i=0;i<nouns.length;i++){
    		sum=0;
    		for(int j=0;j<i;j++){
    			sum+= distmatrix[j][i];
    		}
    		for(int j=i+1;j<nouns.length;j++){
    			distmatrix[i][j]=net.distance(nouns[i], nouns[j]);
    			sum+=distmatrix[i][j];
    		}
    		if(sum>max){
    			max=sum;
    			outcast=nouns[i];
    		}
    	}
    	return outcast;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}

}
