<%--
Created by IntelliJ IDEA.
User: sergd
Date: 27.11.2019
Time: 9:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ds" uri="dorozhkoTag" %>

<c:set var="language" value="${ds:localeValue(lang, cookie['lang'].value)}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="userProfile.">
    <html>
    <head>
        <title><fmt:message key="titel.autorisation"/> </title>
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

                <div class="col-sm-6" style="background: #f2f2f2; border-radius: 3px">
                    <form action="autorisation.html" role="form" class="needs-validation" novalidate method="post">


                        <label style="background-color: B82303; font-size: 25px">${message}</label>

                        <div class="form-group has-feedback">
                            <label for="uname" class="control-label col-xs-3" style="font-size: 25px;"><fmt:message key="fields.login"/></label>
                            <input type="text" class="form-control" id="uname" placeholder="<fmt:message key="fields.login"/>"
                                   name="login" value="${userLogin}" required autofocus pattern="^[\w-]{1,20}$">
                            <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                            <label style="background-color: B82303; font-size: 25px">${errorLogin}</label>
                        </div>

                        <div class="form-group">
                            <label for="pwd" style="font-size: 25px"><fmt:message key="fields.password"/></label>
                            <input type="password" class="form-control" id="pwd"
                                   placeholder="<fmt:message key="fields.password"/>" name="password" required autofocus pattern="^[\w\dа-яА-Я-+%$@!]{1,50}$">
                            <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                            <label style="background-color: B82303; font-size: 25px">${errorPassword}</label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-9 col-sm-offset-3">
                                <span class="help-block">*<fmt:message key="fields.requiredFields"/></span>
                            </div>
                        </div>
                        <div class="mx-auto">
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.logIn"/></button>

                            <button type="reset" class="btn btn-danger"><fmt:message key="button.reset"/></button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-3"></div>
            </div>
        </div>

    </div>
    <c:import url="endOfPages.jsp"/>
    <script>
        // Disable form submissions if there are invalid fields
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Get the forms we want to add validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();

    </script>
    </body>
    </html>
</fmt:bundle>