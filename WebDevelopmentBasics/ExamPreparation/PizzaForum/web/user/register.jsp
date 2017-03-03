<div class="container">
	<jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
	<form method="POST" action="">
		<label>Username</label>
		<div class="form-group">
			<input type="text" class="form-control" name="username" placeholder="Enter username">
		</div>
		<label>Email</label>
		<div class="form-group">
			<input type="email" class="form-control" name="email" placeholder="Enter email">
		</div>
		<label>Password</label>
		<div class="form-group">
			<input type="password" class="form-control" name="password" placeholder="Enter password">
		</div>
		<label>Confirm Password</label>
		<div class="form-group">
			<input type="password" class="form-control" name="confirmPassword" placeholder="Confirm password">
		</div>
		<input type="reset" class="btn btn-danger" value="Clear"/>
		<input type="submit" class="btn btn-success" value="Register"/>
	</form>
</div>