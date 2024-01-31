package learn.rest12.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	private String name;
	private int age;
	private String grade;
	private String email;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	private Set<Course> courses;

	public Student() {
		super();
	}

	public Student(int studentId) {
		super();
		this.studentId = studentId;
	}

	public Student(String name, int age, String grade, String email, Set<Course> courses) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.email = email;
		this.courses = courses;
	}

	public Student(int studentId, String name, int age, String grade, String email, Set<Course> courses) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.email = email;
		this.courses = courses;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
				
		String studentString = studentId + "," + name + "," + age + "," + grade + "," + email;
		
		for (var i : courses) {
			studentString += i.toString().substring(i.toString().indexOf(","));
		}
		
		return studentString;
	}

}
