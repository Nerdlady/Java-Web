<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add</title>
    </head>
    <body>
    <jsp:include page="menu.jsp"></jsp:include>
        <form method="post">
            <label for="title">Title</label>
            <input type="text" name="title" id="title"/><br/>
            <label for="author">Author</label>
            <input type="text" name="author" id="author"><br/>
            <label for="pages">Pages</label>
            <input type="text" name="pages" id="pages"><br/>
            <input type="submit" name="add" value="Add Book"/>
        </form>
    </body>
</html>
