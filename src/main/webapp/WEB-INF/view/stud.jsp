<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Students table</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add new speciality</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/stud" class="form-horizontal col-md-6 col-md-offset-3" accept-charset="UTF-8" method="post">
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
                    <button type="submit" class="btn btn-default">Save changes</button>
                </form>
            </div>
            <div class="modal-footer">
                <br>
                <br>
            </div>
        </div>
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
            <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editStud(${stud.id})" data-toggle="modal" data-target="#exampleModal">Edit</a> </td>
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
        <a href="#" class="btn btn-success" id="myBtn" data-toggle="modal" data-target="#exampleModal">Add new student</a>
    </div>
</div>
<script>
    var btn = document.getElementById("myBtn");




    // When the user clicks the button, open the modal
    btn.onclick = function() {
        var formInputName = document.getElementById("studNameId");
        var formInputPhone = document.getElementById("studPhoneId");
        var formInputYear = document.getElementById("studYearId");
        var formInputId = document.getElementById("studIdId");

        formInputName.value = "";
        formInputId.value = "";
        formInputPhone.value = "";
        formInputYear.value = "";
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
