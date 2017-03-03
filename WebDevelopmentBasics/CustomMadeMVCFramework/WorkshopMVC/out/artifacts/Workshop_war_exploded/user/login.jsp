<main>
    <div class="container body-content span=8 offset=2">
        <div class="well">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
                <fieldset>
                    <legend>Login</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user_email">Email</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control" id="user_email" placeholder="Email" name="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="password">Password</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/">Cancel</a>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>
