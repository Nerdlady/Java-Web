<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Your Cart</h1></div>
                <br/>
                <div class="list-group">
                    <c:set var="games" value="${games}"/>
                    <c:forEach var="game" items="${games}">
                        <div class="list-group-item">
                            <a class="btn btn-outline-danger btn-lg" href="/cart/delete/${game.id}">X</a>
                            <div class="media col-3">
                                <figure class="pull-left">
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
                                </figure>
                            </div>
                            <div class="col-md-6">
                                <a href="#"><h4 class="list-group-item-heading"> ${game.title} </h4></a>
                                <p class="list-group-item-text">  ${fn:substring(game.description, 0, 300)}
                                </p>
                            </div>
                            <div class="col-md-2 text-center mr-auto">
                                <h2> ${game.price} &euro; </h2>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                </div>
                <br/>

                <c:set var="total" value="${0}"/>
                <c:forEach var="game" items="${games}">
                    <c:set var="total" value="${total + game.price}" />
                </c:forEach>
                <div class="col-8 offset-2 my-3 text-center">
                    <h1><strong>Total Price - </strong> ${total} &euro;</h1>
                </div>
                <div class="col-8 offset-2 my-3">
                    <form method="post">

                        <input type="submit" class="btn btn-success btn-lg btn-block"
                               value="Order"/>
                    </form>
                </div>
                <br/>
            </div>
        </div>
</main>
