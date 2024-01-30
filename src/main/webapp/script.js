function hitit() {
	var findId = document.getElementById("findStudentID");
	window.location.href = "./student/find" + findId.value;
}

function findAStudent() {

	var findID = document.getElementById("findStudentID").value;
	console.log(findID);

	// Use AJAX to find a student from id
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "./student/find" + findID, true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {

			table = document.getElementById("studentListItemsFind");

			listString = xhr.responseText;
			console.log(listString)
			
			if (listString == "Doesn't exist!") {
				document.getElementById("find_name").value = "Doesn't exist!";
				return;				
			}
				

			// example input
			// 12,a,16,8,a@b.d

			// split the list
			listStringParts = listString.split(",");
			
			
			var foundStudentID = document.getElementById("find_id");
			var foundStudentName = document.getElementById("find_name");
			var foundStudentAge = document.getElementById("find_age");
			var foundStudentGrade = document.getElementById("find_grade");
			var foundStudentEmail = document.getElementById("find_email");

			foundStudentID.value = listStringParts[0];
			foundStudentName.value = listStringParts[1];
			foundStudentAge.value = listStringParts[2];
			foundStudentGrade.value = listStringParts[3];
			foundStudentEmail.value = listStringParts[4];

		}
	};

	xhr.send();
}

function getStudentList() { // ajax request to get the list
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "./student/list", true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {

			console.log(xhr.responseText);
			table = document.getElementById("studentListItems");

			// to empty the table before refreshing
			table.innerHTML = "";

			listString = xhr.responseText;

			// split the list
			listStringParts = listString.split("\n");

			// then split the internal parts
			listStringParts.forEach(e => {
				innerStringParts = e.split(",");

				var newRow = "<tr>";
				innerStringParts.forEach(f => {
					newRow += "<td>" + f + "</td>";
				});
				
			//	newRow += "<td><button type=\"button\">delete</button></td>";				
				newRow += "</tr>";

				table.innerHTML += newRow;
			});
		}
	};

	xhr.send();
}

function insertAStudent() {
	var formData = new FormData(document.getElementById("studentForm"));

	// parameters to string version
	var urlEncodedData = new URLSearchParams(formData).toString();
	console.log(urlEncodedData);

	// now creating ajax fcnction to save
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "./student/create", true);

	//  content type should match
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	// finally send it
	xhr.send(urlEncodedData);
}

function updateAStudent() {
	var updateStudent = "";

	var foundStudentID = document.getElementById("find_id");
	var foundStudentName = document.getElementById("find_name");
	var foundStudentAge = document.getElementById("find_age");
	var foundStudentGrade = document.getElementById("find_grade");
	var foundStudentEmail = document.getElementById("find_email");

	updateStudent = foundStudentID.value + "," + foundStudentName.value + "," + foundStudentAge.value + "," + foundStudentGrade.value + "," + foundStudentEmail.value;
	console.log(updateStudent);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "./student/update", true);

	//  content type should match
	xhr.setRequestHeader("Content-Type", "text/plain");

	// finally send it
	xhr.send(updateStudent);
	
	
	foundStudentName.value = "Updated!";
	foundStudentID.value = "";
	foundStudentAge.value = "";
	foundStudentGrade.value = "";
	foundStudentEmail.value = "";

}

function deleteAStudent() {
	var foundStudentID = document.getElementById("find_id").value;
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", "./student/delete" + foundStudentID, true);

	//  content type should match
	xhr.setRequestHeader("Content-Type", "text/plain");

	// finally send it
	xhr.send();
	
	document.getElementById("find_id").value = "";
	document.getElementById("find_age").value = "";
	document.getElementById("find_grade").value = "";
	document.getElementById("find_email").value = "";
	document.getElementById("find_name").value = "Deleted!";
	
}