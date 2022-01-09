/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import java.util.ArrayList;

import Framework.CommonFilterImpl;
import Utility.Course;
import Utility.CourseList;
import Utility.StudentList;
import Utility.Writer;

public class MiddleFilter extends CommonFilterImpl{
	StudentList sStudentList = new StudentList();
	CourseList sCourseList = new CourseList();
	boolean is;
	
    public MiddleFilter() {
		
	}
	@Override
    public boolean specificComputationForFilter() throws IOException {
        sStudentList.Read(inArray.get(0));
        sCourseList.Read(inArray.get(1));
        StudentList studentList1 = sStudentList.filterCheckAdvancedCourses(sCourseList, true);
        StudentList studentList2 = sStudentList.filterCheckAdvancedCourses(sCourseList, false);
        
        Writer writer = new Writer();
        writer.outWrite(outArray.get(0), studentList1.Out());
        writer.outWrite(outArray.get(1), studentList2.Out());
       
        return true;
    }  
}
