<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Specialty</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="myModal" class="modal">
    <div class="modal-content">
    <span class="close">&times;</span>
        <form action="/spec" class="form-horizontal col-md-6 col-md-offset-3" method="post">
            <label for="specNameId">New speciality</label>
            <input name="specName" class="form-control" id="specNameId" placeholder="Enter speciality...">
            <label for="sell1">Select faculty</label>
            <select class="form-control" name="facName" id="sell1">
                <c:forEach var="facs" items="${faculties}">
                    <option value="${facs.id}">${facs.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" id="specIdId" name="specId">
            <button type="submit" class="btn btn-default">Create</button>
        </form>
    </div>
</div>
<h1>Specialty</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Specialty</th>
        <th scope="col">Faculty</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="spec" items="${specialty}">
        <tr>
            <td id="${spec.id}">${spec.name}</td>
            <td>${spec.faculty}</td>
            <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editSpec(${spec.id})">Edit</a> </td>
            <td>
                <form action="/spec" class="form-horizontal col-md-6 col-md-offset-3" method="post">
                    <input type="hidden" id="specDelId" name="specId" value="${spec.id}">
                    <input type="hidden" id="specDelName" name="specName" value="TheDelete">
                    <input type="hidden" id="facDelName" name="facName" value="${spec.faculty.id}">
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
        <a href="#" class="btn btn-success" id="myBtn">Add new speciality</a>
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
    var formInput = document.getElementById("specNameId");
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
  function editSpec(id) {
    var myTab = document.getElementById(id);
    var formInput = document.getElementById("specNameId");
    var formInputId = document.getElementById("specIdId");

    console.log(id);
    formInput.value = myTab.innerHTML; //
    formInputId.value = id;
    modal.style.display = "block";

  }



</script>
</body>
</html>
