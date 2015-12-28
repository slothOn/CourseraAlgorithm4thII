package chapter5;

import chapter1.Bag;
import chapter1.Stack;
import chapter4.Digraph;
import chapter4.DirectedDFS;

public class NFA {
	private Digraph G;
	private int M;
	private char[] re;
	public NFA(String pat){
		//match transition+e transition
		Stack<Integer> stack= new Stack<Integer>();
		re=pat.toCharArray();
		M=re.length;
		G=new Digraph(M+1);
		int lp;
		for(int i=0;i<M;i++){
			lp=i;//左端运算起始
			if(re[i]=='|'||re[i]=='(')	stack.push(i);//左括号和或入栈
			//右括号出栈
			if(re[i]==')'){
				int a=stack.pop();
				if(re[a]=='|'){
					int b=stack.pop();
					G.addEdge(b, a+1);
					G.addEdge(a, i);
					lp=b;
				}else{
					lp=a;
				}
			}
			if(i<M-1&&re[i+1]=='*'){
				G.addEdge(lp, i+1);
				G.addEdge(i+1, lp);
			}
			if(re[i]=='*'||re[i]=='('||re[i]==')')
				G.addEdge(i, i+1);
		}
	}
	
	public boolean recognizes(String txt){
		//initialize
		DirectedDFS dfs=new DirectedDFS(G, 0);
		//所有可达状态
		Bag<Integer> ps=new Bag<Integer>();
		for(int i=0;i<M;i++){
			if(dfs.marked(i)) ps.add(i);
		}
		//遍历全文
		for(int i=0;i<txt.length();i++){
			//已经到达状态
			Bag<Integer> matches=new Bag<Integer>();
			for(int j:ps){
				if(j==M) continue;
				if(re[j]==txt.charAt(i)||re[j]=='.'){
					matches.add(j+1);
				}
			}
			dfs=new DirectedDFS(G, matches);
			ps=new Bag<Integer>();
			for(int j=0;j<M+1;j++){
				if(dfs.marked(j)) ps.add(j);
			}
		}
		for(int i:ps){
			if(i==M) return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
