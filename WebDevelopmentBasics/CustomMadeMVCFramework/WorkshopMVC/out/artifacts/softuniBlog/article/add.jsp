<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content span=8 offset=2">
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
        <div class="well">
            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>New Article</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="title">Article Title</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="title" placeholder="Article Title" name="title" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="content">Content</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" name="content" id="content"  cols="100" rows="10"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="/">Cancel</a>
                            <input type="submit" value="Create" class="btn btn-primary"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>
