package com.gsu21se45.service;

import com.gsu21se45.entity.RealEstate;

public interface RealEstateService {

    String getSellerId(int conversationId);

    RealEstate getByConversationId(int conversationId);
}
