package Utility;

import java.util.ArrayList;

public class Student {
	
	protected String studentId;
    protected String name;
    protected String department;
    protected ArrayList<String> completedCoursesList = new ArrayList<String>();
    
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ArrayList<String> getCompletedCoursesList() {
		return completedCoursesList;
	}
	public void setCompletedCoursesList(ArrayList<String> completedCoursesList) {
		this.completedCoursesList = completedCoursesList;
	}
	public void addCompletedCoursesList(String course) {
		this.completedCoursesList.add(course);
	}
	public void deleteCompletedCourseList(String course) {
		for(int i=0; i<this.completedCoursesList.size(); i++) {
			if(this.completedCoursesList.get(i).equals(course)) {
				this.completedCoursesList.remove(i);
			}
		}
	}
	public boolean checkCourse(String courseId) {
		for(int i=0; i<completedCoursesList.size(); i++) {
			if(completedCoursesList.get(i).equals(courseId)) {
				return true;
			}
		}
		return false;
	}
	public boolean checkAdvancedCourse(ArrayList<String> advancedCourses) {
		boolean allIs = true;
		for(int i=0; i<advancedCourses.size(); i++) {
			boolean is = false;
			for(int j=0; j<this.completedCoursesList.size(); j++) {
				if(advancedCourses.get(i).equals(this.completedCoursesList.get(j))) {
					is = true;
				}
			}
			if(is == false) {
				allIs = false;
			}
		}
		return allIs;
	}
   
}
