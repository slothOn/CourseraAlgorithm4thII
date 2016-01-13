package work05;
//Brute force implementation
import chapter1.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
//y坐标优先
public class PointSET {
		private SET<Point2D> set;
	   public   PointSET() {
		   // construct an empty set of points 
		   set=new SET<Point2D>();
	   }
	   public   boolean isEmpty() {
		   // is the set empty? 
		   return set.isEmpty();
	   }
	   public   int size() {
		   // number of points in the set
		   return set.size();
	   }
	   public   void insert(Point2D p) {
		   // add the point to the set (if it is not already in the set)
		   set.add(p);
	   }
	   public   boolean contains(Point2D p)  {
		   // does the set contain point p? 
		   return set.contains(p);
	   }
	   
	   public   void draw() {
		   // draw all points to standard draw 
		   for(Point2D p:set){
			   StdDraw.point(p.x(), p.y());
		   }
	   }
	   
	   public   Iterable<Point2D> range(RectHV rect) {
		   // all points that are inside the rectangle 
	       Queue<Point2D> queue=new Queue<Point2D>();
		   for(Point2D point:set){
	    	   if(rect.contains(point))
	    		   queue.enqueue(point);
	       }
		   return queue;
	   }
	   public Point2D nearest(Point2D p) {
		   // a nearest neighbor in the set to point p; null if the set is empty 
		  double dist, mindist=Double.MAX_VALUE;
		  Point2D minpt=null;
		  for(Point2D point:set){
			  dist=p.distanceSquaredTo(point);
			  if(dist<mindist) {
				  minpt=point;
				  mindist=dist;
			  }
		  }
		  return minpt;
	   }

	   public static void main(String[] args) {
		   // unit testing of the methods (optional) 
		   PointSET set=new PointSET();
		   In input=new In("E:\\jwork\\algorithm\\Part2\\kdtree\\circle10.txt");
		   String[] lines=input.readAllLines();
		   for(int i=0;i<lines.length;i++){
			   set.insert(new Point2D(Double.valueOf(lines[i].split(" ")[0]), Double.valueOf(lines[i].split(" ")[1])));
		   }
		   System.out.println(set.nearest(new Point2D(.81, .30)));
	   }
	}

