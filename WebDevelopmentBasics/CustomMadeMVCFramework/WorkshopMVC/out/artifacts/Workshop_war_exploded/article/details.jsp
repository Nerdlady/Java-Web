<main>
    <div class="container body-content">
        <div class="row">
            <div class="col-md-12">
                <article>
                    <header>
                        <h2>${article.title}</h2>
                    </header>

                    <p>${article.content}</p>

                    <small class="author">${article.author.fullName}</small>

                    <footer>

                        <div class="pull-right">

                            <a class="btn btn-success btn-xs" href="@{/article/edit/${article.id}">Edit</a>
                            <a class="btn btn-danger btn-xs" href="@{/article/delete/${article.id}">Delete</a>

                            <a class="btn btn-default btn-xs" href="/">back &raquo;</a>
                        </div>
                    </footer>
                </article>
            </div>
        </div>
    </div>
</main>
