<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="container">
    <div style="align-content: center" class="col-md-offset-3 col-md-5">
        <c:set var="error" value="${error}"/>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissable">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <p><c:out value="${error}"/></p>
            </div>

        </c:if>
        <form class="form-group" method="post">
            <div>
                <label for="name">Product name</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter product name...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="price">Price</label>
                <div class="input-group">
                    <input type="number" class="form-control" name="price" id="price" placeholder="Enter product price...">
                    <span class="input-group-addon " >
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div>
                <label for="imageURL">Image URL</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="imageURL" id="imageURL" placeholder="Enter product image url">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </span>
                </div>
            </div>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Add" name="add">
            </div>
        </form>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
