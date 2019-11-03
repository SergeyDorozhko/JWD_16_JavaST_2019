<%@ page import="by.dorozhko.xmlparse.entity.TariffType" %>
<%@ page import="java.util.Set" %>
<%@ page import="by.dorozhko.xmlparse.entity.VoiceTariff" %><%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 30.10.2019
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Result</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>


<div class="container">
    <div class="jumbotron text-center">
        <h1>Result list</h1>
    </div>
</div>


<div class="container">

    <lu>
        <%


            if (request.getAttribute("parser") != null) {
                out.println("<p>parser = " + request.getAttribute("parser") + "</p>");
            } else {
                out.print("<p>NO parser");
            }



            if (request.getAttribute("error") != null) {
                out.println("<p>"+ request.getAttribute("error") +"</p>");
            }

        %>
    </lu>


    <div class="table-responsive">
        <table class="table table-bordered">
            <caption>Belarus Operator's tariffs.</caption>
            <thead>
            <tr>
                <th id="tariff name">Tariff name</th>
                <th id="operator">Operator</th>
                <th id="payroll">Payroll</th>
                <th id="priceIn">Call Price (In operator)</th>
                <th id="priceOut">Call Price (other operator)</th>
                <th id="smsPrice">SMS price</th>
                <th id="tarificationType">Tarification</th>
                <th id="connectPay">Pay for connection</th>
                <th id="startData">launch data</th>
                <th id="endData">Archive data</th>
            </tr>
            </thead>
            <tbody>

            <%
                Set<TariffType> tariffs = (Set<TariffType>) request.getAttribute("list");

                if (tariffs != null && !tariffs.isEmpty()) {
                    for (TariffType tariff : tariffs) {
                        out.println("<tr>");
                        out.println("<td>" + tariff.getName() + "</td>");
                        out.println("<td>" + tariff.getOperatorName() + "</td>");
                        out.println("<td>" + tariff.getPayroll() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getCallPrice().getInOperator() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getCallPrice().getOtherOperators() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getSmsPrice() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getParametrs().getTarification() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getParametrs().getConnectiong() + "</td>");
                        out.println("<td>" + ((VoiceTariff) tariff).getDate().getLaunchDate() + "</td>");
                        out.println("<td>" + (((VoiceTariff) tariff).getDate().getArchiveDate() != null ? ((VoiceTariff) tariff).getDate().getArchiveDate() : "") + "</td>");
                        out.println("</tr>");

                    }
                } else out.println("<p>There are no tariffs, List is empty!</p>");
            %>


            </tbody>
        </table>
    </div>
</div>

<div class="container">
    <form action="home">
        <div class="row">
            <button type="submit" class="btn btn-primary btn-md">home</button>

        </div>
    </form>
</div>
</div>
</body>
</html>
