<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit</title>
    </head>
    <body>
    <jsp:include page="menu.jsp"></jsp:include>
        <c:set var="book" value="${book}"/>
        <form method="post">
            <label for="title">Title</label>
            <input type="text" name="title" id="title" value="${book.title}" readonly/><br/>
            <label for="author">Author</label>
            <input type="text" name="author" id="author" value="${book.author}"><br/>
            <label for="pages">Pages</label>
            <input type="text" name="pages" id="pages" value="${book.pages}"><br/>
            <input type="submit" name="edit" value="Edit"/>
        </form>
    </body>
</html>
