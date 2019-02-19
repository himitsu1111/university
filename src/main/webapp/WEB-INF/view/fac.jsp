<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Faculties</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <meta charset="UTF-8">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add new faculty</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/fac" id="myForm" class="form-horizontal col-md-6 col-md-offset-3" accept-charset="UTF-8" method="post" onsubmit="this.submit(); this.reset(); return false;">
                    <%--<label for="facNameId">New faculty</label>--%>
                    <input name="utf8" type="hidden" value="&#x2713;" />
                    <input name="facName" class="form-control" id="facNameId" placeholder="Enter faculty...">
                    <input type="hidden" id="facIdId" name="facId">
                    <button type="submit" class="btn btn-primary" id="submitId" >Save changes</button>
                </form>
            </div>
            <div class="modal-footer">
                <br>
                <br>
            </div>
        </div>
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
      <td><a href="#" class="btn btn-info" id="myBtn2" name="mBtn2" onclick="editFac(${fac.id})" data-toggle="modal" data-target="#exampleModal">Edit</a> </td>
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
        <a href="#" class="btn btn-success" id="myBtn" data-toggle="modal" data-target="#exampleModal">Add new faculty</a>
    </div>
</div>
<script>


    var btn = document.getElementById("myBtn");

    btn.onclick = function() {
        var formInput = document.getElementById("facNameId");
        var formInputId = document.getElementById("facIdId");
        formInputId.value = "";
        formInput.value = "";
    }


    function editFac(id) {
        var myTab = document.getElementById(id);
        var formInput = document.getElementById("facNameId");
        var formInputId = document.getElementById("facIdId");

        console.log(id);
        formInput.value = myTab.innerHTML;
        formInputId.value = id;
    }



</script>
</body>
</html>
