package chapter6;
//linear programming 寻找最大解
public class Simplex {
	private double[][] a;
	private int m, n;
	public Simplex(double[][] A, double[] b, double[] c){
		//初始状态
		m= A.length; n= A[0].length;
		a= new double[m+1][m+n+1];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				a[i][j]= A[i][j];
		for(int i=0;i<m;i++)	a[i][m+n]= b[i];
		for(int i=0;i<n;i++)	a[m][i]= c[i];
		for(int i=0;i<m;i++)	a[i][n+i]= 1;
	}
	//找到待调整的列,如果没有返回-1,表明已找到最优解
	private int bland(){
		for(int q=0;q<m+n;q++){
			if(a[m][q]>0) return q;
		}
		return -1;
	}
	//找到待调整的行,L内不应出现负值,only consider the positive entry
	private int minRatioRule(int q){
		int rp= -1;
		double min=Double.MAX_VALUE, pmin;
		for(int i=0;i<m;i++){
			if(a[i][q]<=0) continue;
			pmin= a[i][m+n]/a[i][q]; 
			if(pmin<min){
				min= pmin; rp= i;
			}
		}
		return rp;
	}
	//一个pivot到另一个pivot状态调整(消元法)
	private void pivot(int p, int q){
		double div = a[p][q], numerator;
		for(int j=0;j<m+n+1;j++){
			a[p][j] = a[p][j]/div;
		}
		for(int i=0;i<m+1;i++){
			if(i == p){
				continue;
			}
			numerator= a[i][q];
			for(int j=0;j<m+n+1;j++){
				a[i][j] = a[i][j] - a[p][j]*numerator;
			}
		}
		a[p][q] = 1.0;
	}
	
	public void solve(){
		int p, q;
		while(true){
			q = bland();
			if(q == -1) {
				System.out.printf("%.2f",-a[m][m+n]);
				break;
			}
			p = minRatioRule(q);
			if(p == -1) throw new IllegalArgumentException("unbounded"); 
			pivot(p, q);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] A= {{5,15},{4,4},{35,20}};
		double[] b= {480,160,1190};
		double[] c= {13, 23};
		Simplex simplex= new Simplex(A, b, c);
		simplex.solve();
	}

}
