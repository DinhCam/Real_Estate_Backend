package com.gsu21se45.service;

import com.gsu21se45.entity.Conversation;

import java.util.List;

public interface ConversationService {

    Conversation getConversation(String buyerId, String staffId, int realEstateId);

    Conversation save(Conversation conversation);

    List<Integer> getIdsByRecipientId(String recipientId);

    Conversation getConversationByRealEstateIdAndStatusOfAppointment(int realEstateId, String status);
}
