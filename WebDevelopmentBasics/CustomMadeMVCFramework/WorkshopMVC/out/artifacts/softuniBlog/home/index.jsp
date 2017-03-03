<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
    <div class="container body-content">
        <div class="row">
            <c:set var="articles" value="${articles}"/>
            <c:forEach var="article" items="${articles}">
            <div class="col-md-6">

                    <article>
                        <header>
                            <h2><c:out value="${article.title}"/></h2>
                        </header>

                        <p> <c:out value="${fn:substring(article.content, 0,500 )}..."/></p>

                        <small class="author"><c:out value="${article.author.name}"/></small>

                        <footer>
                            <div class="pull-right">
                                <a class="btn btn-default btn-xs" href="/articles/details/${article.id}">Read more &raquo;</a>
                            </div>
                        </footer>
                    </article>
            </div>
            </c:forEach>
        </div>
    </div>
</main>