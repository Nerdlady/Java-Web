package pizzaForum.models.bindingModels.topic;

import pizzaForum.models.bindingModels.category.CategoryModel;
import pizzaForum.models.bindingModels.user.LoggedUser;

import java.util.Date;

public class NewTopic {
    private String title;
    private String content;
    private CategoryModel category;
    private LoggedUser author;
    private Date publishDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public LoggedUser getAuthor() {
        return author;
    }

    public void setAuthor(LoggedUser author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
