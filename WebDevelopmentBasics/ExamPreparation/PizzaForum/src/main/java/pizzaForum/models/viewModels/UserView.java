package pizzaForum.models.viewModels;

import java.util.List;

public class UserView {
    private Long id;
    private String username;
    private List<ViewTopic> topics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ViewTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<ViewTopic> topics) {
        this.topics = topics;
    }
}
