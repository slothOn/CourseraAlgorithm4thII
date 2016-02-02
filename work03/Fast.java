package work03;

import java.util.Arrays;


import edu.princeton.cs.algs4.In;

public class Fast {
	//输出j个点
	private static void output(Point[] pts, int i, int j, int start){
		//输出
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
		//startpt.drawTo(endpt);
		System.out.println();
	}
	
	
	private static void check(Point[] pts, int start){
		int j=0;
		int state=0;//记录左堆
		int i;//记录右堆
		for(i=start+2;i<pts.length;i++){
			if(pts[start].SLOPE_ORDER.compare(pts[i-1], pts[i]) == 0){
				j++;
			}else{
				if(j >= 2){
					//检查左序列中是否有共线点,左序列亦有序，所以可遍历查找
					int m;
					for(m=state;m<start;m++){
						if(pts[start].SLOPE_ORDER.compare(pts[m], pts[i-1])>= 0) break;
					}
					state=m;
					if(pts[start].SLOPE_ORDER.compare(pts[state], pts[i-1]) != 0){
						output(pts, i, j, start);
					}
				}
				j=0;
			}
		}
		//连续点对在结尾处
		if(j >= 2){
			output(pts, i, j, start);
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
			Arrays.sort(pts, 0, i, pts[i].SLOPE_ORDER);
			check(pts, i);
		}
	}

}
