<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="user" value="${sessionScope.user}"/>

<c:if test="${not empty user}">
    <a href="/topics/new" class="btn btn-success">New Topic</a>
</c:if>
<c:set var="topics" value="${topics}"/>
<c:forEach var="topic" items="${topics}">
    <div class="thumbnail">
        <h4><strong><a href="/topics/details/${topic.id}">${topic.title}</a></strong>
            <small><a href="/categories/topics/${topic.category.id}">${topic.category.name}</a></small>
        </h4>
        <p><a href="/profile/${topic.author.id}">${topic.author.username}</a> | Replies: ${fn:length(topic.replies)} | ${topic.publishDate}</p>
    </div>
</c:forEach>
