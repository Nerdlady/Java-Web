<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content">
        <div class="row">
            <div class="col-md-12">
                <c:set var="article" value="${article}"/>
                <article>
                    <header>
                        <h2><c:out value="${article.title}"/></h2>
                    </header>

                    <p> <c:out value="${article.content}"/></p>

                    <small class="author"><c:out value="${article.author.name}"/></small>

                    <footer>
                        <div class="pull-right">
                            <c:set var="user" value="${sessionScope.user}"/>
                            <c:if test="${user.id == article.author.id}">
                                <a class="btn btn-success btn-xs" href="/articles/edit/${article.id}">Edit</a>
                                <a class="btn btn-danger btn-xs" href="/articles/delete/${article.id}">Delete</a>
                            </c:if>

                            <a class="btn btn-default btn-xs" href="/">back &raquo;</a>
                        </div>
                    </footer>
                </article>
            </div>
        </div>
    </div>
</main>