package repositories;

import models.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    private List<Message> messages;
    private static MessageRepository messageRepository;

    public static MessageRepository getInstance(){
        if (messageRepository == null){
            messageRepository = new MessageRepositoryImpl();
        }
        return messageRepository;
    }

    private MessageRepositoryImpl() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void save(Message message) {
        this.messages.add(message);
    }
}
