<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-wrapper">
  <div class="container">
	<nav class="navbar navbar-inverse navbar-static-top">
	  <div class="container">
		  <c:set var="user" value="${sessionScope.user}"/>
		<div id="navbar">
		  <ul class="nav navbar-nav">
			<li><a href="/">Topics</a></li>
			<li><a href="/categories">Categories</a></li>
			  <c:if test="${user.role == \"ADMIN\"}">
				  <li class="dropdown">
					  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
					  <ul class="dropdown-menu">
						  <li class=""><a href="/categories">Categories</a></li>
					  </ul>
				  </li>
			  </c:if>
		  </ul>
			<c:choose>
				<c:when test="${not empty user}">
					<ul class="signmenu nav navbar-nav">
						<li><span class="navbar-text">Hello, <a href="/profile/${user.id}"><c:out value="${user.username}"/></a></span></li>
						<li><span class="navbar-text"><a href="/logout">Log out</a></span></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="signmenu nav navbar-nav">
						<li><span class="navbar-text">Hello, <a href="/login">Log in</a></span></li>
						<li><span class="navbar-text"><a href="/register">Register</a></span></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	</nav>
  </div>
</div>