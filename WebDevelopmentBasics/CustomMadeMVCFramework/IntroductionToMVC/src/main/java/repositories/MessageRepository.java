package repositories;

import models.Message;

public interface MessageRepository {
    void save(Message message);
}
