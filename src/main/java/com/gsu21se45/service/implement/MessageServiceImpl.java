package com.gsu21se45.service.implement;

import com.gsu21se45.dto.MessageStatus;
import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Message;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.MessageRepository;
import com.gsu21se45.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findByConversationId(int conversationId) {
        return messageRepository.findMessagesByConversation(new Conversation(conversationId));
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Long countNewMessages(String senderId, int conversationId) {
        return messageRepository.countBySenderAndAndConversationAndStatus(new User(senderId), new Conversation(conversationId), MessageStatus.RECEIVED + "");
    }

    @Override
    public Optional<Message> findById(int id) {
        return messageRepository.findById(id);
    }

}
