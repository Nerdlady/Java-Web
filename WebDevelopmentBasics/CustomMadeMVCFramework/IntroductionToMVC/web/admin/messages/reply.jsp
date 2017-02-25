<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reply</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <c:set var="message" value="${message}"/>
        <form class="form-group" method="post">
            <div>
                <label for="email">To:</label>
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="email" value="${message.email}" readonly>
                </div>
            </div>
            <div>
                <label for="subject">Subject</label>
                <div class="input-group col-sm-10">
                    <input type="text" class="form-control" name="subject" id="subject" value="RE: ${message.subject}">
                </div>
            </div>
            <div>
                <label for="message">Message</label>
                <div class="input-group">
                    <textarea class="form-control"  name="message" id="message" cols="170" rows="20" >
                    </textarea>
                </div>
            </div><br/>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Send">
            </div>
        </form>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
