package chapter2;

import chapter1.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Transaction;
//得到最小5个元素
public class TopMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int M= 5;
		MinPQ<Transaction> pq= new MinPQ<Transaction>();
		In in= new In("E:\\jwork\\algs4-data\\tinyBatch.txt");
		String str;
		while((str=in.readLine())!=null){
			pq.insert(new Transaction(str));
			if(pq.size()>M){
				pq.delMin();
			}
		}
		Stack<Transaction> stack= new Stack<Transaction>();
		while(!pq.isEmpty()) stack.push(pq.delMin());
		for(Transaction t: stack) System.out.println(t);
	}

}
