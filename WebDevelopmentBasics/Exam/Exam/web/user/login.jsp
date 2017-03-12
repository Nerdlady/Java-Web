<main class="col-4 offset-4 text-center">
    <div class="container">
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
        <div class="row">
            <div class="col-12">
                <div class="text-center"><h1 class="display-3">Login</h1></div>
                <br/>
                <form method="post">
                    <div class="form-group row">
                        <label class="sr-only">Email</label>
                        <input name="email" class="form-control" placeholder="somebody@example.com"/>
                    </div>
                    <div class="form-group row">
                        <label class="sr-only">Password</label>
                        <input name="password" type="password" class="form-control" placeholder="Password"/>
                    </div>

                    <input type="submit" class="btn btn-outline-warning btn-lg btn-block"
                           value="Login"/>
                </form>
                <br/>
            </div>
        </div>
    </div>
</main>
<br/>

