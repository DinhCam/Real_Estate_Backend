package com.gsu21se45.service;

import com.gsu21se45.entity.Conversation;
import com.gsu21se45.entity.Deal;

import java.util.Date;
import java.util.List;

public interface DealService {

    List<Deal> findByConversationId(int conversationId);

    Deal findLastByConversationId(int conversationId);

    Deal save(Deal deal);

    void update(int dealId, String status);

    Deal findByConversationAndCreateAt(Conversation conversation, Date createAt);
}
