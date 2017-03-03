<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content">
        <div class="row">
            <c:forEach items="${articles}" var="article">
                <div class="col-md-6">
                    <article>
                        <header>
                            <h2>${article.title}</h2>
                        </header>


                        <p>${article.summary}</p>

                        <small class="author">${article.author.fullName}</small>

                        <footer>
                            <div class="pull-right">
                                <a class="btn btn-default btn-xs"
                                   href="/article/${article.id}">Read more &raquo;</a>
                            </div>
                        </footer>
                    </article>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
