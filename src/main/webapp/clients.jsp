<%-- 
    Document   : clients
    Created on : 17.apr.2013, 22:34:10
    Author     : Timur Samkharadze
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HIN bank</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <%@include file="WEB-INF/jspf/headerloginform.jspf" %>
    </head>
    <body>
        <% if (request.getSession().getAttribute("username") == null || !request.getSession().getAttribute("username").equals("admin")) {%>
        <%@include file="WEB-INF/jspf/loginform.jspf" %>
        <% } else {%>
        <%@include file="WEB-INF/jspf/hovdmenu.jspf" %>
        <%@include file="WEB-INF/jspf/clientlist.jspf" %>
        <% }
            if (request.getParameter("client") != null) {
                out.println("<script> var currentClientId=" +
                        request.getParameter("client")+
                        "</script>");
        %>
        <%@include file="WEB-INF/jspf/clients/konto_details.jspf" %>
        <% }%>
    </body>
</html>
