<div class="container">
	<jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
	<form method="POST" action="">
		<label>Username</label>
		<div class="form-group">
			<input type="text" name="username" class="form-control" placeholder="Enter username or email">
		</div>
		<label>Password</label>
		<div class="form-group">
			<input type="password" name="password" class="form-control" placeholder="Enter password">
		</div>
		<a href="#" class="btn btn-primary">Register</a>
		<input type="submit" class="btn btn-success" value="Log In"/>
	</form>
</div>