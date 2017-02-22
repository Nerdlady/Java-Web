<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
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
    <div style="align-content: center" class="col-md-offset-3 col-md-5">
        <form class="form-group" method="post">
            <div>
                <label for="emial">Your Email</label>
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="emial" placeholder="Enter your email...">
                    <span class="input-group-btn">
                    <input type="button" class="form-control" value="*" readonly>
                </span>
                </div>
            </div>
            <div>
                <label for="subject">Subject</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Enter subject...">
                    <span class="input-group-btn">
                    <input type="button" class="form-control" value="*" readonly>
                </span>
                </div>
            </div>
            <div>
                <label for="message">Subject</label>
                <div class="input-group">
                    <textarea class="form-control" style="min-width: 100%"  name="message" id="message" placeholder="Enter subject..."></textarea>
                </div>
            </div><br/>
            <div class="input-group">
                <input type="submit" class="btn btn-primary" value="Send">
            </div>
        </form>
    </div>
</div>

<hr>
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
