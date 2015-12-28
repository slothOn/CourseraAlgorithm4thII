package work2;


import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    
   private Picture picture;	
   public SeamCarver(Picture picture){
	   // create a seam carver object based on the given picture
	   this.picture= picture;
   }
   
   public Picture picture(){
	   // current picture
	   return picture;
   }
   
   public int width(){
	   // width of current picture
	   return picture.width();
   }
   
   public int height(){
	   // height of current picture
	   return picture.height();
   }
   
   public double energy(int x, int y){
	   // energy of pixel at column x and row y
	   if(x==0||x==picture.width()-1||y==0||y==picture.height()-1) return 1000;
	   double rx, ry, gx, gy, bx, by, result;
	   rx=picture.get(x+1, y).getRed()- picture.get(x-1, y).getRed();
	   ry=picture.get(x, y+1).getRed()- picture.get(x, y-1).getRed();
	   gx=picture.get(x+1, y).getGreen()- picture.get(x-1, y).getGreen();
	   gy=picture.get(x, y+1).getGreen()- picture.get(x, y-1).getGreen();
	   bx=picture.get(x+1, y).getBlue()- picture.get(x-1, y).getBlue();
	   by=picture.get(x, y+1).getBlue()- picture.get(x, y-1).getBlue();
	   result=Math.sqrt(rx*rx+gx*gx+bx*bx+ry*ry+gy*gy+by*by);
	   return result;
   }
   
   public int[] findVerticalSeam(){
	   return findVerticalSeam(this.picture);
   }
   
   public int[] findVerticalSeam(Picture picture){
	   // sequence of indices for vertical seam
	   int[] rseq= new int[picture.height()];
	   int num= picture.width()*(picture.height()+1);
	   EdgeWeightedDigraph G= new EdgeWeightedDigraph(num+2);
	   for(int i=0;i<picture.width();i++){
		   G.addEdge(new DirectedEdge(num, i, 0));
		   G.addEdge(new DirectedEdge(i+picture.width()*picture.height(), num+1, 0));
	   }
	   for(int i=0;i<picture.width();i++){
		   for(int j=0;j<picture.height();j++){
			   addVerticalEdges(G, i, j);
		   }
	   }
	   AcyclicSP sp= new AcyclicSP(G, num);
	   int index=-1;
	   for(DirectedEdge edge:sp.pathTo(num+1)){
		   if(index==-1||index==picture.height()) index++;
		   else{
			   rseq[index]=edge.from()%picture.width();
			   index++;
		   }
	   }
	   return rseq;
   }
   
   private void addVerticalEdges(EdgeWeightedDigraph G, int i, int j){
	   int num1= j*picture.width()+i;
	   int num2= (j+1)*picture.width()+i-1;
	   int num3= (j+1)*picture.width()+i;
	   int num4= (j+1)*picture.width()+i+1;
	   G.addEdge(new DirectedEdge(num1, num3, energy(i, j)));
	   if(i==0){
		   G.addEdge(new DirectedEdge(num1, num4, energy(i, j)));
	   }else if(i==picture.width()-1){
		   G.addEdge(new DirectedEdge(num1, num2, energy(i, j)));
	   }else{
		   G.addEdge(new DirectedEdge(num1, num4, energy(i, j)));
		   G.addEdge(new DirectedEdge(num1, num2, energy(i, j)));
	   }
   }
   
   public int[] findHorizontalSeam(){
	   Picture pic= new Picture(picture.height(), picture.width());
	   for(int i=0; i<pic.width(); i++){
		   for(int j=0; j<pic.height(); j++){
			   pic.set(i, j, picture.get(j, i));
		   }
	   }
	   return findVerticalSeam(pic);
   }

   public void removeVerticalSeam(int[] seam){
       
   }
   
   public void removeHorizontalSeam(int[] seam){
	   // remove horizontal seam from current picture
   }
   
	

}
