package com.gsu21se45.service;

import com.gsu21se45.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    List<Message> findByConversationId(int conversationId);

    Message save(Message message);

    Long countNewMessages(String senderId, int conversationId);

    Optional<Message> findById(int id);
}
