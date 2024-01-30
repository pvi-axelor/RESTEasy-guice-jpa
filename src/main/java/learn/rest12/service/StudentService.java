package learn.rest12.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.google.inject.Inject;

import learn.rest12.model.Student;

public class StudentService {
	
	@Inject
	private EntityManager entityManager;
	private EntityTransaction transaction = null;

	public List<Student> getStudentList() {
//		List<Student> objectList = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
//		System.out.println(objectList.stream().map(Student::toString).collect(Collectors.joining("\n")));
		return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	}

	
	
	public void addStudent(Student s) {
		if (transaction == null)
			transaction = entityManager.getTransaction();
					
		try {
			transaction.begin();
			entityManager.persist(s);			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				entityManager.getTransaction().rollback();
		}
	}

	public Student findOne(int id) {
		return entityManager.find(Student.class, id);
	}
	
	public void updateStudent(Student s) {
		if (transaction == null)
			transaction = entityManager.getTransaction();
					
		try {
			transaction.begin();
			entityManager.merge(s);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				entityManager.getTransaction().rollback();
		}
	}
	
	public void deleteStudent(int id) {
		if (transaction == null)
			transaction = entityManager.getTransaction();
					
		try {
			transaction.begin();
			entityManager.remove(entityManager.find(Student.class, id));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null)
				entityManager.getTransaction().rollback();
		}
	}
}
