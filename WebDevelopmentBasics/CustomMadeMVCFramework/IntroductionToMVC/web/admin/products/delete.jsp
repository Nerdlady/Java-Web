<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="row  " style="align-content: center">

        <c:set var="knife" value="${knife}"/>
            <div class="card col-sm-3 col-xs-10 col-sm-offset-5 col-xs-offset-1  thumbnail" >
                <h3>Do you want to delete this product?</h3>
                <c:set var="image" value="${knife.imageURL}"/>
                <c:choose>
                    <c:when test="${not empty image}">
                        <img class="card-image-top img-fluid  img-responsive " src="${image}">
                    </c:when>
                    <c:otherwise>
                        <img class="card-image-top img-fluid  img-responsive " src="http://www.cfmbchurch.com/wp-content/uploads/2011/03/NoImageAvailable-e1458578476489-300x150.jpg">
                    </c:otherwise>
                </c:choose>

                <div class="card-block">
                    <h4 class="card-title">
                        <c:out value="${knife.name}"/>
                    </h4>
                    <p class="card-text">
                        <c:out value="$${knife.price}"/>
                    </p>
                    <form method="post">
                       <input type="submit" class="card-button btn btn-primary" name="yes" value="Yes"/>
                        <input type="submit" class="card-button btn btn-primary" name="no" value="No"/>
                    </form>
                </div>
            </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
