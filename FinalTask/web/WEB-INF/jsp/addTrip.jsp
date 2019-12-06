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
        <title><fmt:message key="titel.head"/></title>
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
        <form action="addTripComfirm.html" role="form" class="needs-validation" novalidate method="post">
            <h2><fmt:message key="titel.head"/></h2>
            <label style="background-color: B82303; font-size: 25px">${unknownError}</label>

            <div class="form-group">
                <label class="col-sm-6 control-label" style="font-size: 25px"><fmt:message
                        key="fields.departureAddress"/> </label>
                <div class="form-group row">
                    <label for="countryFrom" class="col-sm-3 control-label"><fmt:message key="fields.country"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="countryFrom" onchange="this.form.submit()" name="countryFrom"
                                required>
                            <option></option>
                            <c:forEach var="countriesFrom" items="${countriesMap}">
                                <option value="${countriesFrom.getKey()}"
                                        <c:if test="${ds:equalsValue(userCountryFrom, countriesFrom.getKey())}">selected</c:if>>${countriesFrom.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label style="background-color: B82303; font-size: 25px">${errorCountryFrom}</label>
                </div>
                <div class="form-group row">
                    <label for="regionFrom" class="col-sm-3 control-label"><fmt:message key="fields.region"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="regionFrom" name="regionFrom" onchange="this.form.submit()"
                                required>
                            <option></option>
                            <c:forEach var="regionsFrom" items="${regionFromMap}">
                                <option value="${regionsFrom.getKey()}"
                                        <c:if test="${ds:equalsValue(userRegionFrom, regionsFrom.getKey())}">selected</c:if>>${regionsFrom.value}</option>
                            </c:forEach>
                        </select></div>
                    <label style="background-color: B82303; font-size: 25px">${errorRegionFrom}</label>
                </div>
                <div class="form-group row">
                    <label for="cityFrom" class="col-sm-3 control-label"><fmt:message key="fields.city"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="cityFrom" name="cityFrom" required>
                            <option></option>
                            <c:forEach var="citiesFrom" items="${citiesFromMap}">
                                <option value="${citiesFrom.getKey()}"
                                        <c:if test="${ds:equalsValue(userCityFrom, citiesFrom.getKey())}">selected</c:if>>${citiesFrom.value}</option>
                            </c:forEach>
                        </select></div>
                    <label style="background-color: B82303; font-size: 25px">${errorCityFrom}</label>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-6 control-label" style="font-size: 25px"><fmt:message
                        key="fields.arrivalAddress"/> </label>
                <div class="form-group row">
                    <label for="countryTo" class="col-sm-3 control-label"><fmt:message key="fields.country"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="countryTo" onchange="this.form.submit()" name="countryTo"
                                required>
                            <option></option>
                            <c:forEach var="countriesTo" items="${countriesMap}">
                                <option value="${countriesTo.getKey()}"
                                        <c:if test="${ds:equalsValue(userCountryTo, countriesTo.getKey())}">selected</c:if>>${countriesTo.value}</option>

                                <%--                                        <c:if test="${userCountryTo eq countriesTo.getKey()}">selected</c:if>>${countriesTo.value}</option>--%>
                            </c:forEach>
                        </select>
                    </div>
                    <label style="background-color: B82303; font-size: 25px">${errorCountryTo}</label>
                </div>
                <div class="form-group row">
                    <label for="regionTo" class="col-sm-3 control-label"><fmt:message key="fields.region"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="regionTo" name="regionTo" onchange="this.form.submit()"
                                required>
                            <option></option>
                            <c:forEach var="regionsTo" items="${regionToMap}">
                                <option value="${regionsTo.getKey()}"
                                        <c:if test="${ds:equalsValue(userRegionTo, regionsTo.getKey())}">selected</c:if>>${regionsTo.value}</option>
                            </c:forEach>
                        </select></div>
                    <label style="background-color: B82303; font-size: 25px">${errorRegionTo}</label>
                </div>
                <div class="form-group row">
                    <label for="cityTo" class="col-sm-3 control-label"><fmt:message key="fields.city"/>*</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="cityTo" name="cityTo" required>
                            <option></option>
                            <c:forEach var="citiesTo" items="${citiesToMap}">
                                <option value="${citiesTo.getKey()}"
                                        <c:if test="${ds:equalsValue(userCityTo, citiesTo.getKey())}">selected</c:if>>${citiesTo.value}</option>
                            </c:forEach>
                        </select></div>
                    <label style="background-color: B82303; font-size: 25px">${errorCityTo}</label>
                </div>
            </div>

            <div class="form-group row">
                <label for="departureDate" class="col-sm-3 control-label"><fmt:message
                        key="fields.departureDate"/>*</label>
                <div class="col-sm-4">
                    <input type="date" id="departureDate" name="departureDate" class="form-control"
                           value="${userDepartureDate}" required autofocus>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorDepartureDate}</label>
            </div>
            <div class="form-group row">
                <label for="departureTime" class="col-sm-3 control-label"><fmt:message key="fields.depatruteTime"/>
                    *</label>
                <div class="col-sm-3">
                    <input type="time" id="departureTime" name="departureTime"
                           class="form-control" value="${userDepartureTime}" required autofocus>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorDepartureTime}</label>
            </div>
            <div class="form-group row">
                <label for="cost" class="col-sm-3 control-label"><fmt:message key="fields.cost"/> *</label>
                <div class="col-sm-3">
                    <input type="number" step="0.01" min="0" placeholder="0,00" id="cost" name="cost"
                           class="form-control" value="${userCost}" required autofocus>
                </div>
                <div class="col-sm-2">
                    <select class="form-control" id="currency" name="currency" required>
                        <option></option>
                        <c:forEach var="currencies" items="${currenciesMap}">
                            <option value="${currencies.getKey()}"
                                    <c:if test="${ds:equalsValue(userCurrency, currencies.getKey())}">selected</c:if>>${currencies.value}</option>
                        </c:forEach>
                    </select></div>
                <label style="background-color: B82303; font-size: 25px">${errorCost}${errorCurrency}</label>
            </div>


            <div class="form-group row">
                <label for="passengers" class="col-sm-3 control-label"><fmt:message
                        key="fields.passengersNumber"/>*</label>
                <div class="col-sm-4">
                    <input type="number" min="1" max="20" step="1" id="passengers" name="passengers"
                           class="form-control" value="${userPassengers}" required autofocus>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorPassengers}</label>
            </div>

            <div class="form-group row">
                <label for="additionalInformation" class="col-sm-3 control-label"><fmt:message
                        key="fields.additionalInformation"/>*</label>
                <div class="col-sm-9">
                    <textarea cols="100" maxlength="1000" id="additionalInformation" name="additionalInformation"
                              class="form-control" required autofocus>${userAdditionalInformation}
                    </textarea>
                </div>
                <label style="background-color: B82303; font-size: 25px">${errorPassportDate}</label>
            </div>

            <div class="form-group">
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">*<fmt:message key="fields.requiredFields"/></span>
                </div>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary" name="button" value="add"><fmt:message
                        key="button.add"/></button>
                <button type="reset" class="btn btn-danger" name="button" value="reset"><fmt:message
                        key="button.reset"/></button>
            </div>
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