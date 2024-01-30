<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Information Form</title>
<script src="./script.js"></script>
<link rel="stylesheet" href="./style.css">
</head>

<body>

	<form id="studentForm" method="post"
		onsubmit="insertAStudent(); return false;">
		<label for="name">Name:</label> <input type="text" id="name"
			name="name" required> <label for="age">Age:</label> <input
			type="number" id="age" name="age" required> <label
			for="grade">Grade:</label> <input type="text" id="grade" name="grade"
			required> <label for="email">Email:</label> <input
			type="email" id="email" name="email" required>

		<button type="submit">Submit</button>
	</form>

	<br>
	<div id="studentList">
		<input type="text" placeholder="find student by id" id="findStudentID">
		<button type="button" onclick="findAStudent()">find</button>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Grade</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody id="studentListItemsFind">
				<tr>
					<td><input type="number" name="find_id" id="find_id" readonly></td>
					<td><input type="text" name="find_name" id="find_name"
						required></td>
					<td><input type="number" name="find_age" id="find_age"
						required></td>
					<td><input type="text" name="find_grade" id="find_grade"
						required></td>
					<td><input type="email" name="find_email" id="find_email"
						required></td>
				</tr>
			</tbody>
		</table>
		<br>
		<button type="button" onclick="updateAStudent()">update</button>
		<button type="button" onclick="deleteAStudent()">delete</button>
	</div>

	<br>
	<br>
	<div id="studentList">
		<h2>Student List</h2>
		<button type="button" onclick="getStudentList()">Refresh</button>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Grade</th>
					<th>Email</th>
					<!--  <th>Action</th> -->
				</tr>
			</thead>
			<tbody id="studentListItems"></tbody>
		</table>
	</div>
	<br>
	<br>

</body>
</html>
