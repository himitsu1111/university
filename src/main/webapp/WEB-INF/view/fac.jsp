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
<table class="table table-hover">
  <thead>
  <tr>
    <th scope="col">Faculties</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="fac" items="${faculties}">
    <tr>
      <td>${fac.name}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
