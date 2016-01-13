package chapter3;

public class SparseVector {
	private SeparateChainingHashST<Integer, Double> hashst;
	public SparseVector(){
		hashst=new SeparateChainingHashST<Integer, Double>();
	}
	
	public Iterable<Integer> indicies(){
		return hashst.keys();
	}
	
	public double get(int i){
		return hashst.get(i);
	}
	
	public void put(int i, double val){
		hashst.put(i, val);
	}
	
	public double dot(Double[] vector){
		double sum=0.0;
		for(int i:indicies()){
			sum += vector[i]*get(i);
		}
		return sum;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
