<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bookhut.models.binding.LoginModel" %>
<%@ page import="bookhut.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Menu</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="/">Home</a> </li>
                <li><a href="/signup">Sign up</a> </li>
                <%
                    LoginModel loginModel = (LoginModel) session.getAttribute(Config.LOGIN_MODEL);
                    String username = null;
                    if(loginModel != null){
                        username = loginModel.getUsername();
                        request.setAttribute(Config.USERNAME, username);
                    }
                %>
                <c:set var="title" value="${USERNAME}" scope="session"/>
                <c:choose>
                    <c:when test="${title != null}">
                        <li><a href="/signout">Sign Out(${title})</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/signin">Sign In</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="/add">Add Book </a> </li>
                <li><a href="/shelves">Shelves</a> </li>
            </ul>
        </nav>
    </body>
</html>
