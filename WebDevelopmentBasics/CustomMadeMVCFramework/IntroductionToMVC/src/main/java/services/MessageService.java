package services;

import dtos.MessageDto;

import java.util.List;

public interface MessageService {
    void save(MessageDto messageDto);
    List<MessageDto> getAllMessages();
    MessageDto getById(Long id);
    void delete(Long id);
}
