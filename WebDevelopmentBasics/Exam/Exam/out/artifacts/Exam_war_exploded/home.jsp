<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">SoftUni Store</h1></div>

                <form class="form-inline">
                    Filter:
                    <input type="submit" name="filter" class="btn btn-link" value="All"/>
                    <input type="submit" name="filter" class="btn btn-link" value="Owned"/>
                </form>
                <c:set var="games" value="${games}"/>

                <c:forEach var="game" items="${games}" varStatus="loop">
                    <c:if test="${loop.index % 3 == 0}">
                        <div class="card-group">
                    </c:if>
                    <div class="card col-4 thumbnail">
                        <c:choose>
                            <c:when test="${not empty game.imageURL}">
                                <img class="card-image-top img-fluid img-thumbnail"
                                     src="${game.imageURL}"/>
                            </c:when>
                            <c:otherwise>
                                <img
                                        class="card-image-top img-fluid img-thumbnail"
                                        onerror="this.src='https://i.ytimg.com/vi/${game.trailer}/maxresdefault.jpg';"
                                        src="https://i.ytimg.com/vi/${game.trailer}/maxresdefault.jpg">
                            </c:otherwise>
                        </c:choose>


                        <div class="card-block">
                            <h4 class="card-title">${game.title}</h4>
                            <p class="card-text"><strong>Price</strong> - ${game.price}&euro;</p>
                            <p class="card-text"><strong>Size</strong> - ${game.size} GB</p>
                            <p class="card-text">${fn:substring(game.description, 0, 300)}</p>
                        </div>

                        <div class="card-footer">
                            <c:set var="user" value="${sessionScope.user}"/>
                            <c:if test="${(not empty user) && (user.role == \"ADMIN\")}">
                                <a class="card-button btn btn-warning" name="edit" href="/admin/games/edit/${game.id}">Edit</a>
                                <a class="card-button btn btn-danger" name="delete"
                                   href="/admin/games/delete/${game.id}">Delete</a>
                            </c:if>
                            <a class="card-button btn btn-outline-primary" name="info" href="/games/details/${game.id}">Info</a>
                            <a class="card-button btn btn-primary" name="buy" href="/games/buy/${game.id}">Buy</a>
                        </div>
                    </div>
                    <c:if test="${loop.index + 1 % 3 == 0}">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<br/>
