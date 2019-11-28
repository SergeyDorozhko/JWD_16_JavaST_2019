<%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 25.11.2019
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List of journeys</title>
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
                                        <h5 class="card-subtitle" style="color: orange">Водитель: <c:out
                                                value="${element.driver.name}"/></h5>
                                        <h6 class="card-subtitle mb-2 text-muted">Отправление:</h6>
                                        <h6 class="card-subtitle mb-2 text-muted">Дата: <c:out
                                                value="${element.departureDate}"/>,
                                            Время: <c:out value="${element.departureTime}"/></h6>
                                    </div>
                                    <div class="col-sm-4">
                                        <p class="card-text">Цена поездки: <c:out value="${element.cost}"/> <c:out
                                                value="${element.currency}"/></p>
                                    </div>
                                    <div class="col-sm-4">
                                        <p class="card-text" style="color: black">Занято мест: <big
                                                style="color: red"><c:out value="${element.passengers.size()}"/>/<c:out
                                                value="${element.passengersNumber}"/></big></p>
                                    </div>
                                </div>
                                <div class="mx-auto" style="width: 400px;">
                                    <c:if test="${authorizedUser.role.id!=2 and authorizedUser!=null}">
                                        <a href="#" class="card-link">More information</a>
                                    </c:if>
                                    <c:if test="${authorizedUser.role.id==1}">
                                        <a href="#" class="card-link">Reserve seat</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>

        </div>

    </div>
</div>

<c:import url="endOfPages.jsp"/>

</body>
</html>
