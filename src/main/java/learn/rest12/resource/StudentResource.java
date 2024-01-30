package learn.rest12.resource;

import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import learn.rest12.model.Student;
import learn.rest12.service.StudentService;

@Path("/student")
public class StudentResource {

	@Inject
	StudentService studentService;
	
	@Path("/list")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getStudentList() {

		String studentList = this.studentService.getStudentList().stream().map(Student::toString)
				.collect(Collectors.joining("\n"));

		return Response.status(200).entity(studentList).build();
	}

	@Path("/create")
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createStudent(@FormParam("name") String name, @FormParam("age") int age,
			@FormParam("grade") String grade, @FormParam("email") String email) {
		
		this.studentService.addStudent(new Student(name, age, grade, email));
		return Response.status(200).build();
		
	}

	@Path("/find{id}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response findOneStudent(@PathParam("id") int id) {
		
		String studentFound = "Doesn't exist!";
		try {
			studentFound = this.studentService.findOne(id).toString();
		} catch (Exception e) {}
		
		return Response.status(200).entity(studentFound).build();
	}
	
	@Path("/delete{id}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteStudent(@PathParam("id") int id) {
		System.out.println(id);
		this.studentService.deleteStudent(id);
		return Response.status(200).build();
	}
	
	@Path("/update")
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public Response udpateStudent(String input) {
		
		String[] inputSplit = input.split(",");
		Student student = new Student(Integer.parseInt(inputSplit[0]), inputSplit[1], Integer.parseInt(inputSplit[2]) ,inputSplit[3] ,inputSplit[4]);
		studentService.updateStudent(student);
		return Response.status(200).build();
	}
}
