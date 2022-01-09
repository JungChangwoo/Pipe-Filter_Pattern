package Utility;

import java.util.ArrayList;

public class Course {
	protected String courseId;
    protected String professor;
    protected String name;
    protected ArrayList<String> advancedCoursesList = new ArrayList<String>();
    
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getAdvancedCoursesList() {
		return this.advancedCoursesList;
	}
	public void setAdvancedCoursesList(ArrayList<String> advancedCoursesList) {
		this.advancedCoursesList = advancedCoursesList;
	}
	public void addAdvancedCoursesList(String course) {
		this.advancedCoursesList.add(course);
	}
	public void deleteCompletedCourseList(String course) {
		for(int i=0; i<this.advancedCoursesList.size(); i++) {
			if(this.advancedCoursesList.get(i).equals(course)) {
				this.advancedCoursesList.remove(i);
			}
		}
	}
    
}
