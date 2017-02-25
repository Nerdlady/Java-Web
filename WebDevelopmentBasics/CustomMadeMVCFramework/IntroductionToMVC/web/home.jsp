<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Sharp Store</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet">

</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1" class=""></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="https://static.pexels.com/photos/81777/pexels-photo-81777.jpeg"
                 alt="First slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Stay Sharp</h1>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla tempor augue ac est mollis, eu
                        posuere augue tincidunt. Etiam iaculis nec mi a condimentum. </p>
                    <p><a class="btn btn-lg btn-primary" href="/home/about" role="button">About Us</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="https://static.pexels.com/photos/8446/food-vegetables-wood-knife.jpg"
                 alt="Second slide">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Cut Fresh</h1>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida
                        at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <p><a class="btn btn-lg btn-primary" href="/home/products" role="button">Our Products</a></p>
                </div>
            </div>
        </div>

    </div>

</div><!-- /.carousel -->

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>