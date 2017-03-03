<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content span=8 offset=2">
       <c:set var="user" value="${user}"/>
        <h3><c:out value="${user.email}"/></h3>
        <h3><c:out value="${user.name}"/></h3>
    </div>
</main>
