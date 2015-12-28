package work1;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class OutcastTest {
	private WordNet net;
	
	public OutcastTest(WordNet wordnet){
		// constructor takes a WordNet object
		if(wordnet==null) throw new NullPointerException("v or w is null");
		net=wordnet;
	}
    public String outcast(String[] nouns){
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
		String[] arg={"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\synsets.txt", 
				"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\hypernyms.txt", 
				"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\outcast5.txt", 
				"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\outcast8.txt", 
				"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\outcast11.txt"
				};
		WordNet wordnet = new WordNet(arg[0], arg[1]);
	    OutcastTest outcast = new OutcastTest(wordnet);
	    for (int t = 2; t < arg.length; t++) {
	        In in = new In(arg[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(arg[t] + ": " + outcast.outcast(nouns));
	    }
	}

}
