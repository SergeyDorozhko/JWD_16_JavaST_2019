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

    <form class="form-horizontal" role="form">
        <h2>Registration</h2>
        <div class="form-group row">
            <label for="firstName" class="col-sm-3 control-label">First Name*</label>
            <div class="col-sm-9">
                <input type="text" id="firstName" placeholder="First Name" class="form-control" autofocus>
            </div>
        </div>
        <div class="form-group row">
            <label for="lastName" class="col-sm-3 control-label">Last Name*</label>
            <div class="col-sm-9">
                <input type="text" id="lastName" placeholder="Last Name" class="form-control" autofocus>
            </div>
        </div>

        <div class="form-group row">
            <label for="email" class="col-sm-3 control-label">Email* </label>
            <div class="col-sm-9">
                <input type="email" id="email" placeholder="Email" class="form-control" name="email">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-3 control-label">Password*</label>
            <div class="col-sm-9">
                <input type="password" id="password" placeholder="Password" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-3 control-label">Confirm Password*</label>
            <div class="col-sm-9">
                <input type="password" id="password" placeholder="Password" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label for="birthDate" class="col-sm-3 control-label">Date of Birth*</label>
            <div class="col-sm-4">
                <input type="date" id="birthDate" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label for="phoneNumber" class="col-sm-3 control-label">Phone number*</label>
            <div class="col-sm-6">
                <input type="phoneNumber" id="phoneNumber" placeholder="Phone number" class="form-control">
            </div>
        </div>
        <div class="form-group row">
            <label for="sex" class="col-sm-3 control-label">Select Gender:</label>
            <div class="col-sm-4">
                <select class="form-control" id="sex" name="sex">
                    <option></option>
                    <option>Female</option>
                    <option>Male</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block">*Required fields</span>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Register</button>
    </form> <!-- /form -->
    <%--    </div>--%>

</div> <!-- ./container -->

<c:import url="endOfPages.jsp"/>

<script>
    $(function () {
        $.validator.setDefaults({
            highlight: function (element) {
                $(element)
                    .closest('.form-group')
                    .addClass('has-error')
            },
            unhighlight: function (element) {
                $(element)
                    .closest('.form-group')
                    .removeClass('has-error')
            }
        });

        $.validate({
            rules:
                {
                    password: "required",
                    birthDate: "required",
                    weight: {
                        required: true,
                        number: true
                    },
                    height: {
                        required: true,
                        number: true
                    },
                    email: {
                        required: true,
                        email: true
                    }
                },
            messages: {
                email: {
                    required: true,
                    email: true
                }
            },
            password: {
                required: " Please enter password"
            },
            birthDate: {
                required: " Please enter birthdate"
            },
            email: {
                required: ' Please enter email',
                email: ' Please enter valid email'
            },
            weight: {
                required: " Please enter your weight",
                number: " Only numbers allowed"
            },
            height: {
                required: " Please enter your height",
                number: " Only numbers allowed"
            },
        })

    });

</script>
</body>
</html>
