<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form method="POST" action="">
        <label>Title</label>
        <div class="form-group">
            <input type="text" name="title" class="form-control" placeholder="Enter subject...">
        </div>
        <div class="form-group">
            <label>Content</label>
            <textarea name="content" class="form-control" placeholder="Enter your message..."></textarea>
        </div>
        <div class="form-group">
            <label for="categotyName">Category</label>
            <c:set var="categories" value="${categories}"/>
            <select id="categotyName" name="categoryName" class="form-control">
                <c:forEach var="category" items="${categories}">
                    <option>${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-primary" value="Publish"/>
    </form>
</div>