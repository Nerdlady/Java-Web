<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="userProfile" value="${userProfile}"/>
    <c:set var="loggedUser" value="${sessionScope.user}"/>
    <h1><c:out value="${userProfile.username}"/></h1>
    <table class="table table-striped">
        <caption>Topics</caption>
        <thead>
        <tr>
            <th>Title</th>
            <th>Category</th>
            <th>Date</th>
            <th>Replies Count</th>
            <c:if test="${(not empty loggedUser) && (loggedUser.id == userProfile.id)}">
                <th>Delete</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:set var="topics" value="${userProfile.topics}"/>
        <c:forEach var="topic" items="${topics}">
            <tr>
                <td><a href="/topics/details/${topic.id}"><c:out value="${topic.title}"/></a></td>
                <td><a href="/categories/topics/${topic.category.id}"><c:out value="${topic.category.name}"/></a></td>
                <td><c:out value="${topic.publishDate}"/></td>
                <td><c:out value="${fn:length(topic.replies)}"/></td>
                <c:if test="${(not empty loggedUser) && (loggedUser.id == userProfile.id)}">
                    <td><a href="/topics/delete/${topic.id}" class="btn btn-danger">Delete</a></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>