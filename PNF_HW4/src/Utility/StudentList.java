package Utility;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;

import Exception.NullDataException;
import Utility.Constants.ConstantsException;

public class StudentList {
	protected ArrayList<Student> vStudent;

	public StudentList() {
		vStudent = new ArrayList<Student>();
	}

	public void Read(PipedInputStream in) throws IOException {
		// Student Ã³¸®
		while (true) {
			Student readStudent = ReadLine(in);
			if (readStudent == null) {
				return;
			} else {
				if (readStudent.getStudentId() == null) {
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

		while (byte_read != '\n' && byte_read != -1) {
			byte_read = in.read();
			if (byte_read == -1)
				isEnd = true;
			if (byte_read != -1)
				buffer[idx++] = (byte) byte_read;
		}

		if (isEnd) {
			return null;
		}
		String studentLine = new String(buffer);
		String[] words = studentLine.split(" ");

		if (words.length == 1) {
			return student;
		}
		student.setStudentId(words[0]);
		student.setName(words[1] + " " + words[2]);
		student.setDepartment(words[3]);
		char[] ca = words[words.length - 1].toCharArray();
		String last = "";
		for (int i = 0; i < 5; i++) {
			last += ca[i];
		}
		words[words.length - 1] = last;
		for (int i = 4; i < words.length; i++) {
			student.addCompletedCoursesList(words[i]);
		}

		return student;
	}

	public char[] Out() {
		char[] out = new char[9999];
		String outString = "";
		for (int i = 0; i < vStudent.size(); i++) {
			Student student = vStudent.get(i);
			String line = "";
			line += student.getStudentId();
			line += " ";
			line += student.getName();
			line += " ";
			line += student.getDepartment();
			for (int j = 0; j < student.getCompletedCoursesList().size(); j++) {
				line += " ";
				line += student.getCompletedCoursesList().get(j);
			}
			line += "\n";
			outString += line;
		}
		out = outString.toCharArray();
		return out;
	}

	public boolean filterDepartment(String department, boolean is) throws NullDataException {
		if(!registeredDepartment(department)) throw new NullDataException(ConstantsException.NULLDEPARTMENT);
		ArrayList<Student> filteredStudents = new ArrayList<Student>();
		for (int i = 0; i < vStudent.size(); i++) {
			if (vStudent.get(i).department.equals(department) == is) {
				filteredStudents.add(vStudent.get(i));
			}
		}
		vStudent = filteredStudents;
		return true;
	}

	private boolean registeredDepartment(String department) {
		for (int i = 0; i < vStudent.size(); i++) {
			if (vStudent.get(i).department.equals(department)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Student> getStudents() {
		return vStudent;
	}

	public void addCourse2Filter(String[] courses) {
		for (int i = 0; i < vStudent.size(); i++) {
			boolean checkFirst = false;
			boolean checkSecond = false;
			if (vStudent.get(i).checkCourse(courses[0]))
				checkFirst = true;
			if (vStudent.get(i).checkCourse(courses[1]))
				checkSecond = true;

			if (checkFirst == true && checkSecond == false) {
				vStudent.get(i).addCompletedCoursesList(courses[1]);
			} else if (checkFirst == false && checkSecond == true) {
				vStudent.get(i).addCompletedCoursesList(courses[0]);
			} else if (checkFirst == false && checkSecond == false) {
				vStudent.get(i).addCompletedCoursesList(courses[0]);
				vStudent.get(i).addCompletedCoursesList(courses[1]);
			}
		}
	}

	public void deleteCourse2Filter(String[] courses) {
		for (int i = 0; i < vStudent.size(); i++) {
			boolean checkFirst = false;
			boolean checkSecond = false;
			if (vStudent.get(i).checkCourse(courses[0]))
				checkFirst = true;
			if (vStudent.get(i).checkCourse(courses[1]))
				checkSecond = true;

			if (checkFirst == true && checkSecond == false) {
				vStudent.get(i).deleteCompletedCoursesList(courses[0]);
			} else if (checkFirst == false && checkSecond == true) {
				vStudent.get(i).deleteCompletedCoursesList(courses[1]);
			} else if (checkFirst == true && checkSecond == true) {
				vStudent.get(i).deleteCompletedCoursesList(courses[0]);
				vStudent.get(i).deleteCompletedCoursesList(courses[1]);
			}
		}
	}

	public boolean addCourseFilter(String course) {
		for (int i = 0; i < vStudent.size(); i++) {
			if (!vStudent.get(i).checkCourse(course))
				vStudent.get(i).addCompletedCoursesList(course);
		}
		return true;
	}

	public boolean deleteCourseFilter(String course) {
		for (int i = 0; i < vStudent.size(); i++) {
			if (vStudent.get(i).checkCourse(course))
				vStudent.get(i).deleteCompletedCoursesList(course);
		}
		return true;
	}

}
