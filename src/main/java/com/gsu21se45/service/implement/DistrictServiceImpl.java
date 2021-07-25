package com.gsu21se45.service.implement;

import com.gsu21se45.entity.District;
import com.gsu21se45.repository.DistrictRepository;
import com.gsu21se45.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository repository;

    @Override
    public List<District> getAll() {
        return repository.findAll();
    }
}
