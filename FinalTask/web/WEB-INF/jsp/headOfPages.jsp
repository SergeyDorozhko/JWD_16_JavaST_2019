

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<header id="header" style="height: 40px"/>
<div class="row">
    <nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark fixed-top">
        <lu class="navbar-brand">
            <img src="images/mainPage/logo2.png" alt="Logo" style="width:40px; position:absolute; left:200px" ;>

            <div class="dropdown" style="position:absolute; top:15px; left:300px;">
                <button class="btn btn-outline-warning btn-sm dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <form action="main.html" method="get">
                    <button class="dropdown-item" type="submit">Home</button>
                    </form>
                        <form action="listOfUsers.html" method="get">
                    <button class="dropdown-item" type="submit">All users</button>
                    </form>
                </div>
            </div>




            <lo class="nav-item">
                <div class="mx-auto" style="width: 200px;">

                    <h4 style="color:orange;">Попутчик. <small><i><em>С нами удобно!</em></i></small></h4>
                </div>
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
