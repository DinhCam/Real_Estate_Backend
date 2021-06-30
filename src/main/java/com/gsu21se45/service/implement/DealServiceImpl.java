package com.gsu21se45.service.implement;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Deal;
import com.gsu21se45.repository.DealRepository;
import com.gsu21se45.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Override
    public List<Deal> findByConversationId(int conversationId) {
        return dealRepository.findDealsByConversation(new Conversation(conversationId));
    }

    @Override
    public Deal findLastByConversationId(int conversationId) {
        return dealRepository.findFirstByConversationOrderByCreateAtDesc(new Conversation(conversationId));
    }

    @Override
    public Deal save(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void update(int dealId, String status) {
        dealRepository.update(dealId, status);
    }

    @Override
    public Deal findByConversationAndCreateAt(Conversation conversation, Date createAt) {
        return dealRepository.findDealByConversationAndCreateAt(conversation, createAt);
    }
}
