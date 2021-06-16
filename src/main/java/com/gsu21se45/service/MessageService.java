package com.gsu21se45.service;

import com.gsu21se45.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findByConversationId(int conversationId);

    Message save(Message message);
}
