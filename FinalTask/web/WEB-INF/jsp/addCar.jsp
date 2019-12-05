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
<fmt:bundle basename="pagecontent" prefix="addCar.">
    <html>
    <head>
        <title><fmt:message key="head"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <style>
            body {
                background-size: cover;
            }

            *[role="form"] {
                max-width: 730px;
                padding: 15px;
                margin: 0 auto;
                border-radius: 0.3em;
                background-color: #f2f2f2;
            }

            *[role="form"] h2 {
                font-family: 'Open Sans', sans-serif;
                font-size: 40px;
                font-weight: 600;
                color: #000000;
                margin-top: 2%;
                margin-bottom: 2%;
                text-align: center;
                text-transform: uppercase;
                letter-spacing: 4px;
            }

        </style>
    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #4B0082" background="images/background.jpg">

    <c:import url="headOfPages.jsp"/>

    <
    <div class="container">
            <%--    <div class="row">--%>
        <form action="saveCar.html" role="form" class="needs-validation" novalidate method="post">
            <h2><fmt:message key="head"/></h2>
            <label style="background-color: B82303; font-size: 25px">${unknownError}</label>


            <div class="form-group row">
                <label for="brand" class="col-sm-3 control-label"><fmt:message key="fields.carBrand"/>*</label>
                <div class="col-sm-6">
                    <select class="form-control" id="brand"  onchange="this.form.submit()" name="brand" required>
                        <option></option>
                        <c:forEach var="brands" items="${brandsMap}">
                            <option value="${brands.getKey()}"
                                    <c:if test="${brands.getKey() == userBrand}">selected</c:if>>${brands.value}</option>
                        </c:forEach>
                    </select></div>
                <label style="background-color: B82303; font-size: 25px">${errorBrand}</label>
            </div>
            <div class="form-group row">
                <label for="model" class="col-sm-3 control-label"><fmt:message key="fields.carModel"/>*</label>
                <div class="col-sm-6">
                    <select class="form-control" id="model" name="model" required>
                        <option></option>
                        <c:forEach var="models" items="${modelsMap}">
                            <option value="${models.getKey()}"
                                    <c:if test="${models.getKey() == userModel}">selected</c:if>>${models.value}</option>
                        </c:forEach>
                    </select></div>
                <label style="background-color: B82303; font-size: 25px">${errorModel}</label>
            </div>
            <div class="form-group row">
                <label for="climate" class="col-sm-3 control-label"><fmt:message key="fields.climate"/>*</label>
                <div class="col-sm-6">
                    <select class="form-control" id="climate" name="climate" required>
                        <option></option>
                        <c:forEach var="climateType" items="${climateTypeMap}">
                            <option value="${climateType.getKey()}"
                                    <c:if test="${climateType.getKey() == userClimate}">selected</c:if>>${climateType.value}</option>
                        </c:forEach>
                    </select></div>
                                    <label style="background-color: B82303; font-size: 25px">${errorClimate}</label>
            </div>
            <div class="form-group row">
                <label for="produced" class="col-sm-3 control-label"><fmt:message key="fields.produced"/> *</label>
                <div class="col-sm-4">
                    <input type="number" min="1950" max="2020" step="1" id="produced" name="produced" class="form-control" value="${userProduced}" required autofocus>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorProduced}</label>
            </div>



            <div class="form-group">
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">*<fmt:message key="fields.requiredFields"/></span>
                </div>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary" name="button" value="saveCar"><fmt:message key="button.save"/></button>
                <button type="reset" class="btn btn-danger"><fmt:message key="button.reset"/></button>
            </div>
        </form> <!-- /form -->
            <%--    </div>--%>


    </div>
    </div> <!-- ./container -->

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