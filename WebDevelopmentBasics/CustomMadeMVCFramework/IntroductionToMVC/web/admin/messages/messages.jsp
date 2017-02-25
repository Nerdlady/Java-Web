<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<div class="container">
        <c:set var="messages" value="${messages}"/>
        <c:choose>
            <c:when test="${empty messages}">
                <h3><c:out value="No messages"/></h3>
            </c:when>
            <c:otherwise>
                <table class="table-condensed table-hover table-striped" id="myTable">
                    <tr>
                        <td class="col-sm-3"><strong>Email</strong></td>
                        <td class="col-sm-3"><strong>Subject</strong> </td>
                        <td class="col-sm-d"><strong>Message</strong></td>
                    </tr>
                    <c:forEach var="message" items="${messages}">
                        <tr>
                            <td class="col-sm-3"><c:out value="${fn:substring(message.email,0,20)}"/></td>
                            <td class="col-sm-3" ><c:out value="${fn:substring(message.subject,0,25)}"/></td>
                            <td class="col-sm-d"><c:out value="${fn:substring(message.message,0,40)}"/></td>
                            <td><a href="/home/admin/messages/view/${message.id}">View</a> </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
