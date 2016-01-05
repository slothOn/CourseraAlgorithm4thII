package chapter6;

import chapter3.BinarySearchST;

//B树的结点,每个内部结点除了root都要维持M/2到M-1个key, 此外B树也是一棵平衡树
public class Page<Key extends Comparable<Key>> {
	BinarySearchST<Key, Page<Key>> keys;
	private boolean bottom;
	private int M=6;
	public  Page(boolean bottom){
		//create and open a page
		this.bottom=bottom;
		keys=new BinarySearchST<Key, Page<Key>>(M);
	}
	
	public void close(){
		//close the page,将内容写回外部页
		
	}
	
	public void add(Key key){
		//将键插入(外部的)页中
		keys.put(key, new Page(true));//即便是set也不应允许null值,对应contains操作
	}
	
	public void add(Page p){
		//打开p,向这个(内部)页中插入一个条目并将p和p中的最小键相关联
		Key k=(Key) p.keys.min();
		keys.put(k, p);
	}
	
	public boolean isExternal(){
		//外部页吗
		return bottom;
	}
	
	public boolean contains(Key key){
		//key在页中?
		return keys.contains(key);
	}
	
	public Page next(Key key){
		//可能含有key的子树
		int rank=keys.rank(key);
		return keys.get(keys.select(rank-1));
	}
	
	public boolean isFull(){
		//页是否已溢出
		int N=keys.size();
		if(N == M) return true;
		return false;
	}
	
	public Page split(){
		//将较大的中间键移动到一个新平台中
		int i=M/2;
		Page sp=new Page(this.bottom);
	    Key mid=keys.select(M/2);
	    Key hi=keys.select(M-1);
	    for(Key key:keys.keys(mid, hi)){
	    	sp.add(key);
	    	keys.delete(key);
	    }
	    return sp;
	}
	
	public Iterable<Key> keys(){
		//页中key遍历
		return keys.keys();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
