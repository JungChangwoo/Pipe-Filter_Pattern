package Components.Course;

import java.io.IOException;

import Framework.CommonFilterImpl;
import Utility.StudentList;
import Utility.Writer;

public class AddDeleteCourseFilter extends CommonFilterImpl {
	StudentList sStudentList = new StudentList();
	String[] courses;
	boolean is;
	int num;

	public AddDeleteCourseFilter(String course1, String course2, boolean is) {
		this.is = is;
		this.num = 2;
		this.courses = new String[this.num];
		this.courses[0] = course1;
		this.courses[1] = course2;
	}

	public AddDeleteCourseFilter(String course, boolean is) {
		this.is = is;
		this.num = 1;
		this.courses = new String[this.num];
		this.courses[0] = course;
	}

	@Override
	public boolean specificComputationForFilter() throws IOException {
		sStudentList.Read(in);
		switch (this.num) {
		case 1:
			addDeleteCourse();
			break;
		case 2:
			addDeleteCourse2();
			break;
		default:
			break;
		}
		
		new Writer().outWrite(out, sStudentList.Out());
		return true;
	}

	private void addDeleteCourse() {
		if (is) {
			sStudentList.addCourseFilter(courses[0]);
		} else {
			sStudentList.deleteCourseFilter(courses[0]);
		}
	}
	private void addDeleteCourse2() {
		if (is) {
			sStudentList.addCourse2Filter(this.courses);
		} else {
			sStudentList.deleteCourse2Filter(this.courses);
		}
	}
	
}