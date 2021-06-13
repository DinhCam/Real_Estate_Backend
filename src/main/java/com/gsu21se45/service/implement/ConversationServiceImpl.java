package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.repository.ConversationRepository;
import com.gsu21se45.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    ConversationRepository conversationRepository;

    @Override
    public Conversation getConversation(int buyerId, int sellerId, int realEstateId) {
        Conversation conversation = conversationRepository.getConversation(buyerId, sellerId, realEstateId);

        return conversation;
    }
}
