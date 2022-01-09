package Utility;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;

public class StudentList {
	protected ArrayList<Student> vStudent;

	public StudentList() {
		vStudent = new ArrayList<Student>();
	}
	
	public void Read(PipedInputStream in) throws IOException {
		//Student 처리
        while(true) {
        	Student readStudent = ReadLine(in);
        	if(readStudent == null) {
        		return;
        	} else {
        		if(readStudent.getStudentId() == null) {
        			continue;
        		} else {
        		vStudent.add(readStudent);
        		}
        	}
        }
	}
	
	public Student ReadLine(PipedInputStream in) throws IOException {
		Student student = new Student();

		byte[] buffer = new byte[256];
		int idx = 0;
		int byte_read = 0;
		boolean isEnd = false;
		
		while(byte_read != '\n' && byte_read != -1) {
			byte_read = in.read();
			if(byte_read == -1) isEnd = true;
			if(byte_read != -1) buffer[idx++] = (byte)byte_read;
		}
		
		if(isEnd) {
			return null;
		}
		String studentLine = new String(buffer);
		String[] words = studentLine.split(" ");
		
		if(words.length == 1) {
			return student;
		}
		student.setStudentId(words[0]);
		student.setName(words[1]+" "+words[2]);
		student.setDepartment(words[3]);
		char[] ca = words[words.length-1].toCharArray();
		String last = "";
		for(int i=0; i<5; i++) {
			last += ca[i];
		}
		words[words.length-1] = last;
		for(int i=4; i<words.length; i++) {
			student.addCompletedCoursesList(words[i]);
		}

		return student;
	}

	public char[] Out() {
		char[] out = new char[9999];
		String outString = "";
		for(int i=0; i<vStudent.size(); i++) {
			Student student = vStudent.get(i);
			String line = "";
			line += student.getStudentId();
			line += " ";
			line += student.getName();
			line += " ";
			line += student.getDepartment();
			for(int j=0; j<student.getCompletedCoursesList().size(); j++) {
				line += " ";
				line += student.getCompletedCoursesList().get(j);
			}
			line += "\n";
			outString += line;
		}
		out = outString.toCharArray();
		return out;
	}

	public void filterDepartment(String department, boolean is) {
		ArrayList<Student> filteredStudents = new ArrayList<Student>();
		for(int i=0; i<vStudent.size(); i++) {
			if(vStudent.get(i).department.equals(department) == is) {
				filteredStudents.add(vStudent.get(i));
			}
		}
		vStudent = filteredStudents;
	}
	
	public ArrayList<Student> getStudents() {
		return vStudent;
	}

	public StudentList filterCheckAdvancedCourses(CourseList sCourseList, boolean is) {
		StudentList filteredStudentList = new StudentList();
		// 학생을 한 명씩 Loop
		for(int i=0; i<vStudent.size(); i++) {
			boolean checkAdvanced = true;
			ArrayList<String> completedCourses = vStudent.get(i).getCompletedCoursesList();
			// 해당 학생의 CompletedCoursesList를 하나씩 Loop
			for(int j=0; j<completedCourses.size(); j++) {
				ArrayList<String> advancedCourses = sCourseList.getAdvancedCourses(completedCourses.get(j));
				if(advancedCourses == null) continue;
				else if(!vStudent.get(i).checkAdvancedCourse(advancedCourses)) {
					checkAdvanced = false;
				};
			}
			// true => return 선수과목이수 / false => return not 선수과목이수
			if(checkAdvanced == is) {
				filteredStudentList.vStudent.add(vStudent.get(i));
			}
		}
		return filteredStudentList;
	}

}
