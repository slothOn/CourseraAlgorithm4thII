import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    //private double[][] pic;
    private int[][] path;
    private float[][] dist;
   private Picture picture;	
   private int transposetimes;
   
   public SeamCarver(Picture picture){
	   // create a seam carver object based on the given picture
	   if(picture==null) throw new NullPointerException();
	   this.picture= new Picture(picture);
	   transposetimes = 0;
	   init();  
	}
   
   private void init(){
	   if(transposetimes%2 == 0){
		   //pic= new double[picture.height()][picture.width()];
		   path=new int[picture.height()][picture.width()];
		   dist=new float[picture.height()][picture.width()];
		   for(int i=0;i<picture.height();i++){
			   for(int j=0;j<picture.width();j++){
				   //pic[i][j]=energy(j, i);
				   path[i][j]=-1;
				   dist[i][j]=Float.MAX_VALUE;
			   }	   
		   }
	   }else{
		   //pic= new double[picture.width()][picture.height()];
		   path= new int[picture.width()][picture.height()];
		   dist=new float[picture.width()][picture.height()];
		   for(int i=0;i<picture.width();i++){
			   for(int j=0;j<picture.height();j++){
				   //pic[i][j]=energy(i, j);
				   path[i][j]=-1;
				   dist[i][j]=Float.MAX_VALUE;
			   }	   
		   }
	   }
   }
   public Picture picture(){
	   // current picture
	   return new Picture(this.picture);
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
	   if(x<0||x>picture.width()-1||y<0||y>picture.height()-1) throw new IndexOutOfBoundsException();
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
	   if(height()==1) return new int[]{0};
	   if(width()==1) return new int[height()];
	   //如果transposetimes为奇,就转置
	   if(transposetimes%2==1){
		   transpose();
		   transposetimes++;
	   }
	   return findSeam();
   }
   
   private int[] findSeam(){
	   
	   for(int j=0;j<dist[0].length;j++){
		    dist[0][j]=0;
	   }
	   
	   for(int i=0;i<dist.length-1;i++){
		   for(int j=0;j<dist[0].length;j++){
			   relax(i,j);
		   }
	   }
	   double min=Double.MAX_VALUE; int minindex=-1;
	   for(int j=0;j<dist[0].length;j++){
		   if(dist[dist.length-1][j]<min){
			   min=dist[dist.length-1][j];
			   minindex=j;
		   }
	   }
	   
	   int[] resultpath= new int[path.length];
	   resultpath[path.length-1]=minindex;
	   for(int i=dist.length-2;i>=0;i--){
		   resultpath[i]=path[i+1][minindex];
		   minindex=resultpath[i];
	   }
	   return resultpath;
   }
   //row i, col j
   private double energyAt(int i, int j){
	   if(transposetimes%2 == 0) return energy(j, i);
	   else return energy(i,j);
   }
   private void relax(int i, int j){
	   if(i==dist.length-1){
		   return;
	   }
	   if(j==0){
		   if(dist[i+1][j]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j]=j;
		   }
		   if(dist[i+1][j+1]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j+1]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j+1]=j;
		   }
		   return;
	   }else if(j==dist[0].length-1){
		   if(dist[i+1][j]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j]=j;
		   }
		   if(dist[i+1][j-1]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j-1]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j-1]=j;
		   }
		   return;
	   }else{
		   if(dist[i+1][j]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j]=j;
		   }
		   if(dist[i+1][j-1]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j-1]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j-1]=j;
		   }
		   if(dist[i+1][j+1]>dist[i][j]+energyAt(i,j)){
			   dist[i+1][j+1]=(float) (dist[i][j]+energyAt(i,j));
			   path[i+1][j+1]=j;
		   }
		   return;
	   }
   		   
   }
   
   private void transpose(){
	   
	   float[][] dist2= new float[dist[0].length][dist.length];
	   int[][] path2= new int[path[0].length][path.length];
	   dist=null; path=null;
	   for(int i=0;i<dist2.length;i++){
		   for(int j=0;j<dist2[0].length;j++){
			   path2[i][j]=-1;
			   dist2[i][j]=Float.MAX_VALUE;
		   }
	   }
	   dist=dist2;path=path2;	   
   }
   
   public int[] findHorizontalSeam(){
	   
	  if(width()==1) return new int[]{0};
	  if(height()==1) return new int[width()];
	   if(transposetimes%2==0){
		   transpose();
		   transposetimes++;
	   }
      int[] rh=findSeam();
 
      return rh;
   }
 
   private boolean ifVerticalSeamValid(int[] seam){
	   if(seam[0]<0||seam[0]>width()-1) return false;
	   for(int i=1;i<seam.length;i++){
		   if(seam[i]<0||seam[i]>width()-1) return false;
		   if ( ( ( seam[i]-seam[i-1] )>1 )||( ( seam[i]-seam[i-1] )<-1 ) ){
			   return false;
		   }
	   }
	   return true;
   }
   
   private boolean ifHorizontalSeamValid(int[] seam){
	   if(seam[0]<0||seam[0]>height()-1) return false;
	   for(int i=1;i<seam.length;i++){
		   if(seam[i]<0||seam[i]>height()-1) return false;
		   if ( ( ( seam[i]-seam[i-1] )>1 )||( ( seam[i]-seam[i-1] )<-1 ) ){
			   return false;
		   }
	   }
	   return true;
   }

   public void removeVerticalSeam(int[] seam){
	   if(seam==null) throw new NullPointerException();
	   if(width()<=1) throw new IllegalArgumentException();
	   if(seam.length!=height()||!ifVerticalSeamValid(seam)){
		    throw new IllegalArgumentException();   
	   }
	   
	   
       Picture rpicture=new Picture(picture.width()-1, picture.height());
       for(int i=0;i<picture.height();i++){
    	   for(int j=0;j<seam[i];j++){
    		   rpicture.set(j, i, picture.get(j, i));
    	   }
    	   for(int j=seam[i];j<picture.width()-1;j++){
    		   rpicture.set(j, i, picture.get(j+1, i));
    	   }
       }
       picture=null;
       picture=rpicture;
       init();
   }
   
   public void removeHorizontalSeam(int[] seam){
	   // remove horizontal seam from current picture
	   if(seam==null) throw new NullPointerException();
	   if(height()<=1) throw new IllegalArgumentException();
	   if(seam.length!=width()||!ifHorizontalSeamValid(seam)){
		   throw new IllegalArgumentException();   
	   }
	   
	   Picture rpicture=new Picture(picture.width(),picture.height()-1);
       for(int i=0;i<picture.width();i++){
    	   for(int j=0;j<seam[i];j++){
    		   rpicture.set(i, j, picture.get(i, j));
    	   }
    	   for(int j=seam[i];j<picture.height()-1;j++){
    		   rpicture.set(i, j, picture.get(i, j+1));
    	   }
       }
       picture=null;
       picture=rpicture;
       init();
   }

}
