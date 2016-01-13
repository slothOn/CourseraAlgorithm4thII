package work05;

import chapter1.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	    private static boolean VERTICAL= true;
	    private static boolean HORIZONTAL=false;
		private static class Node{
			Point2D pt;
			RectHV rect;
			Node lb; //left & bottom
			Node rt;//right & top
			Node(Point2D p, RectHV r, Node lb, Node rt){
				pt=p; rect=r; this.lb=lb; this.rt=rt;
			}
		}
		private Node root;
	   public   KdTree() {
		   // construct an empty set of points 
	       root=null;
	   }
	   public   boolean isEmpty(){
		   // is the set empty? 
		   return root == null;
	   }
	   public   int size(){
		   // number of points in the set
		   return size(root);
	   }
	   private int size(Node n){
		   if(n == null) return 0;
		   return size(n.lb)+size(n.rt)+1;
	   }
	   
	   public   void insert(Point2D p) {
		   // add the point to the set (if it is not already in the set)
		   if(contains(p)) return;
		   if(root == null)
			   root=insert(root, p, VERTICAL, new RectHV(0, 0, 1, 1));
		   else 
			   root=insert(root, p, VERTICAL, root.rect);
	   }
	   private Node insert(Node n, Point2D p, boolean direct, RectHV r){
		   if(n == null) return new Node(p, r, null, null);
		   if(direct == VERTICAL){
			   if(p.x()<n.pt.x()){
				   if(n.lb == null)
					   n.lb=insert(n.lb, p, HORIZONTAL, new RectHV(r.xmin(), r.ymin(), n.pt.x(), r.ymax()));
				   else n.lb=insert(n.lb, p, HORIZONTAL, n.lb.rect);
			   }else{
				   if(n.rt == null)
						n.rt=insert(n.rt, p, HORIZONTAL, new RectHV(n.pt.x(), r.ymin(), r.xmax(), r.ymax()));
				   else n.rt=insert(n.rt, p, HORIZONTAL, n.rt.rect);
			   }
		   }else{
			   if(p.y()<n.pt.y()){
				   if(n.lb == null)
					   n.lb=insert(n.lb, p, VERTICAL, new RectHV(r.xmin(), r.ymin(), r.xmax(), n.pt.y()));
				   else
					   n.lb=insert(n.lb, p, VERTICAL, n.lb.rect);
			   }else{
				   if(n.rt == null)
					   n.rt=insert(n.rt, p, VERTICAL, new RectHV(r.xmin(), n.pt.y(), r.xmax(), r.ymax()));
				   else
					   n.rt=insert(n.rt, p, VERTICAL, n.rt.rect);
			   }
		   }
		   return n;
	   }
	   
	   public   boolean contains(Point2D p) {
		   // does the set contain point p? 
		   return contains(root, p, VERTICAL);
	   }
	   private boolean contains(Node n, Point2D p, boolean direct){
		   if(n == null) return false;
		   if(p.equals(n.pt)) return true;
		   if(direct == VERTICAL){
			   if(p.x()<n.pt.x()) return contains(n.lb, p, HORIZONTAL);
			   else return contains(n.rt, p, HORIZONTAL);
		   }else{
			   if(p.y()<n.pt.y()) return contains(n.rt, p, VERTICAL);
			   else return contains(n.rt, p, VERTICAL);
		   }
	   }
	   
	   public void draw() {
		   // draw all points to standard draw
		   if(isEmpty()) return;
		   draw(root, VERTICAL);
	   }
	   private void draw(Node n, boolean direct){
		   StdDraw.setPenColor(StdDraw.BLACK);
		   StdDraw.setPenRadius(.01);
		   StdDraw.point(n.pt.x(), n.pt.y());
		   if(direct == VERTICAL){
			   StdDraw.setPenColor(StdDraw.RED);
			   StdDraw.setPenRadius();
			   StdDraw.line(n.pt.x(), n.rect.ymin(), n.pt.x(), n.rect.ymax());
		   }else{
			   StdDraw.setPenColor(StdDraw.BLUE);
			   StdDraw.setPenRadius();
			   StdDraw.line(n.rect.xmin(), n.pt.y(), n.rect.xmax(), n.pt.y());
		   }
		   if(n.lb != null) draw(n.lb, !direct);
		   if(n.rt != null) draw(n.rt, !direct);
	   }
	   
	   public Iterable<Point2D> range(RectHV rect) {
		   // all points that are inside the rectangle 
		   Queue<Point2D> queue=new Queue<Point2D>();
		   if(!isEmpty())  range(root, rect, queue);
		   return queue;
	   }
	   private void range(Node n, RectHV rect, Queue<Point2D> queue){
		   if(rect.distanceSquaredTo(n.pt) <= 0){
			   queue.enqueue(n.pt);
		   }
		   if(n.lb != null && rect.intersects(n.lb.rect)) {
			   range(n.lb, rect, queue);   
		   }
		   if(n.rt != null && rect.intersects(n.rt.rect)){
			   range(n.rt, rect, queue);
		   }
	   }
	   
	   public Point2D nearest(Point2D p) {
		   // a nearest neighbor in the set to point p; null if the set is empty 
		   if(isEmpty()) return null;
		   Node n=nearest(root, p);
		   return n.pt;
	   }
	   private Node nearest(Node n, Point2D p){
		   double dist=p.distanceSquaredTo(n.pt), dist1=Double.MAX_VALUE, dist2=Double.MAX_VALUE;
		   Node n1, n2;
		   n1=n2=null;
		   if(n.lb != null && n.lb.rect.distanceSquaredTo(p)<dist){
			   n1=nearest(n.lb, p);
			   dist1=p.distanceSquaredTo(n1.pt);
		   }
		   if(n.rt != null && n.rt.rect.distanceSquaredTo(p)<dist){
			   n2=nearest(n.rt, p);
			   dist2=p.distanceSquaredTo(n2.pt);
		   }
		   if(dist<dist1 && dist <dist2) return n;
		   if(dist1<dist && dist1<dist2) return n1;
		   else return n2;
	   }

	   public static void main(String[] args) {
		   // unit testing of the methods (optional) 
		   KdTree set=new KdTree();
		   In input=new In("E:\\jwork\\algorithm\\Part2\\kdtree\\circle10.txt");
		   String[] lines=input.readAllLines();
		   for(int i=0;i<lines.length;i++){
			   set.insert(new Point2D(Double.valueOf(lines[i].split(" ")[0]), Double.valueOf(lines[i].split(" ")[1])));
		   }
		   set.draw();
		   System.out.println(set.nearest(new Point2D(.81, .30)));
	   }

}
