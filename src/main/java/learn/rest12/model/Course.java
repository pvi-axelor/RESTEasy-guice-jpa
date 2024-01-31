package learn.rest12.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;

	@ManyToOne
	private Student student;

	private String name;

	public Course() {
		super();
	}
	
	public Course(String name) {
		super();
		this.name = name;
	}

	public Course(String name, Student student) {
		super();
		this.name = name;
		this.student = student;
	}

	public Course(int courseId, String name, Student student) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.student = student;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return courseId + "," + name;
	}

}
