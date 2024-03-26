package com.mappings.hybernate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mappings.hybernate.dao.AppDAO;
import com.mappings.hybernate.entity.Course;
import com.mappings.hybernate.entity.Instructor;
import com.mappings.hybernate.entity.InstructorDetail;
import com.mappings.hybernate.entity.Review;
import com.mappings.hybernate.entity.Student;

@SpringBootApplication
public class HybernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HybernateApplication.class, args);
		
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner->{
		
			createInstructor(appDAO);
			findInstructor(appDAO);
			deleteInstructor(appDAO);
			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
			findInstructorWithCourses(appDAO);
			findCoursesForInstructor(appDAO);
			findInstructorWithCousesJoinFetch(appDAO);
			updateInstructor(appDAO);
			updateCourse(appDAO);
			deleteCourse(appDAO);
			createCourseAndReviews(appDAO);
			findCourseandReviews(appDAO);
			deleteCourseAndReviews(appDAO);
			createCourseAndStudent(appDAO);
			findCourseAndStudents(appDAO);
			findStudentAndCourses(appDAO);
			addCoursesToStudent(appDAO);
			deleteStudent(appDAO);
		};
	}
	
	private void createInstructor(AppDAO appDAO) {
//		Instructor tempInstructor=new Instructor("Chad","Darby","darby@gmail.com");
//		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.luv2code.com/youtube","Luv to code");
		Instructor tempInstructor=new Instructor("John","smith","smith@gmail.com");
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com","enjoy vlogging");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		System.out.println("Saving instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!!");
	}
	
	private void findInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding Instructor by id "+id);
		Instructor tempInstructor=appDAO.find(id);
		System.out.println("Instructor by id "+id +" is "+tempInstructor);
		System.out.println("Instructor details is : "+tempInstructor.getInstructorDetail());
	}
	
	private void deleteInstructor(AppDAO appDAO) {
		int id=5;
		System.out.println("deleting Instructor by id "+id);
		appDAO.deleteInstructorById(id);
		
		System.out.println("Done!!");
	}
	
	private void findInstructorDetail(AppDAO appDAO) {
		int id=2;
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(id);
		Instructor tempInstructor=tempInstructorDetail.getInstructor();
		System.out.println("Instructor  detail by id "+id +" is "+tempInstructorDetail);
		System.out.println("Instructor is : "+tempInstructor);
	}
	
	private void deleteInstructorDetail(AppDAO appDAO){
		int id=3;
		System.out.println("deleting Instructor detail by id "+id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!!");
	}
	
	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor=new Instructor("Faiz","Khan","faiz@gmail.com");
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com","video games");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		Course c1=new Course("Java 8 new features");
		Course c2=new Course("Spring Framework");
		
		tempInstructor.add(c1);
		tempInstructor.add(c2);
		
		System.out.println("Saving instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!!");
	}
	
	private void findInstructorWithCourses(AppDAO appDAO) {
		int id=5;
		Instructor tempInstructor=appDAO.find(id);
		System.out.println("Instructor by id "+id +" is "+tempInstructor);
		System.out.println("Instructor details is : "+tempInstructor.getCourses());
	}
	

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id=5;
		System.out.println("Finding Instructor by id "+id);
		Instructor tempInstructor=appDAO.find(id);
		System.out.println("Instructor by id "+id +" is "+tempInstructor);
		
		List<Course> couses=appDAO.findCoursesByInstructorId(id);
		tempInstructor.setCourses(couses);
		System.out.println("The associated courses are "+tempInstructor.getCourses());
	}
	

	private void findInstructorWithCousesJoinFetch(AppDAO appDAO) {
		int id=5;
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor by id "+id +" is "+tempInstructor);
		System.out.println("Instructor details is : "+tempInstructor.getInstructorDetail());
		System.out.println("The associated courses are "+tempInstructor.getCourses());
	}

	private void updateInstructor(AppDAO appDAO) {
		int id=5;
		Instructor tempInstructor=appDAO.find(id);
		tempInstructor.setLastName("Shaik");
		appDAO.update(tempInstructor);
		System.out.println("Done!!");
		
	}

	private void updateCourse(AppDAO appDAO) {
		int id=10;
		Course course=appDAO.findCourseById(id);
		course.setTitle("Java 8 new features in simple way");
		appDAO.update(course);
		System.out.println("Done!!");
	}
	private void deleteCourse(AppDAO appDAO) {
		int id=15;
		appDAO.deleteCourseById(id);
		System.out.println("Done!!");
	}
	
	private void createCourseAndReviews(AppDAO appDAO) {
		Course course=new Course("Hybernate Tutorial!!");
		course.add(new Review("Great Course !!"));
		course.add(new Review("Good job man"));
		course.add(new Review("really awful"));
		appDAO.save(course);
		System.out.print("Saved course");
	}
	private void findCourseandReviews(AppDAO appDAO) {
		int id=13;
		Course course=appDAO.findCourseAndReviewById(id);
		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id=13;
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}
	
	private void createCourseAndStudent(AppDAO appDAO) {
		Course course=new Course(" New Spring Boot 3, Spring 6 & Hibernate for Beginners");
		Student s1=new Student("John","Smith","john@gmail.com");
		Student s2=new Student("Afra","Khan","khan@gmail.com");
		Student s3=new Student("Samad","Mohammed","samadm@gmail.com");
		course.add(s1);
		course.add(s2);
		course.add(s3);
		appDAO.save(course);
		System.out.println("Saving the course "+ course);
		System.out.println("Associated students "+course.getStudents());
	}
	
	private void findCourseAndStudents(AppDAO appDAO) {
		int id=17;
		Course course=appDAO.findCourseAndStudentByCourseId(id);
		System.out.println("Course with given id is "+course);
		System.out.println("Students associated are "+course.getStudents());
	}
	
	private void findStudentAndCourses(AppDAO appDAO) {
		int id=7;
		Student student=appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student with given id is "+student);
		System.out.println("Courses associated are "+student.getCourses());
	}
	private void addCoursesToStudent(AppDAO appDAO) {
		int id=7;
		Student student=appDAO.findStudentAndCoursesByStudentId(id);
		student.add(new Course("Microservices with Node JS and React"));
		student.add(new Course("MERN From Scratch"));
		appDAO.update(student);
		System.out.println("Done");
		System.out.println("Student is "+student);
		System.out.println("Courses associated are "+student.getCourses());
	}
	private void deleteStudent(AppDAO appDAO) {
		int id=5;
		appDAO.deleteStudentById(id);
	
		System.out.println("Done");
	}
}
