package Utility;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public class Writer {
	
	public void outWrite(PipedOutputStream out, char[] outResult) throws IOException {
		for(int i=0; i<outResult.length; i++) {
			out.write(outResult[i]);
		}
	}
	public void outWrite(PipedOutputStream out, ArrayList<Integer> outResult) throws IOException {
		for(int i=0; i<outResult.size(); i++) {
			out.write(outResult.get(i));
		}
	}
	
}
