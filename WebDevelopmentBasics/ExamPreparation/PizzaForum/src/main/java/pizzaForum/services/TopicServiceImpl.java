package pizzaForum.services;

import pizzaForum.entities.Topic;
import pizzaForum.models.bindingModels.topic.NewTopic;
import pizzaForum.models.viewModels.ViewTopic;
import pizzaForum.repositories.TopicRepository;
import pizzaForum.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TopicServiceImpl implements TopicService {
    @Inject
    private TopicRepository topicRepository;
    @Override
    public void save(NewTopic topic) {
        Topic topicToPersist = ModelParser.getInstance().map(topic,Topic.class);
        this.topicRepository.save(topicToPersist);
    }

    @Override
    public List<ViewTopic> getAllTopics() {
        List<Topic> topics = this.topicRepository.getLastTen();
        List<ViewTopic> viewTopics = new ArrayList<>();
        for (Topic topic : topics) {
            ViewTopic viewTopic = ModelParser.getInstance().map(topic,ViewTopic.class);
            viewTopics.add(viewTopic);
        }
        return viewTopics;
    }

    @Override
    public ViewTopic findById(Long id) {
        Topic topic = this.topicRepository.getById(id);
        ViewTopic viewTopic = null;
        if (topic != null){
            viewTopic = ModelParser.getInstance().map(topic,ViewTopic.class);
        }
        return viewTopic;
    }

    @Override
    public List<ViewTopic> getTopicsByCategoryId(Long id) {
        List<Topic> topics = this.topicRepository.getTopicsByCategoryId(id);
        List<ViewTopic> viewTopics = new ArrayList<>();
        for (Topic topic : topics) {
            ViewTopic viewTopic = ModelParser.getInstance().map(topic,ViewTopic.class);
            viewTopics.add(viewTopic);
        }
        return viewTopics;
    }

    @Override
    public void delete(Long id) {
        this.topicRepository.delete(id);
    }


}
