package pizzaForum.models.viewModels;

import pizzaForum.models.bindingModels.user.LoggedUser;

import java.util.Date;

public class ViewReply {
    private String content;
    private LoggedUser author;
    private Date publishDate;
    private String imageURL;
    private ViewTopic topic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ViewTopic getTopic() {
        return topic;
    }

    public void setTopic(ViewTopic topic) {
        this.topic = topic;
    }
}
