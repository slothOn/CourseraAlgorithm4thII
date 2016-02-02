package CodeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.introcs.BinaryStdOut;

public class RouterConfigReader {


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("E:\\config.bin");
		FileInputStream input=new FileInputStream(f);
		byte[] b=new byte[1024];		
		/*
		int i=-1;
		while((i=input.read()) != -1){
			System.out.print(i);
		}
		*/
		input.read(b);
		for(byte bi:b){
			System.out.print(bi);
		}
	}

}
