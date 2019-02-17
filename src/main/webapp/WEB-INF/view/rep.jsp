<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>report</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type = "text/javascript" src = "https://www.gstatic.com/charts/loader.js">
    </script>
    <script type = "text/javascript">
        google.charts.load('current', {packages: ['corechart']});
    </script>
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
      <td id="y${rep.id}">${rep.year}</td>
      <td id="f${rep.id}">${rep.fac}</td>
      <td id="s${rep.id}">${rep.stud}</td>
    </tr>
  </c:forEach>
    <tr>
      <td>Total:</td>
      <td></td>
      <td>${resultCount}</td>
    </tr>
  </tbody>
</table>
<div class="row">
    <div class="col-md-6">
        <a href="/" class="btn btn-warning">Home</a>
    </div>
    <div class="col-md-6">
        <form action="/excel" class="form-horizontal col-md-6 col-md-offset-3" method="post">
            <input type="hidden" id="repId" name="repName" value="${report}">
            <button type="submit" class="btn btn-danger">Export to excel</button>
        </form>
    </div>
</div>
<br>
<br>
<br>
<div id = "container" style = "width: 700px; height: 350px; ">
    <script language = "JavaScript">
        function drawChart() {
            // Define the chart to be drawn.
            var i = 0;
            console.log('count is ' + '${finalCount}');
            var myArray = new Array(['Year','Count']);
           // myArray.push(['Year','Count']);
            console.log(myArray);
            for(i=1;i < '${finalCount+1}'; i++)
            {
                var year = document.getElementById('y'+ i);
                var studNum = document.getElementById('s'+ i);
                var facName = document.getElementById('f'+ i);
                console.log('y'+ i);
                console.log(studNum);
                if(facName.innerHTML !== 'result:')
                    myArray.push([year.innerHTML,parseInt(studNum.innerHTML)]);
            }
            let myMap = new Map(myArray);
            console.log(myMap);
            var sort = new Array();
            for(i = 0; i < myArray.length; i++)
            {
                marker = true;
                console.log(myArray[i]);
                if(sort.length === 0)
                    sort.push(myArray[i]);
                else {
                    for(z = 0; z <sort.length; z++)
                    {

                        if(myArray[i][0] !== sort[z][0])
                            continue;
                        else {
                            var sum = myArray[i][1]+sort[z][1];
                            console.log('z = ' + z);
                            console.log('sort before = ' + sort);
                            sort.splice(z,1);
                            sort.push([myArray[i][0],sum]);
                            console.log('sort after = ' + sort);
                            marker = false;
                            break;
                        }

                    }
                    if(marker)
                        sort.push(myArray[i]);
                }
            }
            sort.sort(function(a,b) {
                return parseInt(a) - parseInt(b);
            });

            var data = google.visualization.arrayToDataTable(sort);
/*
            var data = google.visualization.arrayToDataTable([
                ['Year', 'Count'],
                ['2012',  900],
                ['2013',  1000],
                ['2014',  1170],
                ['2015',  1250],
                ['2016',  1530]
            ]);
*/
            var options = {title: 'Number of students'};

            // Instantiate and draw the chart.
            var chart = new google.visualization.BarChart(document.getElementById('container'));
            chart.draw(data, options);
        }
        google.charts.setOnLoadCallback(drawChart);
    </script>
</body>
</html>
