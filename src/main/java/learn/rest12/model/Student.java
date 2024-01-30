package learn.rest12.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.print.attribute.standard.MediaSize.Other;

@Entity
public class Student {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int studentId;
	
	private String name;
	private int age;
	private String grade;
	private String email;

	public Student() {
		super();
	}

	public Student(int studentId) {
		super();
		this.studentId = studentId;
	}

	public Student(String name, int age, String grade, String email) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.email = email;
	}

	public Student(int studentId, String name, int age, String grade, String email) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.email = email;
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

	@Override
	public String toString() {
		return studentId + "," + name + "," + age + "," + grade + "," + email;
	}

}
