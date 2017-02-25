<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <c:set var="message" value="${message}"/>
        <form class="form-group">
            <div>
                <label for="email">Your Email</label>
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="email" value="${message.email}" readonly>
                </div>
            </div>
            <div>
                <label for="subject">Subject</label>
                <div class="input-group col-sm-10">
                    <input type="text" class="form-control" name="subject" id="subject" value="${message.subject}" readonly>
                </div>
            </div>
            <div>
                <label for="message">Message</label>
                <div class="input-group">
                    <textarea class="form-control" style="min-width: 100%"  name="message" id="message" readonly cols="170" rows="20" >
                        <c:out value="${message.message}"/>
                    </textarea>
                </div>
            </div><br/>
            <a class="card-button btn btn-primary" name="reply"  href="/home/admin/messages/reply/${message.id}">Reply</a>
            <a class="card-button btn btn-primary" name="delete"  href="/home/admin/messages/delete/${message.id}">Delete</a>
        </form>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
