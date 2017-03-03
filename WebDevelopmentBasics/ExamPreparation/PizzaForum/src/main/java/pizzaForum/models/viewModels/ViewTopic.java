package pizzaForum.models.viewModels;

import pizzaForum.models.bindingModels.user.LoggedUser;

import java.util.Date;
import java.util.List;

public class ViewTopic {
    private Long id;
    private String title;
    private LoggedUser author;
    private String content;
    private Date publishDate;
    private ViewCategory category;
    private List<ViewReply> replies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LoggedUser getAuthor() {
        return author;
    }

    public void setAuthor(LoggedUser author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public ViewCategory getCategory() {
        return category;
    }

    public void setCategory(ViewCategory category) {
        this.category = category;
    }

    public List<ViewReply> getReplies() {
        return replies;
    }

    public void setReplies(List<ViewReply> replies) {
        this.replies = replies;
    }
}
