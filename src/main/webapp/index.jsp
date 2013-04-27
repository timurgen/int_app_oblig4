<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HIN bank</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <%@include file="WEB-INF/jspf/headerloginform.jspf" %>
    </head>
    <body>
        <% if (request.getSession().getAttribute("username") != null && request.getSession().getAttribute("username").equals("admin")) {%>
        <%@include file="WEB-INF/jspf/hovdmenu.jspf" %>



        <% } else {%>
        <%@include file="WEB-INF/jspf/loginform.jspf" %>

        <% }%>
    </body>
</html>
