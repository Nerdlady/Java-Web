<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="/" class="navbar-brand">SOFTUNI BLOG</a>

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:set var="user" value="${sessionScope.user}"/>
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li>
                                <a href="/articles/create"> Create article</a>
                            </li>
                            <li>
                                <a href="/profile"> My profile</a>
                            </li>
                            <li>
                                <a href="/logout">
                                    LOGOUT
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="/register">
                                    REGISTER
                                </a>
                            </li>

                            <li>
                                <a href="/login">
                                    LOGIN
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</header>