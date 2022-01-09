/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

import Exception.NullDataException;

public abstract class CommonFilterImpl implements CommonFilter {
	protected PipedInputStream in = new PipedInputStream();
	protected PipedOutputStream out = new PipedOutputStream();
	protected ArrayList<PipedInputStream> inArray = new ArrayList<PipedInputStream>();
	protected ArrayList<PipedOutputStream> outArray = new ArrayList<PipedOutputStream>();
	
	public void makeInputPipes(int portNum) {
		for(int i=0; i<portNum; i++) {
			PipedInputStream in = new PipedInputStream();
			inArray.add(in);
		}
	}
	public void makeOutputPipes(int portNum) {
		for(int i=0; i<portNum; i++) {
			PipedOutputStream out = new PipedOutputStream();
			outArray.add(out);	
		}
	}
	public void connectOutputTo(CommonFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream());
	}
	public void connectOutputTo(CommonFilter nextFilter, int portIdx) throws IOException {
		out.connect(nextFilter.getPipedInputStreamArray().get(portIdx));
	}
	public void connectInputTo(CommonFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream());
	}
	public void connectInputTo(CommonFilter previousFilter, int portIdx) throws IOException {
		in.connect(previousFilter.getPipedOutputStreamArray().get(portIdx));
	}
	public void connectOutputToArray(CommonFilter nextFilter, int portIdx) throws IOException {
		outArray.get(portIdx).connect(nextFilter.getPipedInputStream());
	}
	public void connectInputToArray(CommonFilter previousFilter, int portIdx) throws IOException {
		inArray.get(portIdx).connect(previousFilter.getPipedOutputStream());
	}
	public ArrayList<PipedInputStream> getPipedInputStreamArray(){
		return inArray;
	}
	public ArrayList<PipedOutputStream> getPipedOutputStreamArray(){
		return outArray;
	}
	public PipedInputStream getPipedInputStream() {
		return in;
	}
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}
	abstract public boolean specificComputationForFilter() throws IOException, NullDataException;
	// Implementation defined in Runnable interface for thread
	public void run() {
		try {
			specificComputationForFilter();
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} catch (NullDataException e) {
			e.printStackTrace();
		} finally {
			closePorts();
		}
	}
	private void closePorts() {
		try {
			in.close();
			out.close();
			for(int i=0; i<inArray.size(); i++) {
				outArray.get(i).close();
				inArray.get(i).close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
