package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.entity.User;
import com.gsu21se45.repository.ConversationRepository;
import com.gsu21se45.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation getConversation(String buyerId, String sellerId, int realEstateId) {
        return conversationRepository.getConversationByBuyerAndAndSellerAndRealEstate(new User(buyerId), new User(sellerId), new RealEstate(realEstateId));
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public List<Integer> getIdsByRecipientId(String recipientId) {
        return conversationRepository.getIdsByRecipientId(recipientId);
    }
}
