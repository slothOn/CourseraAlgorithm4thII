package chapter5;

import edu.princeton.cs.algs4.BinaryDump;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
//游程长度编码,先0后1,记录个数
public class RunLength {
	private final static int R=255;//maximum run-length count
	private final static int lgR=8;//num of bits per count
	//压缩比特流
	public static void compress(BinaryIn in){
		boolean old=false;
		char cnt=0;//记录0或1的个数
		while(!in.isEmpty()){
			if(in.readBoolean()!=old){
				//reset
				BinaryStdOut.write(cnt);
				cnt=0;
				old=!old;
			}else if(cnt==R){
				BinaryStdOut.write(cnt);
				BinaryStdOut.write(0);
				cnt=0;
			}
			cnt++;
		}
		BinaryStdOut.write(cnt);
		BinaryStdOut.close();
	}
	//解压
	public static void expand(BinaryIn in){
		boolean old=false;
		while(!in.isEmpty()){
			int cnt=in.readChar(lgR);
			for(int i=0;i<cnt;i++){
				BinaryStdOut.write(false);
			}
			old=!old;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinaryIn in=new BinaryIn("/media/zxc/Personal Affairs/jwork/algs4-data/abra.txt");
		//BinaryIn in=new BinaryIn("E:\\config.bin");
		compress(in);
		
	}

}
