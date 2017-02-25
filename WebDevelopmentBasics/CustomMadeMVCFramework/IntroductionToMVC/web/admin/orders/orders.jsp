<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="card-group">
            <c:set var="orders" value="${orders}"/>
            <c:forEach var="order" items="${orders}">
                <div class="card col-sm-3 thumbnail">
                    <h4>Product:</h4><br/>
                    <c:set var="image" value="${order.product.imageURL}"/>
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
                            <c:out value="${order.product.name}"/>
                        </h4>
                        <p class="card-text">
                            <c:out value="$${order.product.price}"/>
                        </p>
                    </div>
                    <hr>
                    <h4>Buyer information:</h4>
                    <div class="card-block">
                        <h4 class="card-title">Name:
                            <c:out value="${order.name}"/>
                        </h4>
                        <p class="card-text">Number:
                            <c:out value="${order.number}"/>
                        </p>
                        <p class="card-text">Address:
                            <c:out value="${order.address}"/>
                        </p>
                        <p class="card-text">Order Type:
                            <c:out value="${order.orderType.toString()}"/>
                        </p>
                        <p class="card-text">Status:
                            <c:out value="${order.status.toString()}"/>
                        </p>
                        <a class="card-button btn btn-primary" name="edit"  href="/home/admin/orders/edit/${order.id}">Edit</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
