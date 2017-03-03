package pizzaForum.repositories;

import pizzaForum.entities.Topic;

import java.util.List;

public interface TopicRepository {
    void save(Topic topic);
    List<Topic> getLastTen();
    Topic getById(Long id);
    List<Topic> getTopicsByCategoryId(Long id);
    void delete(Long id);
}
