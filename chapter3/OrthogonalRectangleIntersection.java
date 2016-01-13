package chapter3;

import chapter2.MinPQ;
//sweep line algorithm
import edu.princeton.cs.algs4.RectHV;
public class OrthogonalRectangleIntersection {
	private MinPQ<Double> xpq;
	private SeparateChainingHashST<Double, RectHV> rhashst;
	public OrthogonalRectangleIntersection() {
		// TODO Auto-generated constructor stub
		xpq=new MinPQ<Double>();
		rhashst=new SeparateChainingHashST<Double, RectHV>();
	}
	
	public void put(RectHV rect){
		xpq.insert(rect.xmin());
		xpq.insert(rect.xmax());
		rhashst.put(rect.xmin(), rect);
		rhashst.put(rect.xmax(), rect);
	}
	
	public boolean test(RectHV rect){
		double swpx;
		IntervalST<Double, String> ist=new IntervalST<Double, String>();
		while(!xpq.isEmpty()){
			swpx=xpq.delMin(); 
			RectHV r=rhashst.get(swpx);
			if(rect.xmin()<swpx&&swpx<rect.xmax()){
				if(ist.intersectsAny(rect.ymin(), rect.ymax()) != null) return true;
			}
			if(swpx == r.xmin())
				ist.put(rect.ymin(), rect.ymax(), null);
			else
				ist.delete(rect.ymin(), rect.ymax());
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrthogonalRectangleIntersection ori=new OrthogonalRectangleIntersection();
		SeparateChainingHashST<RectHV, String> rects=new SeparateChainingHashST<RectHV, String>();
		for(RectHV rect:rects.keys()){
			if(ori.test(rect)){
				System.out.println("error detected,"+rects.get(rect));
				return;
			}
			ori.put(rect);
		}
		System.out.println("no error");
	}

}
