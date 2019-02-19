<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>

    <title>Specialty</title>
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
                <form action="/spec" class="form-horizontal col-md-6 col-md-offset-3" accept-charset="UTF-8" method="post">
                    <input name="specName" class="form-control" id="specNameId" placeholder="Enter speciality...">
                    <label for="sell1">Select faculty</label>
                    <select class="form-control" name="facName" id="sell1">
                    <c:forEach var="facs" items="${faculties}">
                    <option value="${facs.id}">${facs.name}</option>
                    </c:forEach>
                    </select>
                    <input type="hidden" id="specIdId" name="specId">
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
            <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editSpec(${spec.id})" data-toggle="modal" data-target="#exampleModal">Edit</a> </td>
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
        <a href="#" class="btn btn-success" id="myBtn" data-toggle="modal" data-target="#exampleModal">Add new speciality</a>
    </div>
</div>
<script>

  var btn = document.getElementById("myBtn");

  btn.onclick = function() {
    var formInput = document.getElementById("specNameId");
    formInput.value = "";
      var formInputId = document.getElementById("specIdId");
      formInputId.value = "";
  }

  function editSpec(id) {
    var myTab = document.getElementById(id);
    var formInput = document.getElementById("specNameId");
    var formInputId = document.getElementById("specIdId");

    formInput.value = myTab.innerHTML; //
    formInputId.value = id;


  }



</script>
</body>
</html>
