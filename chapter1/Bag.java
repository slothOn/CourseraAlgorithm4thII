package chapter1;

import java.util.Iterator;

/**
 * Created by zxc on 2015/11/29.
 */
public class Bag<Item> implements Iterable<Item> {
	private class Node<Item>{
		Item data;
		Node next;
	}
	private Node first;
	private int N;
    public Bag(){
    	N=0;
    	first= null;
    }
    public void add (Item item){
    	Node node= new Node<Item>();
    	node.data= item;
    	node.next=first; first= node;
    	N++;
    }

    public boolean isEmpty(){
    	return N==0;
    }

    public int size(){
    	return N;
    }
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new BagIterator();
	}
	
	private class BagIterator implements Iterator<Item>{
		private Node cur= first;
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cur!=null;
		}

		public Item next() {
			// TODO Auto-generated method stub
			Item result= (Item) cur.data;
			cur=cur.next;
			return result;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
   
}
