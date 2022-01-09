/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Exception.NullDataException;
import Framework.CommonFilterImpl;
import Utility.Constants.ConstantsException;
import Utility.Writer;

public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
	public boolean specificComputationForFilter() throws IOException, NullDataException {
		ArrayList<Integer> outResult = new ArrayList<Integer>();
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(sourceFile)));
		while (true) {
			int byte_read = br.read();
			outResult.add(byte_read);
			if (byte_read == -1) break;
		}

		if (outResult.size() <= 1) {
			throw new NullDataException(ConstantsException.NULLSOURCE);
		}
		new Writer().outWrite(out, outResult);
		return true;
	}
}
