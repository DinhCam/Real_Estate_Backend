package com.gsu21se45.service.implement;

import com.gsu21se45.repository.RealEstateTypeRepository;
import com.gsu21se45.service.RealEstateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealEstateTypeServiceImpl implements RealEstateTypeService {

    @Autowired
    private RealEstateTypeRepository repository;

    @Override
    public int getIdByName(String name) {
        return repository.getIdByName(name);
    }
}
