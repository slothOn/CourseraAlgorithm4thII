package chapter5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.StdOut;

public class BinaryDump {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int width=Integer.parseInt("16");
		int cnt;
		String str="/media/zxc/Personal Affairs/jwork/algs4-data/EXtale.bin";
		BinaryIn in=new BinaryIn(str);
		for(cnt=0; !in.isEmpty();cnt++){
			if(width==0) continue;
			if(cnt!=0 && cnt%width==0) StdOut.println();
			if(in.readBoolean()){
				StdOut.print("1");
			}else StdOut.print("0");
		}
		StdOut.println();
		StdOut.println(cnt+" bits");
	}

}
