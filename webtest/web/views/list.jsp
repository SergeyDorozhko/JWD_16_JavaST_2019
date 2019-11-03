<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sergd
  Date: 29.10.2019
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>LIST</h2>

<ul>
    <%
        List<String> names = (List<String>) request.getAttribute("userNames");

        if (names != null && !names.isEmpty()) {
            for (String s : names) {
                out.println("<li>" + s + "</li>");
            }
        } else out.println("<p>There are no users yet!</p>");
    %>
</ul>
</body>
</html>
