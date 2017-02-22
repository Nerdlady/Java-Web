<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/products.css" rel="stylesheet">
</head>
<body>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/home/index">Sharp Store</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class=""><a href="/home/products">Products</a></li>
                        <li><a href="/home/about">About Us</a></li>
                        <li><a href="/home/contacts">Contacts</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">Theme <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Dark</a></li>
                                <li><a href="#">Light</a></li>
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>

    </div>
</div>

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
                    <img class="card-image-top img-fluid  img-responsive " src="${knife.imageURL}">
                    <div class="card-block">
                        <h4 class="card-title">
                            <c:out value="${knife.name}"/>
                        </h4>
                        <p class="card-text">
                            <c:out value="$${knife.price}"/>
                        </p>
                        <a class="card-button btn btn-primary" name="buy"  href="/home/buy/${knife.id}">Buy</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<hr>
<div class="container marketing">
    <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>© 2017 Sharp Store Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a></p>
    </footer>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
