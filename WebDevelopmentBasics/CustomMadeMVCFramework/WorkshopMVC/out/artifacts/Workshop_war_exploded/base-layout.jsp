<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/head.jsp"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<jsp:include page="${view}"/>

<jsp:include page="fragments/footer.jsp"/>
<jsp:include page="fragments/script-bundle.jsp"/>
</body>
</html>
