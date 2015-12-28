package chapter1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by zxc on 2015/11/29.
 */
public class Queue<T> implements Iterable<T> {
    class Node{
    	T item;
    	Node next;
    }
    private Node first;
    private Node last;
    private int N;
	public Queue(){
		N=0;
		first=last=null;
    }

    public void enqueue(T item){
    	Node n= new Node();
    	n.item=item;
    	if(first==null){
    		first=last=n;
    	}else{
    		last.next=n;
    		last=n;
    	}
    	N++;
    }

    public T dequeue(){
    	T data= first.item;
    	if(first==last){
    		first=last=null;
    	}else{
    		first=first.next;
    	}
    	N--;
    	return data;
    }

    public boolean isEmpty(){
    	return first==null;
    }

    public int size(){
    	return N;
    }

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<T>{
		Node cur=first;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cur!=null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T data= cur.item;
			cur= cur.next;
			return data;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		
		}
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Queue<String> q=new Queue<String>();
		File f=new File("E:\\jwork\\TestFixedCapacityStack.txt");
		Scanner scan=new Scanner(f);
		while(scan.hasNext()){
			String s=scan.next();
			if(s.equals("-")){
				if(q.isEmpty()) System.out.println("The queue is empty");
				System.out.println(q.dequeue()+" ");
			}
			else{
				q.enqueue(s);
			}
		}
		System.out.println(q.size()+" left on queue");
	}
}
