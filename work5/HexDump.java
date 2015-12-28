package work5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.StdOut;

public class HexDump {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryIn in= new BinaryIn("/media/zxc/Personal Affairs/jwork/algorithm/Part2/burrowstesting/abra.bin");
		int bytesPerLine = 16;
        if (args.length == 1) {
            bytesPerLine = Integer.parseInt("64");
        }

        int i;
        for (i = 0; !in.isEmpty(); i++) {
            if (bytesPerLine == 0) {
                in.readChar();
                continue;
            }
            if (i == 0) StdOut.printf("");
            else if (i % bytesPerLine == 0) StdOut.printf("\n", i);
            else StdOut.print(" ");
            char c = in.readChar();
            StdOut.printf("%02x", c & 0xff);
        }
        if (bytesPerLine != 0) StdOut.println();
        StdOut.println((i*8) + " bits");
	}

}
