package chapter6;


//B树集合的实现
public class BTreeSET<Key extends Comparable<Key>>{
	private Page<Key> root=new Page<Key>(true);
	//哨兵键
	public BTreeSET(Key sentinel){
		add(sentinel);
	}
	
	public boolean contains(Key key){
		return contains(root, key);
	}
	private boolean contains(Page h, Key key){
		if(h.isExternal()) return h.contains(key);
		return contains(h.next(key), key);
	}
	
	public void add(Key key){
		add(root, key);
		if(root.isFull()){
			Page<Key> lefthalf=root;
			Page<Key> righthalf=root.split();
			root=new Page<Key>(false);
			root.add(lefthalf);
			root.add(righthalf);
		}
	}
	private void add(Page h, Key key){
		if(h.isExternal()){
			h.add(key);
			return;
		}
		Page next=h.next(key);
		add(next, key);
		if(next.isFull()){
			h.add(next.split());
		}
		next.close();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTreeSET<String> btree=new BTreeSET<String>("*");
		String[] str={"S","E","A","R","C","H","E","X","A","M","P","L","E"};
		for(int i=0;i<str.length;i++){
			btree.add(str[i]);
		}
		System.out.println(btree.contains("S"));
	}

}
