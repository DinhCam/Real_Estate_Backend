package com.gsu21se45.service;

import com.gsu21se45.entity.Conversation;

public interface ConversationService {

    Conversation getConversation(int buyerId, int sellerId, int realEstateId);

    Conversation save(Conversation conversation);
}
