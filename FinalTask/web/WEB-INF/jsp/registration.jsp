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
    <title>Registration</title>
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
        <h2>Registration</h2>
        <label style="background-color: #B82303; font-size: 25px">${unknownError}</label>
        <div class="form-group row">
            <label for="login" class="col-sm-3 control-label">Login*</label>
            <div class="col-sm-9 some-form__line">
                <input type="text" id="login" name="login" placeholder="Login" class="form-control" value="${userLogin}"
                       required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorLogin}</label>
        </div>
        <div class="form-group row">
            <label for="firstName" class="col-sm-3 control-label">First Name*</label>
            <div class="col-sm-9">
                <input type="text" id="firstName" name="firstName" placeholder="First Name" class="form-control"
                       value="${userFirstName}" required autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorFirstName}</label>
        </div>
        <div class="form-group row">
            <label for="lastName" class="col-sm-3 control-label">Last Name*</label>
            <div class="col-sm-9">
                <input type="text" id="lastName" name="lastName" placeholder="Last Name" class="form-control"
                       value="${userLastName}" required autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorLastName}</label>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-3 control-label">Email* </label>
            <div class="col-sm-9">
                <input type="email" id="email" name="email" placeholder="Email" class="form-control" name="email"
                       value="${userEmail}" required autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorEmail}</label>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-3 control-label">Password*</label>
            <div class="col-sm-9">
                <input type="password" id="password" name="password" placeholder="Password" class="form-control"
                       required autofocus>
            </div>
        </div>
        <label style="background-color: B82303; font-size: 25px">${errorPassword}</label>
        <div class="form-group row">
            <label for="confirmPassword" class="col-sm-3 control-label">Confirm Password*</label>
            <div class="col-sm-9">
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password"
                       class="form-control" required autofocus>
            </div>
        </div>
        <div class="form-group row">
            <label for="birthDate" class="col-sm-3 control-label">Date of Birth*</label>
            <div class="col-sm-4">
                <input type="date" id="birthDate" name="birthDate" class="form-control" value="${userBirthday}" required
                       autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorBirthday}</label>
        </div>
        <div class="form-group row">
            <label for="phoneNumber" class="col-sm-3 control-label">Phone number*</label>
            <div class="col-sm-6">
                <input type="phoneNumber" id="phoneNumber" name="phoneNumber" placeholder="375XXYYYYYYY"
                       class="form-control" value="${userPhoneNumber}" pattern="[0-9]{12}" required>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorPhoneNumber}</label>
        </div>
        <div class="form-group row">
            <label for="country" class="col-sm-3 control-label">Country*</label>
            <div class="col-sm-6">
                <select class="form-control" id="country" name="country" required>
                    <option></option>
                    <c:forEach var="countries" items="${countriesMap}">
                        <option value="${countries.getKey()}"
                                <c:if test="${countries.getKey() == userCountry}">selected</c:if>>${countries.value}</option>
                    </c:forEach>
                </select></div>
            <label style="background-color: B82303; font-size: 25px">${errorCountry}</label>
        </div>
        <div class="form-group row">
            <label for="passportNumber" class="col-sm-3 control-label">Passport number*</label>
            <div class="col-sm-6">
                <input type="tel" id="passportNumber" name="passportNumber" placeholder="Passport Number"
                       class="form-control" value="${userPassportNumber}" required autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorPassportNumber}</label>
        </div>
        <div class="form-group row">
            <label for="passportDate" class="col-sm-3 control-label">Passport date of ussue*</label>
            <div class="col-sm-4">
                <input type="date" id="passportDate" name="passportDate" class="form-control"
                       value="${userPassportDate}" required autofocus>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorPassportDate}</label>
        </div>
        <div class="form-group row">
            <label for="sex" class="col-sm-3 control-label">Select Gender*:</label>
            <div class="col-sm-4">
                <select class="form-control" id="sex" name="sex" required>
                    <option></option>
                    <c:forEach var="gender" items="${gendersMap}">
                        <option value="${gender.getKey()}"
                                <c:if test="${gender.getKey() == userSex}">selected</c:if>>${gender.value}</option>
                    </c:forEach>
                </select>
            </div>
            <label style="background-color: B82303; font-size: 25px">${errorSex}</label>
        </div>

        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block">*Required fields</span>
            </div>
        </div>
        <div class="mx-auto-center">
            <button type="submit" class="btn btn-primary" name="button" value="register">Register</button>
            <button type="submit" class="btn btn-primary" name="button" value="addCar">Add car</button>
            <button type="reset" class="btn btn-danger">Reset</button>
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
