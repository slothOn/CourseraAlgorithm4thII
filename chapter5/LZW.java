package chapter5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

public class LZW {
	//8位
	private final static int R=256;
	//12位
	private final static int T=4096;
	//用12位表示8位
	private final static int W=12;
	private BinaryIn in;
	private BinaryOut out;
	public LZW(BinaryIn in, BinaryOut out){
		this.in=in; this.out=out;
	}
	
	public void compress(){
		String input=in.readString();
		TST<Integer> st= new TST<Integer>();
		for(int i=0;i<R;i++){
			st.put((char)i+"", i);
		}
		int code=R+1;
		while(input.length()>0){
			String s =st.longestPrefixOf(input);
			out.write(st.get(s), W);
			int t=s.length();
			if(t<input.length()&&code<T){
				st.put(input.substring(0, t+1), code++);
			}
			input=input.substring(t);
		}
		out.write(R, W);
		out.close();
	}
	
	public void compress2(){
		TST<Integer> st= new TST<Integer>();
		for(char i=0;i<R;i++){
			st.put(i+"", (int)i);
		}
		String s=in.readString();
		int code=R;
		int t;
		while(s.length()>0)
		{
			String pref=st.longestPrefixOf(s);
			out.write(st.get(pref), W);
			t= pref.length();
			if(code<T-1&&t<s.length())
				st.put(s.substring(0,t+1), ++code);
			s=s.substring(t);
		}
		out.write(R, W);
		out.close();
	}
	public void expand(){
		String[] table= new String[T];
		for(int i=0;i<R;i++){
			table[i]=(char)i+"";
		}
		int code=R, code1, code2;
		//不考虑第一个为EOF, 初始化
		code1=in.readInt(W);
		out.write(table[code1]);
		while(true){
			code2=in.readInt(W);
			if(code2==R) break;
			if(table[code2]==null){
				table[code2]=table[code1]+table[code1].charAt(0);
			}
			out.write(table[code2]);
			if(code<T-1)		table[++code]=table[code1]+table[code2];
			code1=code2;
		}
		out.close();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		LZW lzw=new LZW(new BinaryIn("/media/zxc/Personal Affairs/jwork/algs4-data/tale.txt"), new BinaryOut("/media/zxc/Personal Affairs/jwork/algs4-data/LZWtale.bin"));
		lzw.compress();
		*/
		LZW lzw=new LZW(new BinaryIn("/media/zxc/Personal Affairs/jwork/algs4-data/LZWtale.bin"), new BinaryOut("/media/zxc/Personal Affairs/jwork/algs4-data/EXtale.bin"));
		lzw.expand();
	}

}
