package work03;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.introcs.StdDraw;

public class Brute {
	private static void sortAndDraw(Point p, Point q, Point r, Point s){
		Point[] line=new Point[4];
		line[0]=p; line[1]=q; line[2]=r; line[3]=s;
		//Arrays.sort(line);
		Point start=null, end=null;
		for(int i=0;i<4;i++){
			if(start == null){
				start=line[i];
			}else{
				if(start.compareTo(line[i])>0) start=line[i];
			}
			if(end == null){
				end=line[i];
			}else{
				if(end.compareTo(line[i])<0) end=line[i];
			}
			System.out.print(line[i]+"->");
		}
		System.out.println();
		//start.drawTo(end);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        /*
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        */
		In in=new In("E:\\Further study in north America\\CS\\algorithmI\\collinear\\input8.txt");
		int N=in.readInt();
		Point[] pts=new Point[N];
		for(int i=0;i<N;i++){
			int x=in.readInt();
			int y=in.readInt();
			pts[i]=new Point(x, y);
			//pts[i].draw();
		}
		for(int i=0;i<pts.length;i++){
			Point p=pts[i];
			for(int j=i+1;j<pts.length;j++){
				Point q=pts[j];
				for(int k=j+1;k<pts.length;k++){
					Point r=pts[k];
					if(p.slopeTo(q) != p.slopeTo(r)) continue;
					for(int h=k+1;h<pts.length;h++){
						Point s=pts[h];
						if(p.slopeTo(s) == p.slopeTo(q)){
							sortAndDraw(p, q, r, s);
						}
					}
				}
			}
		}
		//StdDraw.show();
	}
}
