package com.gsu21se45.service;

import com.gsu21se45.entity.Conversation;

public interface ConversationService {

    Conversation getConversation(String buyerId, String sellerId, int realEstateId);

    Conversation save(Conversation conversation);
}
