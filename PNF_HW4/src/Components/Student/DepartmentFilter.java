/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Student;

import java.io.IOException;
import Exception.NullDataException;
import Framework.CommonFilterImpl;
import Utility.StudentList;
import Utility.Writer;

public class DepartmentFilter extends CommonFilterImpl{
	StudentList sStudentList = new StudentList();
	String department;
	boolean is;
	
    public DepartmentFilter(String department, boolean is) {
		this.department = department;
		this.is = is;
	}
	@Override
    public boolean specificComputationForFilter() throws IOException, NullDataException {
        sStudentList.Read(in);
        sStudentList.filterDepartment(this.department, this.is);
        
        new Writer().outWrite(out, sStudentList.Out());
       
        return true;
    }  
}
