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
            <td>${stud.name}</td>
            <td>${stud.phone}</td>
            <td>${stud.startyear}</td>
            <td>${stud.specialty}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
