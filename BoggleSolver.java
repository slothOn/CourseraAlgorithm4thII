

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {
	 // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	private StringST dict; 
	private int M, N;
    private boolean marked[][];
    private SET<String> set;
    
	public BoggleSolver(String[] dictionary){
    	dict= new StringST();
    	int count=1;
		for(String s:dictionary)
    		dict.put(s, count++);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	M=board.rows();
    	N=board.cols();
    	set = new SET<String>();
    	marked=new boolean[M][N];
    	String s="";
    	for(int i=0; i<M; i++)
    		for(int j=0; j<N; j++)
    			dfs(i, j, s, set, board);
    	return set;
    }
    private void dfs(int i, int j, String s, SET<String> set, BoggleBoard board){
    	if(board.getLetter(i, j)=='Q'){
    		s += "QU";
    	}else{
    		s += board.getLetter(i, j);	
    	}
    	if(!dict.ifExistPrefix(s)) return;
    	marked[i][j]=true;
    	if(s.length()>=3&&dict.get(s)!=null)
    		set.add(s);
    	if(i>0&&j>0&&!marked[i-1][j-1]){
    		//可以向左上方向搜索
    		dfs(i-1, j-1, s, set, board);
    	}
    	if(i>0&&!marked[i-1][j]){
    		//可以向上搜索
    		dfs(i-1, j, s, set, board);
    	}
    	if(i>0&&j<N-1&&!marked[i-1][j+1]){
    		//可以向右上搜索
    		dfs(i-1, j+1, s, set, board);
    	}
    	if(j>0&&!marked[i][j-1]){
    		//可以向左搜索
    		dfs(i, j-1, s, set, board);
    	}
    	if(j<N-1&&!marked[i][j+1]){
    		//可以向右搜索
    		dfs(i, j+1, s, set, board);
    	}
    	if(i<M-1&&j>0&&!marked[i+1][j-1]){
    		//可以向左下搜索
    		dfs(i+1, j-1, s, set, board);
    	}
    	if(i<M-1&&!marked[i+1][j]){
    		//可以向下搜索
    		dfs(i+1, j, s, set, board);
    	}
    	if(i<M-1&&j<N-1&&!marked[i+1][j+1]){
    		//可以向右下搜索
    		dfs(i+1, j+1, s, set, board);
    	}
    	//没有方向可以搜索
    	marked[i][j]=false;//回退
    	return;
    }
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	if(!dict.contains(word)) return 0;
    	int len=word.length();
    	if(len<=2){
    		return 0;
    	}else if(len>=3&&len<=4){
    		return 1;
    	}else if(len==5){
    		return 2;
    	}else if(len==6){
    		return 3;
    	}else if(len==7){
    		return 5;
    	}else{
    		return 11;
    	}
    }
	/**
	 * @param args
	 */
    public static void main(String[] args)
    {
        In in = new In("/media/zxc/Personal Affairs/jwork/algorithm/Part2/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("/media/zxc/Personal Affairs/jwork/algorithm/Part2/board4x4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

}

//26字符单词查找树
class StringST{
	private int R=26;
	private int[] map;
	class Node{
		Integer val;
		Node[] next=new Node[R];
	}
	private int charAt(String key, int d){	
		return map[key.charAt(d)];
	}
	
	private Node root;
	public StringST() {
		// TODO Auto-generated constructor stub
		root=new Node();
		map=new int[256];
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<s.length();i++){
			map[s.charAt(i)]=i;
		}
	}
	
	public void put(String key, int val){
		root=put(root, key, val, 0);
	}
	private Node put(Node n, String key, int val, int d){
		if(n==null) n=new Node();
		if(d==key.length()) {
			n.val=val;
			return n;
		}
		int c=charAt(key, d);
		//注意recursion的用法
		n.next[c]=put(n.next[c], key, val, d+1);
		return n;
	}
	
	public Integer get(String key){
		Node n=get(root, key, 0);
		if(n==null) return null;
		return n.val;
	}
	private Node get(Node n, String key, int d){
		//到这已经没有了
		if(n==null) return null;
		//遇到最后一个
		if(d==key.length()){
			return n;
		}
		int c=charAt(key, d);
		return get(n.next[c], key, d+1);
	}
	
	public boolean contains(String key){
		return get(key)!=null;
	}
	
	public boolean ifExistPrefix(String key){
		Node n=get(root, key, 0);
		if(n==null) return false;
		return true;
	}
	
}
