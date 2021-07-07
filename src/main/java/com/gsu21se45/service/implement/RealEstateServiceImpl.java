package com.gsu21se45.service.implement;

import com.gsu21se45.entity.RealEstate;
import com.gsu21se45.repository.RealEstateRepository;
import com.gsu21se45.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealEstateServiceImpl implements RealEstateService {

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Override
    public String getStaffId(int conversationId) {
        return realEstateRepository.getStaffId(conversationId);
    }

    @Override
    public RealEstate getByConversationId(int conversationId) {
        return realEstateRepository.getByConversationId(conversationId);
    }
}
