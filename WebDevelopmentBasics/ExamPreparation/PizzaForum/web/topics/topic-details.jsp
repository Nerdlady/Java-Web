<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="topic" value="${topic}"/>
<div class="thumbnail">
	<h4><strong><a href="#"><c:out value="${topic.title}"/></a></strong></h4>
	<p><a href="/profile/${topic.author.id}"><c:out value="${topic.author.username}"/></a> <c:out value="${topic.publishDate}"/></p>
	<p><c:out value="${topic.content}"/></p>
</div>
<c:set var="replies" value="${replies}"/>
<c:forEach var="reply" items="${replies}">
	<div class="thumbnail reply">
		<h5><strong><a href="#"><c:out value="${reply.author.username}"/></a></strong> <c:out value="${reply.publishDate}"/></h5>
		<p><c:out value="${reply.content}"/></p>
		<c:if test="${not empty reply.imageURL}">
			<img src="${reply.imageURL}" />
		</c:if>
	</div>
</c:forEach>
<c:set var="user" value="${sessionScope.user}"/>
<c:if test="${not empty user}">
	<div class="thumbnail reply">
		<form method="post">
			<div class="form-group">
				<label>Content</label>
				<textarea class="form-control" name="content" placeholder="Enter your reply..."></textarea>
			</div>
			<div class="form-group">
				<label>Image URL</label>
				<input type="text" name="imageURL" class="form-control" placeholder="http://..."/>
			</div>
			<input type="submit" class="btn btn-primary" value="Reply"/>
		</form>
	</div>
</c:if>
