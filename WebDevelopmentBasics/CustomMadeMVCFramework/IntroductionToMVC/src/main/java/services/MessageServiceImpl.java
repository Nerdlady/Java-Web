package services;

import dtos.MessageDto;
import models.Message;
import org.modelmapper.ModelMapper;
import repositories.MessageRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageRepository messageRepository;

    @Override
    public void save(MessageDto messageDto) {
        ModelMapper modelMapper = new ModelMapper();
        Message message = modelMapper.map(messageDto,Message.class);
        this.messageRepository.save(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = this.messageRepository.getAllMessages();
        ModelMapper modelMapper = new ModelMapper();
        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message message : messages) {
            MessageDto messageDto = modelMapper.map(message,MessageDto.class);
            messageDtos.add(messageDto);
        }
        return messageDtos;
    }

    @Override
    public MessageDto getById(Long id) {
        Message message = this.messageRepository.getById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(message,MessageDto.class);
    }

    @Override
    public void delete(Long id) {
        Message message = this.messageRepository.getById(id);
        this.messageRepository.delete(message);
    }
}
