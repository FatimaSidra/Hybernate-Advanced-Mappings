package com.mappings.hybernate.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mappings.hybernate.entity.Course;
import com.mappings.hybernate.entity.Instructor;
import com.mappings.hybernate.entity.InstructorDetail;
import com.mappings.hybernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class AppDAOImpl implements AppDAO{
	
	private EntityManager entityManager ; //define field for entity manager
	
	//inject entity manager using constructor injection
	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	
	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		entityManager.persist(theInstructor);
	}


	@Override
	public Instructor find(int id) {
		return entityManager.find(Instructor.class, id);
	}


	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		Instructor  tempInstructor=entityManager.find(Instructor.class, id);
		List<Course> courses=tempInstructor.getCourses();
		for(Course course:courses) {
			course.setInstructor(null);
		}
		entityManager.remove(tempInstructor);
		
	}


	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		return entityManager.find(InstructorDetail.class, id);
	}
	@Transactional
	public void deleteInstructorDetailById(int id) {
		InstructorDetail  tempInstructorDetail=entityManager.find(InstructorDetail.class, id);
		//remove associated object references 
		//break bi-directional link
		tempInstructorDetail.getInstructor().setInstructorDetail(null);		
		entityManager.remove(tempInstructorDetail);
	}


	@Override
	public List<Course> findCoursesByInstructorId(int id) {
		//create query
		TypedQuery<Course> query=entityManager.createQuery("from Course where instructor.id=:data",Course.class);
		query.setParameter("data", id);
		//execute query
		List<Course> courses=query.getResultList();
		return courses;
	}


	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {
		TypedQuery<Instructor> query=entityManager.createQuery("select i from Instructor i join fetch i.courses join fetch i.instructorDetail where i.id=:data",Instructor.class);
		query.setParameter("data", id);
		Instructor instructor=query.getSingleResult();
		return instructor;
	}


	@Override
	@Transactional
	public void update(Instructor instructor) {
		entityManager.merge(instructor);
	}


	@Override
	@Transactional
	public void update(Course course) {
		entityManager.merge(course);
		
	}


	@Override
	public Course findCourseById(int id) {
		return entityManager.find(Course.class, id);
	}


	@Override
	@Transactional
	public void deleteCourseById(int id) {
		Course course=	entityManager.find(Course.class,id);
		entityManager.remove(course);
	}


	@Override
	@Transactional
	public void save(Course theCourse) {
		entityManager.persist(theCourse);
	}


	@Override
	public Course findCourseAndReviewById(int id) {
		TypedQuery<Course> query=entityManager.createQuery("select c from Course c join fetch c.reviews where c.id=:data",Course.class);
		query.setParameter("data", id);
		return query.getSingleResult();
	}


	@Override
	public Course findCourseAndStudentByCourseId(int id) {
		TypedQuery<Course> query=entityManager.createQuery("select c from Course c join fetch c.students where c.id=:data",Course.class);
		query.setParameter("data", id);
		Course course=query.getSingleResult();
		return course;
	}
	public  Student findStudentAndCoursesByStudentId(int id) {
		TypedQuery<Student> query=entityManager.createQuery("select s from Student s join fetch s.courses where s.id=:data",Student.class);
		query.setParameter("data", id);
		Student student=query.getSingleResult();
		return student;
	}
	@Transactional
	public void update(Student student) {
		entityManager.merge(student);
	}

	@Override
	@Transactional
	public void deleteStudentById(int id) {
		Student student=entityManager.find(Student.class, id);
		entityManager.remove(student);
	}
	
	
}
