<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: sergd--%>
<%--  Date: 20.11.2019--%>
<%--  Time: 14:28--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--<body style="color: #4B0082; background-color: #696969" background="images/background.jpg">--%>

<%--<div class="container">--%>
<header id="header" style="height: 40px"/>
<div class="row">
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark fixed-top">
        <lu class="navbar-brand">
            <img src="images/mainPage/logo2.png" alt="Logo" style="width:40px; position:absolute; left:200px" ;>
            <lo class="nav-item">
                <h4 style="color:orange;">Попутчик. <em>С нами удобно!</em></h4>
            </lo>
            <lo class="nav-item">

            </lo>
        </lu>
        <button type="button" class="btn btn-outline-warning btn-sm" data-toggle="modal" data-target="#myModal"
                style="position:absolute; top:15px; right:300px;">
            Log in
        </button>
        </button>
        <form class="form-inline my-2 my-lg-0">

            <button type="button" class="btn btn-outline-warning btn-sm"
                    style="position:absolute; top:15px; right:200px;">
                Registration
            </button>


        </form>

    </nav>


    <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Autorisation</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="/action_page.php" class="was-validated">
                    <!-- Modal body -->
                    <div class="modal-body">

                        <div class="form-group has-feedback">
                            <label for="uname" class="control-label col-xs-3">Username:</label>
                            <input type="text" class="form-control" id="uname" placeholder="Enter username"
                                   name="uname" required pattern="[\w]{6,}">
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd"
                                   placeholder="Enter password" name="pswd" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Log in</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</header
<%--    </div>--%>

<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br><br>--%>
