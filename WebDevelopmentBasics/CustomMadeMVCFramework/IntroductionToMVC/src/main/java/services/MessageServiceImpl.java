package services;

import dtos.MessageDto;
import models.Message;
import org.modelmapper.ModelMapper;
import repositories.MessageRepository;
import repositories.MessageRepositoryImpl;

public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    public MessageServiceImpl() {
        this.messageRepository = MessageRepositoryImpl.getInstance();
    }

    @Override
    public void save(MessageDto messageDto) {
        ModelMapper modelMapper = new ModelMapper();
        Message message = modelMapper.map(messageDto,Message.class);
        this.messageRepository.save(message);
    }
}
