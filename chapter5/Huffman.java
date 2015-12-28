package chapter5;

import chapter2.MinPQ;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;


public class Huffman {
	private static int R=256;
	private BinaryIn in;
	private BinaryOut out;
	public Huffman(BinaryIn in, BinaryOut out){
		this.in=in; this.out=out;
	}
	private static class Node implements Comparable<Node>{
		private Node left, right;
		private char c;
		private int freq;
		public Node(char c, int freq, Node left, Node right){
			this.c=c; this.freq=freq; this.left=left; this.right=right;
		}
		public boolean isLeaf(){
			return left==null&&right==null;
		}
		@Override
		public int compareTo(Node that) {
			// TODO Auto-generated method stub
			return this.freq-that.freq;
		}
		
	}
	//解压
	public void expand(){
		Node root=readTrie();
		//流结束时追加字符个数
		int N=in.readInt();
		for(int i=0;i<N;i++){
			Node x=root;
			while(!x.isLeaf()){
				if(!in.readBoolean()){
					x=x.left;
				}else{
					x=x.right;
				}
			}
			out.write(x.c);
		}
		out.close();
	}
	//压缩
	public  void compress(){
		String s=in.readString();
		char[] sc=s.toCharArray();
		int[] freq= new int[R];
		for(int i=0;i<sc.length;i++){
			freq[sc[i]]++;
		}
		Node trie= buildTries(freq);
		writeTrie(trie);
		out.write(sc.length);
		//构造编码表
		String[] code=buildCode(trie);
		for(int i=0;i<sc.length;i++){
			for(char c:code[sc[i]].toCharArray())
				if(c=='1')	out.write(true);
				else out.write(false);
		}
		out.close();
	}
	//把编码树写入比特流
	public void writeTrie(Node x){
		if(x.isLeaf()){
			out.write(true);
			out.write(x.c);
		}else{
			out.write(false);
			writeTrie(x.left);
			writeTrie(x.right);
		}
	}
	//读取比特流重构编码树
	public Node readTrie(){
		//读到0是内部结点,1表示外部结点
		if(in.readBoolean()){
			return new Node(in.readChar(),0,null,null);
		}else{
			return new Node('\0', 0, readTrie(), readTrie());
		}
	}
	//使用单词查找树构造编译表
	public String[] buildCode(Node root){
		String[] code=new String[R];
		buildCode(code, root,"");
		return code;
	}
	//创建编码表
	private void buildCode(String[] code, Node root, String s){
		if(root.isLeaf()){
			code[root.c]=s;
		}else{
			buildCode(code, root.left, s+"0");
			buildCode(code, root.right, s+"1");
		}
	}
	//根据词频构建huffman树
	private Node buildTries(int[] freq){
		MinPQ<Node> pq=new MinPQ<Node>();
		for(char i=0;i<freq.length;i++){
			if(freq[i]>0)
				pq.insert(new Node(i, freq[i], null, null));
		}
		while(pq.size()>1){
			Node a=pq.delMin();
			Node b=pq.delMin();
			Node n=new Node('\0', a.freq+b.freq, a, b);
			pq.insert(n);
		}
		return pq.delMin();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Huffman h= new Huffman(new BinaryIn("/media/zxc/Personal Affairs/jwork/algs4-data/tale.txt"), new BinaryOut("/media/zxc/Personal Affairs/jwork/algs4-data/tale.bin"));
		h.compress();
		/*
		Huffman h= new Huffman(new BinaryIn("HuffmanOut.bin"), new BinaryOut("HuffmanOut.txt"));
		h.expand();
		*/
	}

}
