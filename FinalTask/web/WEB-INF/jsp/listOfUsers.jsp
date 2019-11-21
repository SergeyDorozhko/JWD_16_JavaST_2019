<%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 15.11.2019
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 40px; padding-bottom: 120px; color: #4B0082" background="images/background.jpg">


<c:import url="headOfPages.jsp"/>
<div class="container" style="background: silver">

<div class="container" style="background: silver">
    <div class="row" style="position:center">

        <div class="col-sm-8">

            <div class="table-responsive">
                <table class="table table-bordered">
                    <caption>Users</caption>
                    <thead>
                    <tr>
                        <th id="id">ID</th>
                        <th id="login">Login</th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach var="element" items="${usersList}">
                        <tr>
                            <td><c:out value="${element.id}"/></td>
                            <td><c:out value="${element.login}"/></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
</div>

<c:import url="endOfPages.jsp"/>

</body>
</html>

