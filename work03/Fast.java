package work03;

import java.util.Arrays;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Fast {
	private static void check(Point[] pts, int start){
		int j=0;
		for(int i=start+2;i<pts.length;i++){
			if(pts[start].SLOPE_ORDER.compare(pts[i-1], pts[i]) == 0){
				j++;
			}else{
				if(j >= 2){
					Point[] line=new Point[j+2];
					for(int k=i-1, t=0;k>=i-1-j;k--, t++){
						line[t]=pts[k];
					}
					Point startpt=null, endpt=null;
					line[j+1]=pts[start];
					for(int t=0;t<line.length;t++){
						if(startpt == null){
							startpt=line[t];
						}else{
							if(startpt.compareTo(line[t])>0){
								startpt=line[t];
							}
						}
						if(endpt == null){
							endpt=line[t];
						}else{
							if(endpt.compareTo(line[t])<0){
								endpt=line[t];
							}
						}
						System.out.print(line[t]+"->");
					}
					startpt.drawTo(endpt);
					System.out.println();
				}
				j=0;
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in=new In("E:\\Further study in north America\\CS\\algorithmI\\collinear\\input6.txt");
		int N=in.readInt();
		Point[] pts=new Point[N];
		for(int i=0;i<N;i++){
			int x=in.readInt();
			int y=in.readInt();
			pts[i]=new Point(x, y);
			//pts[i].draw();
		}
		for(int i=0;i<pts.length-3;i++){
			Arrays.sort(pts, i+1, pts.length, pts[i].SLOPE_ORDER);
			check(pts, i);
		}
	}

}
