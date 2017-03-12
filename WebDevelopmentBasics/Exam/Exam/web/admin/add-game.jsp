<!--Body of the store-->
<main class="col-4 offset-4 text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
                <div class="text-center"><h1 class="display-3">Add Game</h1></div>
                <br/>
                <form method="post">
                    <div class="form-group row">
                        <label class="form-control-label">Title</label>
                        <input type="text" name="title" pattern="[A-Z].{3,100}" class="form-control"
                               placeholder="Enter Game Title"/>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Description</label>
                        <textarea name="description" class="form-control" placeholder="Enter Game Description" minlength="20"></textarea>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Thumbnail</label>
                        <input  name="imageURL" type="url" class="form-control" placeholder="Enter URL to Image"/>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Price</label>
                        <div class="input-group">

                            <input type="number" name="price" step="0.01" min="0" class="form-control" placeholder="Enter Price"/>
                            <span class="input-group-addon">&euro;</span>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Size</label>
                        <div class="input-group">

                            <input type="number" name="size" step="0.1" min="0" class="form-control" placeholder="Enter Size"/>
                            <span class="input-group-addon">GB</span>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">YouTube Video URL</label>
                        <div class="input-group">
                                <span class="input-group-addon">https://www.youtube.com/watch?v=</span>
                            <input type="text" name="trailer" class="form-control" minlength="11" maxlength="11"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="form-control-label">Release Date</label>
                        <input name="date" type="date" class="form-control" placeholder="yyyy-MM-dd"/>
                    </div>

                    <input type="submit" id="btn-add-game" class="btn btn-outline-success btn-lg btn-block"
                           value="Add Game"/>
                </form>
                <br/>
            </div>
        </div>
    </div>
</main>

