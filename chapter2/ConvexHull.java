package chapter2;

import java.awt.Color;
import java.util.Comparator;

import chapter1.Queue;
import chapter1.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class ConvexHull {
	
	//专门用于比较
	class AngleComparator implements Comparator<Point2D>{
		Point2D start;
		public AngleComparator(Point2D start) {
			// TODO Auto-generated constructor stub
			this.start=start;
		}
		@Override
		public int compare(Point2D p1, Point2D p2) {
			// TODO Auto-generated method stub
			double px1=p1.x()-start.x();
			double py1=p1.y()-start.y();
			double px2=p2.x()-start.x();
			double py2=p2.y()-start.y();
			if(px1 != 0 && px2 != 0){
				double val1=py1/px1;
				double val2=py2/px2;
				if(val1<0 && val2>0) return 1;
				if(val1>0 && val2<0) return -1;
				if(val1<val2) return -1;
				return 1;
			}
			if(px1 != 0){
				if(py1/px1>0) return -1;
				return 1;
			}
			if(px2 != 0){
				if(py2/px2>0) return 1;
				return -1;
			}
			return 1;
		}
		
	}
	
	public static void drawConvexHull(Point2D[] pts){	
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(.01);
		for(int i=0;i<pts.length;i++){
			StdDraw.point(pts[i].x(), pts[i].y());
		}
		StdDraw.setPenRadius();
		for(int i=0;i<pts.length-1;i++){
			StdDraw.line(pts[i].x(), pts[i].y(), pts[i+1].x(), pts[i+1].y());		
		}
		StdDraw.line(pts[pts.length-1].x(), pts[pts.length-1].y(), pts[0].x(), pts[0].y());
		int startindex=getStart(pts);
		Point2D start=pts[startindex];
		Point2D[] npts=new Point2D[pts.length-1];
		for(int i=0;i<startindex;i++){
			npts[i]=pts[i];
		}
		for(int i=startindex;i<npts.length;i++){
			npts[i]=pts[i+1];
		}
		
		angleSort(npts, start);
		
		Point2D[] cvh=getConvexHull(npts, start);
		
		StdDraw.setPenColor(Color.RED);
		for(int i=0;i<cvh.length-1;i++){
			StdDraw.line(cvh[i].x(), cvh[i].y(), cvh[i+1].x(), cvh[i+1].y());
		}
		StdDraw.line(cvh[cvh.length-1].x(), cvh[cvh.length-1].y(), cvh[0].x(), cvh[0].y());
		
	}
	private static Point2D[] getConvexHull(Point2D[] npts, Point2D start){
		Stack<Point2D> cvh=new Stack<Point2D>();
		cvh.push(start);
		cvh.push(npts[0]);
		for(int i=1;i<npts.length-1;i++){
			Point2D ppt=cvh.peek();
			Point2D cpt=npts[i];
			Point2D npt=npts[i+1];
			while(clock(ppt, cpt, npt)){
				cpt=cvh.pop();
				ppt=cvh.peek();
			}
			cvh.push(cpt);
		}
		cvh.push(npts[npts.length-1]);
		Point2D[] cvhpts=new Point2D[cvh.size()];
		for(int i=0;i<cvhpts.length;i++){
			cvhpts[i]=cvh.pop();
		}
		return cvhpts;
	}
	private static boolean clock(Point2D ppt, Point2D cpt, Point2D npt){
		double x1=cpt.x()-ppt.x();
		double y1=cpt.y()-ppt.y();
		double x2=npt.x()-cpt.x();
		double y2=npt.y()-cpt.y();
		if(x1*y2-x2*y1<0) return true;
		return false;
	}
	
	private static Point2D[] angleSort(Point2D[] pts, Point2D start){
		//采用归并排序
		Point2D aux[]=new Point2D[pts.length];
		mergesort(pts, aux, 0, pts.length-1, start);
		return pts;
	}
	private static void mergesort(Point2D[] pts, Point2D[] aux, int lo, int hi, Point2D start){
		if(lo >= hi) return;
		int mid=(lo+hi)/2;
		mergesort(pts, aux, lo, mid, start);
		mergesort(pts, aux, mid+1, hi, start);
		merge(pts, aux, lo, hi, mid, start);
	}
	private static void merge(Point2D[] pts, Point2D[] aux, int lo, int hi, int mid, Point2D start){
		for(int i=lo;i<=hi;i++){
			aux[i]=pts[i];
		}
		for(int k=lo, i=lo, j=mid+1;k<=hi;k++){
			if(i>mid){pts[k]=aux[j++];}
			else if(j>hi){pts[k]=aux[i++];}
			else if(!less(aux[j], aux[i], start)){pts[k]=aux[i++];}
			else pts[k]=aux[j++];
		}
	}
	
	private static boolean less(Point2D p1, Point2D p2, Point2D start){
		double px1=p1.x()-start.x();
		double py1=p1.y()-start.y();
		double px2=p2.x()-start.x();
		double py2=p2.y()-start.y();
		if(px1 != 0 && px2 != 0){
			double val1=py1/px1;
			double val2=py2/px2;
			if(val1<0 && val2>0) return false;
			if(val1>0 && val2<0) return true;
			if(val1<val2) return true;
			return false;
		}
		if(px1 != 0){
			if(py1/px1>0) return true;
			return false;
		}
		if(px2 != 0){
			if(py2/px2>0) return false;
			return true;
		}
		return false;
	}
	
	private static int getStart(Point2D[] pts){
		double ymin=Double.MAX_VALUE;
		int minpt=-1;
		for(int i=0;i<pts.length;i++){
			if(pts[i].y()<ymin){
				minpt=i;
				ymin=pts[i].y();
			}
		}
		return minpt;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in=new In("/media/zxc/Personal Affairs/jwork/algorithm/Part2/kdtree/circle10.txt");
		String[] lines=in.readAllLines();
		Point2D[] pts=new Point2D[lines.length];
		for(int i=0;i<lines.length;i++){
			pts[i]=new Point2D(Double.valueOf(lines[i].split("\\s+")[0]), Double.valueOf(lines[i].split("\\s+")[1]));
		}
		drawConvexHull(pts);
	}

}
