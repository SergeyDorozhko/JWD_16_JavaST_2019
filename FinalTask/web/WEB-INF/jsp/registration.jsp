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
        <title><fmt:message key="titel.registration"/></title>
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


    <div class="container">
            <%--    <div class="row">--%>
        <form action="createAccount.html" role="form" class="needs-validation" novalidate method="post">
            <h2><fmt:message key="titel.registration"/></h2>
            <label style="background-color: #B82303; font-size: 25px">${unknownError}</label>
            <div class="form-group row">
                <label for="login" class="col-sm-3 control-label"><fmt:message key="fields.login"/>*</label>
                <div class="col-sm-9 some-form__line">
                    <input type="text" id="login" name="login" placeholder="<fmt:message key="fields.login"/>"
                           class="form-control" value="${userLogin}"
                           required>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorLogin}</label>
            </div>
            <div class="form-group row">
                <label for="firstName" class="col-sm-3 control-label"><fmt:message key="fields.firstName"/>*</label>
                <div class="col-sm-9">
                    <input type="text" id="firstName" name="firstName"
                           placeholder="<fmt:message key="fields.firstName"/> " class="form-control"
                           value="${userFirstName}" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorFirstName}</label>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-sm-3 control-label"><fmt:message key="fields.lastName"/>*</label>
                <div class="col-sm-9">
                    <input type="text" id="lastName" name="lastName" placeholder="<fmt:message key="fields.lastName"/>" class="form-control"
                           value="${userLastName}" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorLastName}</label>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-3 control-label"><fmt:message key="fields.email"/>* </label>
                <div class="col-sm-9">
                    <input type="email" id="email" name="email" placeholder="<fmt:message key="fields.email"/>" class="form-control" name="email"
                           value="${userEmail}" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorEmail}</label>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-3 control-label"><fmt:message key="fields.password"/>*</label>
                <div class="col-sm-9">
                    <input type="password" id="password" name="password" placeholder="<fmt:message key="fields.password"/>" class="form-control"
                           required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorPassword}</label>
            <div class="form-group row">
                <label for="confirmPassword" class="col-sm-3 control-label"><fmt:message key="fields.passwordComfirm"/>*</label>
                <div class="col-sm-9">
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="<fmt:message key="fields.password"/>"
                           class="form-control" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
            </div>
            <div class="form-group row">
                <label for="birthDate" class="col-sm-3 control-label"><fmt:message key="fields.dateOfBirth"/>*</label>
                <div class="col-sm-4">
                    <input type="date" id="birthDate" name="birthDate" class="form-control" value="${userBirthday}"
                           required
                           autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorBirthday}</label>
            </div>
            <div class="form-group row">
                <label for="phoneNumber" class="col-sm-3 control-label"><fmt:message key="fields.phoneNumber"/>*</label>
                <div class="col-sm-6">
                    <input type="phoneNumber" id="phoneNumber" name="phoneNumber" placeholder="375XXYYYYYYY"
                           class="form-control" value="${userPhoneNumber}" pattern="[0-9]{12}" required>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorPhoneNumber}</label>
            </div>
            <div class="form-group row">
                <label for="country" class="col-sm-3 control-label"><fmt:message key="fields.country"/>*</label>
                <div class="col-sm-6">
                    <select class="form-control" id="country" name="country" required>
                        <option></option>
                        <c:forEach var="countries" items="${countriesMap}">
                            <option value="${countries.getKey()}"
                                    <c:if test="${ds:equalsValue(userCountry, countries.getKey())}">selected</c:if>>${countries.value}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorCountry}</label>
            </div>
            <div class="form-group row">
                <label for="passportNumber" class="col-sm-3 control-label"><fmt:message
                        key="fields.passportNumber"/>*</label>
                <div class="col-sm-6">
                    <input type="tel" id="passportNumber" name="passportNumber" placeholder="<fmt:message
                        key="fields.passportNumber"/>"
                           class="form-control" value="${userPassportNumber}" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorPassportNumber}</label>
            </div>
            <div class="form-group row">
                <label for="passportDate" class="col-sm-3 control-label"><fmt:message
                        key="fields.passportDateOfIssue"/>*</label>
                <div class="col-sm-4">
                    <input type="date" id="passportDate" name="passportDate" class="form-control"
                           value="${userPassportDate}" required autofocus>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorPassportDate}</label>
            </div>
            <div class="form-group row">
                <label for="sex" class="col-sm-3 control-label"><fmt:message key="fields.sex"/>*:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="sex" name="sex" required>
                        <option></option>
                        <c:forEach var="gender" items="${gendersMap}">
                            <option value="${gender.getKey()}"
                                    <c:if test="${ds:equalsValue(userSex, gender.getKey())}">selected</c:if>>${gender.value}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorSex}</label>
            </div>

            <div class="form-group">
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">*<fmt:message key="fields.requiredFields"/></span>
                </div>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary" name="button" value="register"><fmt:message key="button.register"/></button>
                <button type="reset" class="btn btn-danger"><fmt:message key="button.reset"/></button>
            </div>
        </form> <!-- /form -->
            <%--    </div>--%>


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