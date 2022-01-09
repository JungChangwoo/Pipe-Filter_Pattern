package Utility;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourses;
	
	public CourseList() {
		vCourses = new ArrayList<Course>();
	}
	
	public void Read(PipedInputStream in) throws IOException {
		//Student Ã³¸®
        while(true) {
        	Course readCourse = ReadLine(in);
        	if(readCourse == null) {
        		return;
        	} else {
        		if(readCourse.getCourseId() == null) {
        			continue;
        		} else {
        			vCourses.add(readCourse);
        		}
        	}
        }
	}
	
	public Course ReadLine(PipedInputStream in) throws IOException {
		Course course = new Course();

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
		String courseLine = new String(buffer);
		String[] words = courseLine.split(" ");
		
		if(words.length == 1) {
			return course;
		}
		course.setCourseId(words[0]);
		course.setProfessor(words[1]);
		course.setName(words[2]);
		char[] ca = words[words.length-1].toCharArray();
		String last = "";
		for(int i=0; i<5; i++) {
			last += ca[i];
		}
		words[words.length-1] = last;
		for(int i=3; i<words.length; i++) {
			course.addAdvancedCoursesList(words[i]);
		}

		return course;
	}

	public ArrayList<String> getAdvancedCourses(String courseId) {
		for(int i=0; i<vCourses.size(); i++) {
			if(vCourses.get(i).courseId.equals(courseId)) {
				return vCourses.get(i).getAdvancedCoursesList();
			}
		}
		return null;
	}

}
