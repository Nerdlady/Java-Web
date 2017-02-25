<%--
  Created by IntelliJ IDEA.
  User: Nerd_lady
  Date: 22-Feb-17
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buy</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<div class="container">
    <div style="align-content: center" class="col-md-offset-3 col-md-5">
        <form class="form-group" method="post">
            <div>
                <label for="name">Name</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter your name...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="number">Phone number</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="number" id="number" placeholder="Enter your phone number...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="address">Address</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="address" id="address" placeholder="Enter your address...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="type">Type of delivery</label>
                <div class="input-group">
                    <select id="type" class="form-control" name="orderType">
                        <option>express</option>
                        <option>economic</option>
                    </select>
                </div>
            </div><br/>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Buy" name="buy-product">
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
