<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<div class="container">
    <div style="align-content: center" class="col-md-offset-3 col-md-5">
        <form class="form-group" method="post">
            <div>
                <label for="email">Your Email</label>
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter your email...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="subject">Subject</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Enter subject...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="message">Subject</label>
                <div class="input-group">
                    <textarea class="form-control" style="min-width: 100%"  name="message" id="message" placeholder="Enter subject..." cols="100" rows="10"></textarea>
                </div>
            </div><br/>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Send">
            </div>
        </form>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
