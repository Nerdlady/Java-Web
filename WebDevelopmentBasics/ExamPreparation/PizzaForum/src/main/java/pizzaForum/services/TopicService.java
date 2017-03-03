package pizzaForum.services;

import pizzaForum.models.bindingModels.topic.NewTopic;
import pizzaForum.models.viewModels.ViewTopic;

import java.util.List;

public interface TopicService {
    void save(NewTopic topic);
    List<ViewTopic> getAllTopics();
    ViewTopic findById(Long id);
    List<ViewTopic> getTopicsByCategoryId(Long id);
    void delete(Long id);
}
