<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Order</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>
<div class="container" style="align-content: center">
    <div class="row">
        <h3>Edit order</h3>
        <c:set var="order" value="${order}"/>
        <div class="card-group " >
                <div class="card col-sm-3 thumbnail">
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
                </div>
        </div>
    </div>
    <div class="row">
        <form class="form-group col-xs-offset-2 col-sm-offset-0" method="post">
            <div>
                <label for="name">Buyer name:</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="name" id="name" value="${order.name}" readonly>
                </div>
            </div>
            <div>
                <label for="number">Number</label>
                <div class="input-group">
                    <input type="number" class="form-control" name="number" id="number" value="${order.number}" readonly>
                </div>
            </div>
            <div>
                <label for="address">Address</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="address" id="address" value="${order.address}" readonly>
                </div>
            </div>
            <div>
                <label for="orderType">Order type:</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="orderType" id="orderType" value="${order.orderType}" readonly>
                </div>
            </div>
            <div>
                <label for="type">Status</label>
                <div class="input-group">
                    <select id="type" class="form-control" name="status" >
                        <option>PENDING</option>
                        <option>SHIPPED</option>
                        <option>DELIVERED</option>
                    </select>
                </div>
            </div><br/>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Edit" name="edit">
            </div>
        </form>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
