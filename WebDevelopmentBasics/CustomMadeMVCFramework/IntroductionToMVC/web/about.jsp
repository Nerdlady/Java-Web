<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Sharp Store</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="pull-left col-md-6">
            <h3>Who Are We?</h3>
            <p>Donec orci sapien, tincidunt non posuere quis, elementum in enim. Morbi porta ac est eu tempus. Proin mauris purus,
                consectetur eu gravida non, gravida in velit. Phasellus lacus nunc, interdum non massa id, aliquam tempus justo.
                Aliquam porta velit at sollicitudin maximus. Duis massa tellus, aliquet in ante id, faucibus interdum lorem. Integer quis pellentesque risus.
                Donec orci sapien, tincidunt non posuere quis, elementum in enim. Morbi porta ac est eu tempus. Proin mauris purus,
                consectetur eu gravida non, gravida in velit. Phasellus lacus nunc, interdum non massa id, aliquam tempus justo.
                Aliquam porta velit at sollicitudin maximus. Duis massa tellus, aliquet in ante id, faucibus interdum lorem. Integer quis pellentesque risus.</p>
        </div>
        <div class="pull-right">
            <img class="img-responsive" src="https://placeholdit.imgix.net/~text?txtsize=38&txt=400%C3%97200&w=400&h=200&fm=png"/>
        </div>
    </div><br/>
    <hr>
    <div class="row">
        <div class="pull-left">
            <img class="img-responsive" src="https://placeholdit.imgix.net/~text?txtsize=38&txt=400%C3%97200&w=400&h=200&fm=png"/>
        </div>
        <div class="pull-right col-md-6">
            <p>Donec orci sapien, tincidunt non posuere quis, elementum in enim. Morbi porta ac est eu tempus. Proin mauris purus,
                consectetur eu gravida non, gravida in velit. Phasellus lacus nunc, interdum non massa id, aliquam tempus justo.
                Aliquam porta velit at sollicitudin maximus. Duis massa tellus, aliquet in ante id, faucibus interdum lorem. Integer quis pellentesque risus.</p>
        </div>
    </div>
    <hr>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>