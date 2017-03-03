<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content span=8 offset=2">
        <jsp:include page="../error.jsp"/>
        <div class="well">

            <form class="form-horizontal" action="${pageContext.request.contextPath}/register" method="post">
                <fieldset>
                    <legend>Register</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user_email">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="email" id="user_email" placeholder="Email" name="email"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-fullname">Full Name</label>
                        <div class="col-sm-4 ">
                            <input class="form-control" type="text" id="user-fullname" placeholder="Full Name"
                                   name="full-name" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user_password_first">Password</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="user_password_first" placeholder="Password"
                                   name="password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user_password_second">Confirm Password</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="user_password_second" placeholder="Password"
                                   name="confirm-password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/"
                            >Cancel</a>
                            <input value="Submit" type="submit" class="btn btn-primary"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>
