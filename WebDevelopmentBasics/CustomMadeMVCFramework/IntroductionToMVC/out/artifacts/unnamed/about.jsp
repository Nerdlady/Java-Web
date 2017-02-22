<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Sharp Store</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">

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

<div class="container marketing">
    <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>© 2017 Sharp Store Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a></p>
    </footer>
</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>