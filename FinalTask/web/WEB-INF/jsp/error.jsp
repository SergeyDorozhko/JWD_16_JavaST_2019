<%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 16.12.2019
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ds" uri="dorozhkoTag" %>
<c:set var="language" value="${ds:localeValue(lang, cookie['lang'].value)}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="pagecontent" prefix="errorPage.">

    <html>
    <head>
        <title><fmt:message key="titel"/></title>
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


        </style>
    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #4B0082" background="images/background.jpg">

    <c:import url="headOfPages.jsp"/>
    <div class="container">

        <form action="saveCar.html" role="form" class="needs-validation" novalidate method="post">

            <c:choose>
                <c:when test="${not empty unknownError}">
                    <label>${unknownError}</label>
                </c:when>
                <c:otherwise>
                    <label><fmt:message key="faildRequest"/> ${pageContext.errorData.requestURI}</label>>
                    <br>
                    <label><fmt:message key="statusCode"/> ${pageContext.errorData.statusCode}:</label>
                    <c:if test="${not empty error}"><label><fmt:message key="${error}"/></label></c:if>
                    <label>${pageContext.exception.message}</label>
                </c:otherwise>
            </c:choose>

        </form>
    </div>
    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>