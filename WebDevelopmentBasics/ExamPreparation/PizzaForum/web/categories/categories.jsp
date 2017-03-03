<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${sessionScope.user}"/>
<div class="container">
    <c:if test="${(not empty user) && (user.role == \"ADMIN\")}">
        <a href="/admin/categories/new" class="btn btn-success">New Category</a>
    </c:if>
    <table class="table table-striped">
        <caption>Categories</caption>
        <thead>
        <tr>
            <th>Name</th>
            <c:if test="${(not empty user) && (user.role == \"ADMIN\")}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>
        </tr>
        </thead>
        <tbody>

        <c:set var="categories" value="${categories}"/>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><a href="/categories/topics/${category.id}"><c:out value="${category.name}"/></a></td>
                <c:if test="${(not empty user) && (user.role == \"ADMIN\")}">
                    <td><a href="/admin/categories/edit/${category.id}" class="btn btn-primary">Edit</a></td>
                    <td><a href="/admin/categories/delete/${category.id}" class="btn btn-danger">Delete</a></td>
                </c:if>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>