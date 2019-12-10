<%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 25.11.2019
  Time: 11:45
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
    <title><fmt:message key="titel.listOfJourneys"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 40px; padding-bottom: 120px; color: #4B0082" background="images/background.jpg">

<c:import url="headOfPages.jsp"/>
<div class="container" style="background: silver">

    <div class="container" style="background: silver">
        <div class="row" style="position:center">


            <c:choose>
                <c:when test="${ authorizedUser == null or authorizedUser.role.id == 2}">

                    <c:forEach var="element" items="${journeyList}">


                        <div class="col-sm-12">

                            <div class="card mb-1">
                                <div class="card-header">
                                    <h5 class="card-title text-center">
                                        <c:out value="${element.startAddress.city}"/> - <c:out
                                            value="${element.destinationAddress.city}"/>
                                    </h5>
                                </div>
                                <div class="card-body">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <h5 class="card-subtitle" style="color: orange"><fmt:message key="fields.driverName"/><c:out
                                                        value="${element.driver.name}"/></h5>
                                                <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.departure"/></h6>
                                                <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.date"/><c:out
                                                        value="${element.departureDate}"/>,
                                                    <fmt:message key="fields.time"/> <c:out value="${element.departureTime}"/></h6>
                                            </div>
                                            <div class="col-sm-4">
                                                <p class="card-text"><fmt:message key="fields.cost"/><c:out value="${element.cost}"/>
                                                    <c:out
                                                            value="${element.currency}"/></p>
                                            </div>
                                            <div class="col-sm-4">
                                                <p class="card-text" style="color: black"><fmt:message key="fields.passengersNumber"/><big
                                                        style="color: red"><c:out value="${element.passengers.size()}"/>/<c:out
                                                        value="${element.passengersNumber}"/></big></p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </c:when>
                <c:when test="${authorizedUser.role.id == 0}">
                    <c:forEach var="element" items="${journeyList}">


                        <div class="col-sm-12">
                            <form action="viewJourney.html" method="post">

                                <div class="card mb-1">
                                    <div class="card-header">
                                        <h5 class="card-title text-center">
                                            <c:out value="${element.startAddress.city}"/> - <c:out
                                                value="${element.destinationAddress.city}"/>
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="container">
                                            <div class="row">
                                                <div>
                                                    <input type="hidden" id="journeyId" name="journeyId"
                                                           class="form-control"
                                                           value="${element.id}" readonly>
                                                </div>
                                                <div class="col-sm-4">
                                                    <h5 class="card-subtitle" style="color: orange"><fmt:message key="fields.driverName"/><c:out
                                                            value="${element.driver.name}"/></h5>
                                                    <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.departure"/></h6>
                                                    <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.date"/><c:out
                                                            value="${element.departureDate}"/>,
                                                        <fmt:message key="fields.time"/> <c:out value="${element.departureTime}"/></h6>
                                                </div>
                                                <div class="col-sm-4">
                                                    <p class="card-text"><fmt:message key="fields.cost"/><c:out value="${element.cost}"/>
                                                        <c:out
                                                                value="${element.currency}"/></p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <p class="card-text" style="color: black"><fmt:message key="fields.passengersNumber"/><big
                                                            style="color: red"><c:out value="${element.passengers.size()}"/>/<c:out
                                                            value="${element.passengersNumber}"/></big></p>
                                                </div>
                                            </div>
                                            <div class="mx-auto" style="width: 400px;">
                                                <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="button.moreInfo"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </c:forEach>
                </c:when>
                <c:when test="${authorizedUser.role.id==1}">
                    <c:forEach var="element" items="${journeyList}">


                        <div class="col-sm-12">
                            <form action="viewJourney.html" method="post">

                                <div class="card mb-1">
                                    <div class="card-header">
                                        <h5 class="card-title text-center">
                                            <c:out value="${element.startAddress.city}"/> - <c:out
                                                value="${element.destinationAddress.city}"/>
                                        </h5>
                                    </div>
                                    <div class="card-body">
                                        <div class="container">
                                            <div class="row">
                                                <div>
                                                    <input type="hidden" id="journeyId" name="journeyId" class="form-control"
                                                           value="${element.id}" readonly>
                                                </div>
                                                <div class="col-sm-4">
                                                    <h5 class="card-subtitle" style="color: orange"><fmt:message key="fields.driverName"/><c:out
                                                            value="${element.driver.name}"/></h5>
                                                    <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.departure"/></h6>
                                                    <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="fields.date"/><c:out
                                                            value="${element.departureDate}"/>,
                                                        <fmt:message key="fields.time"/> <c:out value="${element.departureTime}"/></h6>
                                                </div>
                                                <div class="col-sm-4">
                                                    <p class="card-text"><fmt:message key="fields.cost"/><c:out value="${element.cost}"/>
                                                        <c:out
                                                                value="${element.currency}"/></p>
                                                </div>
                                                <div class="col-sm-4">
                                                    <p class="card-text" style="color: black"><fmt:message key="fields.passengersNumber"/><big
                                                            style="color: red"><c:out value="${element.passengers.size()}"/>/<c:out
                                                            value="${element.passengersNumber}"/></big></p>
                                                </div>
                                            </div>
                                            <div class="mx-auto" style="width: 400px;">
                                                <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="button.moreInfo"/></button>
                                                <button type="submit" class="btn btn-primary btn-sm"><fmt:message key="button.reservePlace"/></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </c:forEach>
                </c:when>
            </c:choose>
        </div>

    </div>
</div>

<c:import url="endOfPages.jsp"/>

</body>
</html>
</fmt:bundle>