package com.mappings.hybernate.dao;

import java.util.List;

import com.mappings.hybernate.entity.Course;
import com.mappings.hybernate.entity.Instructor;
import com.mappings.hybernate.entity.InstructorDetail;
import com.mappings.hybernate.entity.Student;

public interface AppDAO {
	void save(Instructor theInstructor);
	Instructor find(int id);
	void deleteInstructorById(int id);
	InstructorDetail findInstructorDetailById(int id);
	void deleteInstructorDetailById(int id);
	List<Course> findCoursesByInstructorId(int id);
	Instructor findInstructorByIdJoinFetch(int id);
	void update(Instructor instructor);
	void update(Course course);
	Course findCourseById(int id);
	void deleteCourseById(int id);
	void save(Course theCourse);
	Course findCourseAndReviewById(int id);
	Course findCourseAndStudentByCourseId(int id);
	Student findStudentAndCoursesByStudentId(int id);
	void update(Student student);
	void deleteStudentById(int id);
}
