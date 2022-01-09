/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */
package Framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public interface CommonFilter extends Runnable{
    public void connectOutputTo(CommonFilter filter) throws IOException;
    public void connectOutputTo(CommonFilter filter, int portNum) throws IOException;
    public void connectInputTo(CommonFilter filter) throws IOException;
    public void connectInputTo(CommonFilter filter, int portNum) throws IOException;
    public void connectOutputToArray(CommonFilter filter, int portNum) throws IOException;
    public void connectInputToArray(CommonFilter filter, int portNum) throws IOException;
    public PipedInputStream getPipedInputStream();
    public PipedOutputStream getPipedOutputStream();
    public ArrayList<PipedInputStream> getPipedInputStreamArray();
    public ArrayList<PipedOutputStream> getPipedOutputStreamArray();
    public void makeInputPipes(int portNum);
    public void makeOutputPipes(int portNum);
}
