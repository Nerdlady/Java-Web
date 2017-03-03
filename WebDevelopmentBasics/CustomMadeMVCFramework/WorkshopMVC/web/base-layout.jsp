<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="${pageContext.request.contextPath}/fragments/head.jsp"/>
<body>
<jsp:include page="${pageContext.request.contextPath}/fragments/header.jsp"/>

<jsp:include page="${view}"/>

<jsp:include page="${pageContext.request.contextPath}/fragments/footer.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/fragments/script-bundle.jsp"/>
</body>
</html>
