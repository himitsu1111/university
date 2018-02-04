<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>report</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Report</h1>
<br>

<table class="table table-hover">
  <thead>
  <tr>
    <th scope="col">Year</th>
    <th scope="col">Faculty</th>
    <th scope="col">Count of students</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="rep" items="${report}">
    <tr>
      <td>${rep.year}</td>
      <td>${rep.fac}</td>
      <td>${rep.stud}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
