package repositories;

import models.Message;

import java.util.List;

public interface MessageRepository {
    void save(Message message);
    List<Message> getAllMessages();
    Message getById(Long id);
    void delete(Message message);
}
