package com.gsu21se45.service;

import com.gsu21se45.entity.RealEstate;

public interface RealEstateService {

    String getStaffId(int conversationId);

    RealEstate getByConversationId(int conversationId);
}
