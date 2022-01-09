/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Course.AddDeleteCourseFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
import Components.Student.DepartmentFilter;

public class LifeCycleManager {
	private static CommonFilter sourceFilter;
	private static CommonFilter sinkFilter;
	private static CommonFilter departmentFilter;
	private static CommonFilter addDeleteCourseFilter;
	
	private static Thread threadSource;
	private static Thread threadSink;
	private static Thread threadDepartment;
	private static Thread threadCourse;
	
    public static void main(String[] args) {
        try {
            sourceFilter = new SourceFilter("Students.txt");
            sinkFilter = new SinkFilter("Output.txt");
            departmentFilter = new DepartmentFilter("CS", true);
            addDeleteCourseFilter = new AddDeleteCourseFilter("12345", "23456", true);
            
            sourceFilter.connectOutputTo(departmentFilter);
            departmentFilter.connectOutputTo(addDeleteCourseFilter);
            addDeleteCourseFilter.connectOutputTo(sinkFilter);
            
            execute(sourceFilter, sinkFilter, departmentFilter, addDeleteCourseFilter);
            
            sourceFilter = new SourceFilter("Students.txt");
            sinkFilter = new SinkFilter("Output2.txt");
            departmentFilter = new DepartmentFilter("EE", true);
            addDeleteCourseFilter = new AddDeleteCourseFilter("23456", true);
            
            sourceFilter.connectOutputTo(departmentFilter);
            departmentFilter.connectOutputTo(addDeleteCourseFilter);
            addDeleteCourseFilter.connectOutputTo(sinkFilter);
            
            execute(sourceFilter, sinkFilter, departmentFilter, addDeleteCourseFilter);
            
            sourceFilter = new SourceFilter("Students.txt");
            sinkFilter = new SinkFilter("Output3.txt");
            departmentFilter = new DepartmentFilter("CS", false);
            addDeleteCourseFilter = new AddDeleteCourseFilter("17651", "17652", false);
            
            sourceFilter.connectOutputTo(departmentFilter);
            departmentFilter.connectOutputTo(addDeleteCourseFilter);
            addDeleteCourseFilter.connectOutputTo(sinkFilter);
            
            execute(sourceFilter, sinkFilter, departmentFilter, addDeleteCourseFilter);      
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	private static void execute(CommonFilter sourceFilter, CommonFilter sinkFilter, CommonFilter departmentFilter, CommonFilter addDeleteCourseFilter) {
		threadSource = new Thread(sourceFilter);
		threadSink = new Thread(sinkFilter);
		threadDepartment = new Thread(departmentFilter);
		threadCourse = new Thread(addDeleteCourseFilter);
		
		threadSource.start();
		threadSink.start();
		threadDepartment.start();
		threadCourse.start();
	}
}
