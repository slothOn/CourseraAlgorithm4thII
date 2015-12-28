
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;


public class WordNet {
	
	private Digraph G;
	//symbol table+ arrays
	private ST<String, SET<Integer>> st;
	private ST<Integer, String> aname;
	private SAP sap;

	// constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){
    	if(synsets==null||hypernyms==null) throw new NullPointerException("synsets or hypernyms is null");
    	st= new ST<String, SET<Integer>>();
    	aname =new ST<Integer, String>();
    	In in1= new In(synsets);
    	String line1, line2;
    	String[] linestr, synsetsname;
    	while((line1= in1.readLine())!= null){
    		linestr= line1.split(",");
    		int id= Integer.valueOf(linestr[0]);
    		aname.put(id, linestr[1]);
    		synsetsname= linestr[1].split(" ");
    		for(String name:synsetsname){
    			if(st.contains(name)){
        			st.get(name).add(id);
        		}else{
        			SET<Integer> set= new SET<Integer>();
        			set.add(id);
        			st.put(name, set);
        		}
    		}
    		    		
    		
    	}
    	
    	G= new Digraph(aname.size());
    	In in2= new In(hypernyms);
    	while((line2= in2.readLine())!= null){
    		linestr= line2.split(","); 
    		for(int i=1;i<linestr.length;i++){
    			G.addEdge(Integer.valueOf(linestr[0]), Integer.valueOf(linestr[i]));
    		}
    	}
    	//check rooted DAG
    	DirectedCycle cycle= new DirectedCycle(G);
    	if(cycle.hasCycle())
    		throw new IllegalArgumentException();
    	int rootcount=0;
    	for(int i=0;i<G.V();i++){
    		if(G.outdegree(i)==0){
    			rootcount++;
    			if(rootcount>=2){
    				throw new IllegalArgumentException();
    			}
    		}
    	}
    	sap= new SAP(G);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns(){
    	return st.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word){
    	if(word==null) throw new NullPointerException("word is null");
    	if(st.contains(word)){
    		return true;
    	}
    	return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
    	if(nounA==null||nounB==null) throw new NullPointerException("nounA or nounB is null");
    	if(!isNoun(nounA)||!isNoun(nounB)) throw new IllegalArgumentException();
    	SET<Integer> seta= st.get(nounA);
    	SET<Integer> setb= st.get(nounB);
    	return sap.length(seta, setb);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
    	if(nounA==null||nounB==null) throw new NullPointerException("nounA or nounB is null");
    	if(!isNoun(nounA)||!isNoun(nounB)) throw new IllegalArgumentException();
    	int acsindex= sap.ancestor(st.get(nounA), st.get(nounB));
    	
    	return aname.get(acsindex);
    }

    // do unit testing of this class
    public static void main(String[] args){
    	String[] arg={"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\synsets.txt", 
				"E:\\jwork\\algorithm\\Part2\\wordnet-testing\\wordnet\\hypernyms.txt"};
		WordNet wordnet = new WordNet(arg[0], arg[1]);
		System.out.println(wordnet.sap("gusto", "mediety")+","+wordnet.distance("gusto", "mediety"));
    }
}
