<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<form method="POST">
		<c:set var="category" value="${category}"/>
		<label>Name</label>
		<div class="form-group">
			<input type="text" name="name" class="form-control" value="${category.name}"/>
		</div>
		<input type="submit" class="btn btn-primary" value="Edit Category"/>
	</form>
</div>
