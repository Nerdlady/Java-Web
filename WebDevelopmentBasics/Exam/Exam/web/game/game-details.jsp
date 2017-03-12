<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <c:set var="game" value="${game}"/>
        <div class="row">
            <div class="col-12 text-center">

                <h1 class="display-3">${game.title}</h1>
                <br/>

                <iframe width="560" height="315" src="https://www.youtube.com/embed/${game.trailer}" frameborder="0"
                        allowfullscreen></iframe>

                <br/>
                <br/>

                <p>${game.description}</p>

                <p><strong>Price</strong> - ${game.price}&euro;</p>
                <p><strong>Size</strong> - ${game.size} GB</p>
                <p><strong>Release Date</strong> - ${game.releaseDate.toString()}</p>

                <a class="btn btn-outline-primary" href="#">Back</a>
                <c:set var="user" value="${sessionScope.user}"/>
                <c:if test="${(not empty user) && (user.role = \"ADMIN\")}">
                    <a class="btn btn-warning" href="/admin/games/edit/${game.id}">Edit</a>
                    <a class="btn btn-danger" href="/admin/games/delete/${game.id}">Delete</a>
                </c:if>
                <a class="btn btn-primary" href="/games/buy/${game.id}">Buy</a>
                <br/>
                <br/>

            </div>
        </div>
    </div>
</main>
<br/>