<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students table</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <form action="/stud" class="form-horizontal col-md-6 col-md-offset-3" method="post">
            <label for="studNameId">New speciality</label>
            <input name="studName" class="form-control" id="studNameId" placeholder="Enter name...">
            <input name="studPhone" class="form-control" id="studPhoneId" placeholder="Enter phone...">
            <input name="studYear" class="form-control" id="studYearId" placeholder="Enter year...">
            <label for="sell1">Select speciality</label>
            <select class="form-control" name="specName" id="sell1">
                <c:forEach var="spec" items="${specialty}">
                    <option value="${spec.id}">${spec.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" id="studIdId" name="studId">
            <button type="submit" class="btn btn-default">Create</button>
        </form>
    </div>
</div>
<h1>Students</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Phone</th>
        <th scope="col">Year</th>
        <th scope="col">Specialty</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="stud" items="${students}">
        <tr>
            <td id="${stud.id}">${stud.name}</td>
            <td id="ph${stud.id}">${stud.phone}</td>
            <td id="ye${stud.id}">${stud.startyear}</td>
            <td>${stud.specialty}</td>
            <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editStud(${stud.id})">Edit</a> </td>
            <td>
                <form action="/stud" class="form-horizontal col-md-6 col-md-offset-3" method="post">
                    <input type="hidden" id="studDelId" name="studId" value="${stud.id}">
                    <input type="hidden" id="studDelName" name="studName" value="TheDelete">
                    <input type="hidden" id="studDelYear" name="studYear" value="TheDelete">
                    <input type="hidden" id="studDelPhone" name="studPhone" value="TheDelete">
                    <input type="hidden" id="facDelName" name="specName" value="${stud.specialty.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="row">
    <div class="col-md-6">
        <a href="/" class="btn btn-warning">Home</a>
    </div>
    <div class="col-md-6">
        <a href="#" class="btn btn-success" id="myBtn">Add new student</a>
    </div>
</div>
<script>
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    var btn2 = document.getElementById("myBtn2");
    var btn3 = document.getElementsByName("mBtn2");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    var facName = document.getElementsByName("facName");


    // When the user clicks the button, open the modal
    btn.onclick = function() {
        var formInput = document.getElementById("studNameId");
        formInput.value = "";
        modal.style.display = "block";
    }


    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    function editStud(id) {
        var myTab = document.getElementById(id);
        var myTabPh = document.getElementById("ph" + id);
        var myTabYe = document.getElementById("ye" + id);
        var formInputName = document.getElementById("studNameId");
        var formInputPhone = document.getElementById("studPhoneId");
        var formInputYear = document.getElementById("studYearId");
        var formInputId = document.getElementById("studIdId");

        console.log(id);
        formInputName.value = myTab.innerHTML;
        formInputId.value = id;
        formInputPhone.value = myTabPh.innerHTML;
        formInputYear.value = myTabYe.innerHTML;
        modal.style.display = "block";

    }
</script>
</body>
</html>
