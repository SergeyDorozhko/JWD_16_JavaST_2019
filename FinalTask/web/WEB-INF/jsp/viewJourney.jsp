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
<fmt:bundle basename="pagecontent" prefix="journey.">
    <html>
    <head>
        <title><fmt:message key="titel.viewJourney"/></title>
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
        <form action="editJourneyPage.html" role="form" class="needs-validation" novalidate method="post">
            <h2><fmt:message key="titel.viewJourney"/></h2>
            <label style="background-color: B82303; font-size: 25px">${unknownError}</label>

            <div class="form-group row">
                <label for="driverName" class="col-sm-3 control-label"><fmt:message key="fields.driverName"/></label>
                <div class="col-sm-6">
                    <input type="text" id="driverName" class="form-control"
                           value="${journey.driver.name}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="driverPhone" class="col-sm-3 control-label"><fmt:message key="fields.driverPhone"/></label>
                <div class="col-sm-6">
                    <input type="text" id="driverPhone" class="form-control"
                           value="${journey.driver.phoneNumber}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="driverEmail" class="col-sm-3 control-label"><fmt:message key="fields.driverEmail"/></label>
                <div class="col-sm-6">
                    <input type="text" id="driverEmail" class="form-control"
                           value="${journey.driver.email}" readonly>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-6 control-label" style="font-size: 25px"><fmt:message
                        key="fields.car"/> </label>
                <div class="form-group row">
                    <label for="brand" class="col-sm-3 control-label"><fmt:message key="fields.carBrand"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="brand" class="form-control"
                               value="${journey.driver.car.brand}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="model" class="col-sm-3 control-label"><fmt:message key="fields.carModel"/> </label>
                    <div class="col-sm-6">
                        <input type="text" id="model" class="form-control"
                               value="${journey.driver.car.model}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="yearOfProduce" class="col-sm-3 control-label"><fmt:message
                            key="fields.carYearOfProduce"/></label>
                    <div class="col-sm-6">
                        <input type="yearOfProduce" id="yearOfProduce" class="form-control"
                               value="${journey.driver.car.yearOfProduce}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="airConditioner" class="col-sm-3 control-label"><fmt:message
                            key="fields.carClimate"/></label>
                    <div class="col-sm-6">
                        <input type="airConditioner" id="airConditioner" class="form-control"
                               value="${journey.driver.car.airConditioner}" readonly>
                    </div>
                </div>

            </div>


            <div class="form-group">
                <label class="col-sm-6 control-label" style="font-size: 25px"><fmt:message
                        key="fields.departureAddress"/> </label>
                <div>
                    <input type="hidden" id="journeyId" name="journeyId" class="form-control"
                           value="${journey.id}" readonly>
                </div>

                <div class="form-group row">
                    <label for="countryFrom" class="col-sm-3 control-label"><fmt:message key="fields.country"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="countryFrom" class="form-control"
                               value="${journey.startAddress.country}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="regionFrom" class="col-sm-3 control-label"><fmt:message key="fields.region"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="regionFrom" class="form-control"
                               value="${journey.startAddress.regionalCenter}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cityFrom" class="col-sm-3 control-label"><fmt:message key="fields.city"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="cityFrom" class="form-control"
                               value="${journey.startAddress.city}" readonly>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-6 control-label" style="font-size: 25px"><fmt:message
                        key="fields.arrivalAddress"/> </label>
                <div class="form-group row">
                    <label for="countryTo" class="col-sm-3 control-label"><fmt:message
                            key="fields.country"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="countryTo" class="form-control"
                               value="${journey.destinationAddress.country}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="regionTo" class="col-sm-3 control-label"><fmt:message
                            key="fields.region"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="regionTo" class="form-control"
                               value="${journey.destinationAddress.regionalCenter}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cityTo" class="col-sm-3 control-label"><fmt:message
                            key="fields.city"/></label>
                    <div class="col-sm-6">
                        <input type="text" id="cityTo" class="form-control"
                               value="${journey.destinationAddress.city}" readonly>
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <label for="departureDate" class="col-sm-3 control-label"><fmt:message
                        key="fields.departureDate"/></label>
                <div class="col-sm-4">
                    <input type="date" id="departureDate" class="form-control"
                           value="${journey.departureDate}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="departureTime" class="col-sm-3 control-label"><fmt:message
                        key="fields.depatruteTime"/>
                </label>
                <div class="col-sm-3">
                    <input type="time" id="departureTime"
                           class="form-control" value="${journey.departureTime}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="cost" class="col-sm-3 control-label"><fmt:message key="fields.cost"/>
                </label>
                <div class="col-sm-3">
                    <input type="number" id="cost"
                           class="form-control" value="${journey.cost}" readonly>
                </div>
                <div class="col-sm-3">
                    <input type="text" id="currency"
                           class="form-control" value="${journey.currency}" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="passengers" class="col-sm-3 control-label"><fmt:message
                        key="fields.passengersNumber"/></label>
                <div class="col-sm-4">
                    <input type="number" id="passengers" name="passengers"
                           class="form-control" value="${journey.passengersNumber}" readonly>
                </div>
            </div>

            <div class="form-group row">
                <label for="additionalInformation" class="col-sm-3 control-label"><fmt:message
                        key="fields.additionalInformation"/></label>
                <div class="col-sm-9">
                    <textarea cols="100" maxlength="1000" id="additionalInformation"
                              class="form-control" readonly>${journey.additionalInformation}
                    </textarea>
                </div>
            </div>

            <c:choose>
                <c:when test="${journey.driver.id eq authorizedUser.id}">
                    <div class="mx-auto-center">
                        <button type="submit" class="btn btn-warning" name="button" value="editJourney"><fmt:message
                                key="button.edit"/></button>
                    </div>
                    <div class="mx-auto-center">
                        <button type="button" class="btn btn-danger" name="button" value="deleteJourney"><fmt:message
                                key="button.delete"/></button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="mx-auto-center">
                        <button type="submit" class="btn btn-primary" name="button" value="reservePlace"><fmt:message
                                key="button.reservePlace"/></button>
                    </div>
                </c:otherwise>
            </c:choose>
        </form> <!-- /form -->
            <%--    </div>--%>
        <form action="main.html" role="form" method="get">
            <div class="mx-auto-center">

                <button type="submit" class="btn btn-primary" name="button" value="main"><fmt:message
                        key="button.home"/></button>
            </div>
        </form>

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