package chapter1;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;

import java.util.Iterator;

/**
 * Created by zxc on 2015/11/29.
 */
public class Stack<T> implements Iterable<T>{
	private class Node{
		T item;
		Node next;
	}
	
	private Node first;
	private int N;
	
	public Stack(){
		N=0;
		first=null;
    }

    public void push(T item){
    	Node node=new Node();
    	node.item=item;
    	node.next=first;
    	first=node;
    	N++;
    }

    public T pop(){
    	T item=first.item;
    	first=first.next;
    	N--;
    	return item;
    }

    public boolean IsEmpty(){
    	return N==0;
    }

    public int size(){
    	return N;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<T>{
    	Node cur=first;
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cur!=null;
		}

		public T next() {
			// TODO Auto-generated method stub
			T item=cur.item;
			cur=cur.next;
			return item;
		}

		public void remove() {}
    	
    }
    
    public static void main(String[] args){
        In in = new In("E:\\jwork\\algs4-data\\tobe.txt");
        String[] strs= in.readAllStrings();
        Stack<String> s= new Stack<String>();
        for(String str: strs){
            if(!str.equals("-")) s.push(str);
            else if(!str.isEmpty()) System.out.println(s.pop()+" ");
        }
        System.out.println(s.size()+" left in the stack");
    }
}
