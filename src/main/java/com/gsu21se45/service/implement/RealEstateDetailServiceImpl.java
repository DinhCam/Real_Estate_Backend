package com.gsu21se45.service.implement;

import com.gsu21se45.repository.RealEstateDetailRepository;
import com.gsu21se45.service.RealEstateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealEstateDetailServiceImpl implements RealEstateDetailService {

    @Autowired
    private RealEstateDetailRepository repository;

    @Override
    public int getStreetWardIdById(int id) {
        return repository.getStreetWardIdById(id);
    }
}
