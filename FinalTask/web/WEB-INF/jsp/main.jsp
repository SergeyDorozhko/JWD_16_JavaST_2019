<%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 28.10.2019
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Poputka</title>
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
            <div class="col-sm-1">
            </div>

            <div class="col-sm-10">

                <div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active" data-interval="3000">
                            <img src="images/mainPage/scroll1.jpg" class="img-thumbnail" alt="Cinque Terre">
                            <div class="carousel-caption d-none d-md-block">
                                <h2></h2>
                                <h1 style="color: orange; -webkit-text-stroke: 1px black;">Надоело ждать попутку?</h1>
                            </div>
                        </div>
                        <div class="carousel-item" data-interval="3000">
                            <img src="images/mainPage/scroll2.jpg" class="img-thumbnail" alt="Cinque Terre">
                            <div class="carousel-caption d-none d-md-block">
                                <h2></h2>
                                <h1 style="color: orange; -webkit-text-stroke: 1px black;">Скучно ездить одному?</h1>
                            </div>
                        </div>
                        <div class="carousel-item" data-interval="3000">
                            <img src="images/mainPage/scroll3.jpg" class="img-thumbnail" alt="Cinque Terre">
                            <div class="carousel-caption d-none d-md-block">
                                <h2></h2>
                                <h1 style="color: orange; -webkit-text-stroke: 1px black;">Пользуйся нашим сервисом и
                                    забудь
                                    про скучные поездки!</h1>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleFade" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleFade" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

            </div>


            <div class="col-sm-1">

            </div>
        </div>


        <div class="container" style="background: silver">

            <div class="row">


                <c:forEach var="element" items="${journeyList}" end="8">

                    <div class="col-sm-4">

                        <div class="card mb-1" style="width: 21rem;">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value="${element.startAddress.city}"/> - <c:out
                                        value="${element.destinationAddress.city}"/></h5>
                                <h6 class="card-subtitle mb-2 text-muted">Отправление:</h6>
                                <h6 class="card-subtitle mb-2 text-muted">Дата: <c:out
                                        value="${element.departureDate}"/>, Время: <c:out
                                        value="${element.departureTime}"/></h6>
                                <p class="card-text">Цена поездки: <c:out value="${element.cost}"/> <c:out
                                        value="${element.currency}"/></p>
                                <c:if test="${authorizedUser.role.id!=2 and authorizedUser!=null}">

                                    <a href="#" class="card-link">More information</a>
                                </c:if>

                            </div>
                        </div>
                    </div>

                </c:forEach>

            </div>

            <form action="listOfJourneys.html" method="get">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-12">

                    <button type="submit" class="btn btn-warning btn-lg btn-block">View more
                        suggestions
                    </button>
                </div>
                <div class="col-sm-4">
                </div>
            </form>
        </div>
    </div>
</div>


<c:import url="endOfPages.jsp"/>


</body>
</html>
