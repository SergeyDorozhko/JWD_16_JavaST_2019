<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ds" uri="dorozhkoTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<header id="header" style="height: 40px"/>

<c:set var="language" value="${ds:localeValue(lang, cookie['lang'].value)}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="">
    <div class="row">
        <nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark fixed-top">
            <lu class="navbar-brand">
                <img src="images/mainPage/logo2.png" alt="Logo" style="width:40px; position:absolute; left:200px" ;>


                <lo class="nav-item">
                    <div class="mx-auto" style="width: 200px;">

                        <h4 style="color:orange;"><fmt:message key="headOfPages.titel.brand"/>
                            <small><i><em><fmt:message key="headOfPages.titel.tagline"/></em></i></small></h4>
                    </div>
                </lo>
                <lo class="nav-item">

                </lo>
            </lu>
            <div class="dropdown" style="position:absolute; top:15px; left:300px; height: 30px;">
                <button class="btn btn-outline-warning btn-sm dropdown-toggle" type="button" id="dropdownMenu2"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="headOfPages.button.menu"/>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <form action="main.html" method="get">
                        <button class="dropdown-item" type="submit"><fmt:message
                                key="headOfPages.button.home"/></button>
                    </form>
                    <form action="listOfUsers.html" method="get">
                        <button class="dropdown-item" type="submit"><fmt:message
                                key="headOfPages.button.allUsers"/></button>
                    </form>
                </div>
            </div>

            <form method="get">
                <div class="form-group">
                    <select class="form-control" onchange="this.form.submit()" name="local"
                            style=" background-color: #353535; color: orange; border-color: orange; width: 65px; height: 30px; font-size: 10px; position: absolute; top:15px; left: 380px;"
                            id="exampleFormControlSelect1">
                        <option value="ru-RU" <c:if test="${language eq 'ru-RU' }">selected</c:if>>RU</option>
                        <option value="en-EN" <c:if test="${language eq 'en-EN' }">selected</c:if>>EN</option>
                        <option value="be-BE" <c:if test="${language eq 'be-BE' }">selected</c:if>>BE</option>
                    </select>
                </div>
            </form>
            <c:set var="role" value="${authorizedUser.role.id!=2 and authorizedUser!=null}"/>
            <c:choose>
                <c:when test="${role}">
                    <div class="btn-group" style="position:absolute; top:15px; right:200px;">

                        <form action="viewUserProfile.html" method="get">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="headOfPages.button.viewAccount"/> ${authorizedUser.getLogin()}
                            </button>
                        </form>
                        <form action="logout.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="headOfPages.button.logOut"/>
                            </button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn-group" style="position:absolute; top:15px; right:200px;">
                        <form action="loginPage.html" method="get">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                    <%--                                style="position:absolute; top:15px; right:300px;"--%>
                                <fmt:message key="headOfPages.button.singIn"/>
                            </button>
                        </form>
                        <form action="registrationPage.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                    <%--                                style="position:absolute; top:15px; right:200px;"--%>
                                <fmt:message key="headOfPages.button.registration"/>
                            </button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </nav>

    </div>
</fmt:bundle>
<%--    <div class="modal fade" id="myModal" tabindex="-1" role="dialog">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>

<%--                <!-- Modal Header -->--%>
<%--                <div class="modal-header">--%>
<%--                    <h4 class="modal-title">Autorisation</h4>--%>
<%--                    <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--                </div>--%>
<%--                <form action="/action_page.php" class="was-validated">--%>
<%--                    <!-- Modal body -->--%>
<%--                    <div class="modal-body">--%>

<%--                        <div class="form-group has-feedback">--%>
<%--                            <label for="uname" class="control-label col-xs-3">Username:</label>--%>
<%--                            <input type="text" class="form-control" id="uname" placeholder="Enter username"--%>
<%--                                   name="uname" required pattern="[\w]{6,}">--%>
<%--                            <div class="valid-feedback">Valid.</div>--%>
<%--                            <div class="invalid-feedback">Please fill out this field.</div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="pwd">Password:</label>--%>
<%--                            <input type="password" class="form-control" id="pwd"--%>
<%--                                   placeholder="Enter password" name="pswd" required>--%>
<%--                            <div class="valid-feedback">Valid.</div>--%>
<%--                            <div class="invalid-feedback">Please fill out this field.</div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <!-- Modal footer -->--%>
<%--                    <div class="modal-footer">--%>
<%--                        <button type="submit" class="btn btn-primary">Log in</button>--%>
<%--                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
</header
<%--    </div>--%>

<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br>--%>
<%--<br><br>--%>
