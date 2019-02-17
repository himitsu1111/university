<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Faculties</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="myModal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <form action="/fac" class="form-horizontal col-md-6 col-md-offset-3" method="post">
      <label for="facNameId">New faculty</label>
      <input name="facName" class="form-control" id="facNameId" placeholder="Enter faculty...">
      <input type="hidden" id="facIdId" name="facId">
      <button type="submit" class="btn btn-default">Create</button>
    </form>
  </div>
</div>

<table class="table table-hover" id="tab1">
  <thead>
  <tr>
    <th scope="col">Faculties</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="fac" items="${faculties}">
    <!-- Modal content -->

    <tr>
      <td id="${fac.id}">${fac.name}</td>
      <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editFac(${fac.id})">Edit</a> </td>
      <%--onclick="editFac(${fac.id})"--%>
      <td>
        <form action="/fac" class="form-horizontal col-md-6 col-md-offset-3" method="post">
          <input type="hidden" id="facDelId" name="facId" value="${fac.id}">
          <input type="hidden" id="facDelName" name="facName" value="TheDelete">
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
        <a href="#" class="btn btn-success" id="myBtn">Add new faculty</a>
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
    modal.style.display = "block";
  }

  btn3.onclick = function() {
    facName.value = "some text";
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
  function editFac(id) {
    var myTab = document.getElementById(id);
    var formInput = document.getElementById("facNameId");
    var formInputId = document.getElementById("facIdId");

    console.log(id);
    formInput.value = myTab.innerHTML;
    formInputId.value = id;
    modal.style.display = "block";

  }



</script>
</body>
</html>
