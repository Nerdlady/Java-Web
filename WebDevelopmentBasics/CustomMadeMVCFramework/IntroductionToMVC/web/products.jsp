<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/products.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <form>
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Search...">
                    <span class="input-group-btn">
                    <input type="submit" class="form-control" name="search_btn" value="Find" >
                </span>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="card-group">
            <c:set var="knives" value="${knives}"/>
            <c:forEach var="knife" items="${knives}">
                <div class="card col-sm-3 thumbnail">
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
                        <c:set var="user" value="${sessionScope.user}"/>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <a class="card-button btn btn-primary" name="edit"  href="/home/admin/edit/${knife.id}">Edit</a>
                                <a class="card-button btn btn-primary" name="delete"  href="/home/admin/delete/${knife.id}">Delete</a>
                            </c:when>
                            <c:otherwise>
                                <a class="card-button btn btn-primary" name="buy"  href="/home/buy/${knife.id}">Buy</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<hr>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
