<%--
Created by IntelliJ IDEA.
User: sergd
Date: 27.11.2019
Time: 9:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Autorisation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 40px; padding-bottom: 120px; color: #4B0082" background="images/background.jpg">

<c:import url="headOfPages.jsp"/>

<div class="container">
    <div>

        <div class="row" style="position:center; margin-top: 5%;">

            <div class="col-sm-3"></div>

            <div class="col-sm-6" style="background: silver; border-radius: 3px">
                <form action="autorisation.html" class="was-validated" method="post" >


                    <label style="background-color: B82303; font-size: 25px">${errorLoginPassMessage}</label>

                    <div class="form-group has-feedback">
                        <label for="uname" class="control-label col-xs-3" style="font-size: 25px;">Username:</label>
                        <input type="text" class="form-control" id="uname" placeholder="Enter username"
                               name="login" required pattern="[\w]{2,}">
                        <div class="valid-feedback">Valid.</div>
                    </div>

                    <div class="form-group">
                        <label for="pwd" style="font-size: 25px">Password:</label>
                        <input type="password" class="form-control" id="pwd"
                               placeholder="Enter password" name="password" required>
                        <div class="valid-feedback">Valid.</div>
                    </div>

                    <div class="mx-auto">
                        <button type="submit" class="btn btn-primary">Log in</button>

                        <button type="reset" class="btn btn-danger">Reset</button>
                    </div>
                </form>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>

</div>
<c:import url="endOfPages.jsp"/>
</body>
</html>
